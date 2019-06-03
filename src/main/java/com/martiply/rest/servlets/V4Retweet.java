package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import twitter4j.Status;

import com.martiply.dao.CouponDAO;
import com.martiply.dao.OwnerDAO;
import com.martiply.dao.UserDAO;
import com.martiply.db.tables.TableCoupon;
import com.martiply.model.Coupon;
import com.martiply.model.MetaData;
import com.martiply.model.SocMed;
import com.martiply.model.TweetStatus;
import com.martiply.model.User;
import com.martiply.model.internal.CouponOwner;
import com.martiply.twitter.Twittery;

public class V4Retweet extends BaseWorker {

	public static final int MODE = 10;

	private MetaData metaData = new MetaData();
	private long uid;
	private String twT;
	private int rank;
	private long cpId, refId;
	private float price;
	private double lat, lng;
	private Twittery twittery;

	public V4Retweet(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		try {
			this.uid = Long.parseLong(request.getParameter(PARAM_UID));
			this.twT = request.getParameter(PARAM_TWITTER_TOKEN);
			this.cpId = Long.parseLong(request.getParameter(PARAM_CPID));
			this.refId = Long.parseLong(request.getParameter(PARAM_REF_BID_ID));
			this.rank = Integer.parseInt(request.getParameter(PARAM_RANK));
			this.lat = Double.parseDouble(request.getParameter(PARAM_LAT));
			this.lng = Double.parseDouble(request.getParameter(PARAM_LNG));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/*
		find catalog and check its eligibility (expiration, fund sufficient/ not, error twitter)
		if eligible tweet
		if (base)bidId > 0, get its	reference bid
		if ok then rt, if not update catalog status missing tweet
		check fund
		subtract credit
		insert share
		update share count to catalog and status to money insufficient if credit is negative, 		
	*/
	@Override
	public void start(ApplicationContext ctx) {

		if (cpId == 0 || twT == null || uid == 0 || twT.trim().isEmpty()) {
			metaData.setStatus(-1);
			metaData.setMessage("empty param");
			outJson(metaData);
			return;
		}
		final UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
		final CouponDAO couponDAO = (CouponDAO) ctx.getBean("couponDAO");
		final OwnerDAO ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO");
		
		final User user = userDAO.getTwitterUser(uid, twT);

		if (user == null) {
			metaData.setStatus(-1);
			metaData.setMessage("user not found");
			outJson(metaData);
			return;
		}
			
		CouponOwner couponOwner =	couponDAO.getCouponAndOwnerByCouponIdAndStatus(cpId, TableCoupon.STATUS_OK);
		if (couponOwner == null || couponOwner.getcoupon() == null) {
			metaData.setStatus(-1);
			metaData.setMessage("catalog not exist, is expired, has errors, or fund not sufficient");
			outJson(metaData);
			return;
		}
		
		try {
			ExecutorService executorService = refId > 0 ? Executors.newFixedThreadPool(2) : Executors.newSingleThreadExecutor();
			Future<Status> fTweetStatus = executorService.submit(new Callable<Status>() {

				@Override
				public Status call() throws Exception {
					 twittery = new Twittery(user.getTwitterToken(), user.getTwitterSecret());
					 return twittery.retweet(cpId);
				}
			});

			Future<Float> fbidPad = null;
			if (refId > 0) {
				fbidPad = executorService.submit(new Callable<Float>() {

					@Override
					public Float call() throws Exception {
						Coupon baseBidCatalog = couponDAO.getCouponByCpId(refId);
						return baseBidCatalog.getBid();
					}
				});
			}
			
			executorService.shutdown();
			
			this.price = couponOwner.getBidAndCurrency().getDefaultBid();

			final Status statusFromTwitter = fTweetStatus.get();
			if (statusFromTwitter == null) {
				metaData.setStatus(-1);
				metaData.setMessage(twittery.te.getErrorMessage());
				
				switch (twittery.te.getErrorCode()) {
				case 144:					
					ExecutorService eset = Executors.newSingleThreadExecutor();
					eset.execute(new Runnable() {
						
						@Override
						public void run() {
							couponDAO.updateStatus(couponOwner.getcoupon(), TableCoupon.STATUS_MISSING_AT_TWITTER);
						}
					});
					break;
				default:
					break;
				}				
				outJson(metaData);
				return;
			}

			final TweetStatus tweetStatus = new TweetStatus();
			tweetStatus.setRetweetId(statusFromTwitter.getId()); // pay attention! getId is correct way to get retweet id
			metaData.setTweetStatus(tweetStatus);

			if (fbidPad != null) {
				this.price = fbidPad.get();
			}

			// check if money is drained
			boolean deactivateNoFund = false;
			if ((couponOwner.getOwner().getCredit() - couponOwner.getcoupon().getBid() ) < couponOwner.getcoupon().getBid()) {
				deactivateNoFund = true;
			}
			
			executorService = Executors.newFixedThreadPool(3);

			executorService.execute(new Runnable() {

				@Override
				public void run() {
					long ts = System.currentTimeMillis() / 1000;
					couponDAO.insertShare(uid, cpId, tweetStatus.getRetweetId(), ts, String.valueOf(statusFromTwitter.getCurrentUserRetweetId()), SocMed.VAL_METHOD_IN_APP, lat, lng, rank, price);
				}
			});
			
			// subtract credit
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					ownerDAO.subtractCredit(couponOwner.getOwner().getOwnerId(), price);
				}
			});

			//update share
			if (!deactivateNoFund){
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						couponDAO.updateShare(couponOwner.getcoupon(), user.getTwitterScreenName(), 1);
					}
				});
			}else{
				executorService.execute(new Runnable() {
					
					@Override
					public void run() {
						couponDAO.updateShareAndStatus(couponOwner.getcoupon(), user.getTwitterScreenName(), 1, TableCoupon.STATUS_FUND_SUFFICIENT);
					}
				});
			}
			
			executorService.shutdown();
			try {
				executorService.awaitTermination(30, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			outJson(metaData);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
