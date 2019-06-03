package com.martiply.admin.servlets.promo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.OwnerDAO;
import com.martiply.model.User;
import com.martiply.model.internal.Credentials;
import com.martiply.twitter.Twittery;

import twitter4j.Status;

@WebServlet(name = "Ajax Tweet ID Verification", urlPatterns = { BaseServlet.SERVLET_COUPON_VERIFY_TWEETID })
public class AjaxTweetIdVerify extends BaseServlet {	
	private static final long serialVersionUID = -2769802689592219975L;
	
	private ApplicationContext ctx;
	private OwnerDAO ownerDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO"); 
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
		
		Long tweetId = Long.parseLong(request.getParameter(PARAM_A));
		
		Credentials credentials = ownerDAO.getCredentials(ownerId);
		
		User user = credentials.getUser();
		
		final Twittery twittery = new Twittery(user.getTwitterToken(), user.getTwitterSecret());
		Status status = twittery.getStatus(tweetId);
		if (status.getUser().getId() == user.getUid()){
			out.println(status.getText());
		}else{
			out.println("-2"); // not own tweet
		}	
	}

}
