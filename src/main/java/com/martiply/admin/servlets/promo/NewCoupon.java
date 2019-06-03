package com.martiply.admin.servlets.promo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.CouponDAO;
import com.martiply.dao.OwnerDAO;
import com.martiply.dao.StoreDAO;
import com.martiply.db.tables.TableCoupon;
import com.martiply.model.Coupon;
import com.martiply.model.User;
import com.martiply.model.internal.Credentials;
import com.martiply.twitter.Twittery;
import com.martiply.utils.Flow;

import twitter4j.TwitterException;
@WebServlet(name = "New Coupon", description = "Create and register new coupon", urlPatterns = { BaseServlet.SERVLET_COUPON_NEW})
public class NewCoupon extends BaseServlet{
	private static final long serialVersionUID = -3663789592249538261L;
	
	private ApplicationContext ctx;
	private CouponDAO couponDAO;
	private OwnerDAO ownerDAO;
	private StoreDAO storeDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO");
		couponDAO = (CouponDAO)ctx.getBean("couponDAO");
		storeDAO = (StoreDAO)ctx.getBean("storeDAO");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		final int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
			return;
		}
		
		String cpId = (String)session.getAttribute(SESSION_CP_ID);
		String content = (String)session.getAttribute(SESSION_CP_CONTENT);
		
		if (cpId.equals("0")){
			content = content + " #martiply";
		}
		
		if (content == null || cpId == null){		
			response.sendRedirect(getServletContext().getContextPath() + "/cp/new");
			return;
		}
		
		
		session.removeAttribute(SESSION_CP_ID);
		session.removeAttribute(SESSION_CP_CONTENT);
				
		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "cp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cp.jsp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cpNew.jsp");
		request.setAttribute(ATTR_CPID, cpId);
		request.setAttribute(ATTR_CONTENT, content);
		request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			out.println("-1");;
			return;
		}
		
		String cpId = request.getParameter(TableCoupon.CPID);
		String content = request.getParameter(TableCoupon.CONTENT);
		int end = Integer.parseInt(request.getParameter(TableCoupon.END)); // will go to 500 if error
		String terms = request.getParameter(TableCoupon.TERMS);
		float bid = Float.parseFloat(request.getParameter(TableCoupon.BID));
		String img = request.getParameter(PARAM_IMG);
		if (img.contains("?")){
			img = img.substring(0, img.indexOf("?"));
		}
		
		String[] storeString = request.getParameterValues(PARAM_A_ARRAY);
		int[] stores = new int[storeString.length];
		for (int i = 0; i <storeString.length; i++){
			stores[i] = Integer.parseInt(storeString[i]);
		}
	
		
		if (content == null || content.trim().isEmpty()){
			out.println("Content error");
			return;
		}
		
		
		if (end == 0){
			out.println("Validity error");
			return;
		}
		
		if (terms == null || terms.isEmpty() || terms.length() > 500){
			out.println("Terms error");
			return;
		}
		
		int defaultBid = (int) session.getAttribute(SESSION_DEFAULT_BID);
		bid = defaultBid < bid ? bid : defaultBid ;
		
		Coupon coupon = new Coupon();
		coupon.setContent(content);
		coupon.setEndSec(end);
		coupon.setTerms(terms);
		coupon.setBid(bid);
		
		URL url = new URL(img);

		
		File temp = File.createTempFile("s3-temp-img-mainSource", ".jpg");
		temp.deleteOnExit();
		FileUtils.copyURLToFile(url, temp);
		
		Credentials credentials = ownerDAO.getCredentials(ownerId);
		User user = credentials.getUser();
		
		
		if (cpId == null){
			Twittery twittery = new Twittery(user.getTwitterToken(), user.getTwitterSecret()); 
			try {
				long newTweetId = twittery.tweetPic(temp, content);
				coupon.setCpId(newTweetId);
			} catch (TwitterException e) {
				out.println("Error tweeting");
				return;
			}
		}else{
			coupon.setCpId(Long.parseLong(cpId));
		}
		
		Flow.newCoupon(storeDAO, couponDAO, coupon, ownerId, stores, temp);					
		out.println("0");
	}

}
