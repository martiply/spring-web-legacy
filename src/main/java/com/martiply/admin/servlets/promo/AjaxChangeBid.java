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
import com.martiply.dao.CouponDAO;

@WebServlet(name = "Ajax Change bid", description = "change bid with ajax", urlPatterns = { BaseServlet.SERVLET_COUPON_CHANGE_BID})
public class AjaxChangeBid extends BaseServlet{


	private static final long serialVersionUID = -8792576503266295538L;
	private CouponDAO couponDAO;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		couponDAO = (CouponDAO) ctx.getBean("couponDAO");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			out.println("-1");
			return;
		}
		
		
		long tweetId = Long.parseLong(request.getParameter(PARAM_A));
		float newBid = Float.parseFloat(request.getParameter(PARAM_I));
		
		int defaultBid = (int) session.getAttribute(SESSION_DEFAULT_BID);
		
		if (newBid < defaultBid){
			out.println("-2");
			return;
		}		
		couponDAO.updateBid(tweetId, ownerId, newBid);		
		out.println("0");
		
	}
}
