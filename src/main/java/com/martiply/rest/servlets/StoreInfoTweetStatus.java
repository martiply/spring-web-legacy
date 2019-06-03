package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import twitter4j.Status;

import com.martiply.dao.CouponDAO;
import com.martiply.dao.StoreDAO;
import com.martiply.dao.UserDAO;
import com.martiply.db.tables.TableShareHistory;
import com.martiply.model.Coupon;
import com.martiply.model.MetaData;
import com.martiply.model.SocMed;
import com.martiply.model.Store;
import com.martiply.model.TweetStatus;
import com.martiply.model.User;
import com.martiply.twitter.Twittery;



public class StoreInfoTweetStatus extends BaseWorker {
	public static final int MODE = 12;
	private int storeId;
	private MetaData metaData = new MetaData();
	private long uid;
	private TweetStatus tweetStatus = new TweetStatus();

	
	/**
	 * Return Store and Tweet status (has been RTed or not)
	 * @param request
	 * @param out
	 */
	public StoreInfoTweetStatus(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		this.storeId = Integer.parseInt(request.getParameter(PARAM_STORE_ID));
		String twUidString = request.getParameter(PARAM_UID);
		if (twUidString != null) {
			uid = Long.parseLong(twUidString);
		}
	}

	@Override
	public void start(ApplicationContext ctx) {
		StoreDAO storeDAO = (StoreDAO) ctx.getBean("storeDAO");
		long currentSec = System.currentTimeMillis() / 1000;
		final Store storeInfo = storeDAO.getStoreInfoAndActiveTwitterCoupon(storeId, currentSec);
				
		if (storeInfo == null) {
			metaData.setStatus(-1);
			metaData.setMessage("no store found");
			outJson(metaData);
			return;
		}
		metaData.setStoreInfo(storeInfo);
		final Coupon coupon = storeInfo.getCoupon();
		if (coupon == null) { //if there's no catalog then stops
			metaData.setMessage("no catalog found");
			outJson(metaData);
			return;
		}
		
		// if there's catalog then proceed with status check, last sharers etc
		

		final CouponDAO couponDAO = (CouponDAO)ctx.getBean("couponDAO");
		final UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");

		if (uid != 0) {
			final User user = userDAO.getTwitterUser(uid);
			if (user == null){
				metaData.setMessage("user " + uid + " not found");
				outJson(metaData);
				return;
			}
			final Twittery twittery = new Twittery(user.getTwitterToken(), user.getTwitterSecret());

			final Status status = twittery.getStatus(coupon.getCpId());

			if (status != null) {
				tweetStatus.setRetweetId(status.getCurrentUserRetweetId());
				if (tweetStatus.getRetweetId() != -1) {
					// Try to insert record to table. If record exists, the insertion will be ignored
					// This is required because some users RT off-app
					final SocMed socMed = new SocMed();
					socMed.setcId(coupon.getCpId());
					socMed.setTs(System.currentTimeMillis() / 1000);
					socMed.setMethod(SocMed.VAL_METHOD_OFF_APP);
					socMed.setValue(String.valueOf(status.getCurrentUserRetweetId()));
					socMed.setLat(0f);
					socMed.setLng(0f);

					couponDAO.insertShare(uid, coupon.getCpId(), tweetStatus.getRetweetId(), socMed.getTs(),socMed.getValue(), socMed.getMethod(), socMed.getLat(),
							socMed.getLng(), -1, 0);

				} else {
					// no RT can mean it has been deleted from Twitter.
					couponDAO.updateShareStatus(tweetStatus.getRetweetId(), TableShareHistory.STATUS_UNSHARED);
				}
			}

		}

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.execute(new Runnable() { // find others who shared

					@Override
					public void run() {
						ArrayList<User> sharers = couponDAO.getSharerProfilePics(coupon.getCpId(), 3);
						tweetStatus.setSharers(sharers);
					}
				});

		executorService.execute(new Runnable() { // count n sharers

					@Override
					public void run() {
						int shares = couponDAO.countShares(coupon.getCpId());
						tweetStatus.setShareCount(shares);
					}
				});

		executorService.shutdown();
		try {
			executorService.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		metaData.setTweetStatus(tweetStatus);
		outJson(metaData);

	}
}
