package com.martiply.admin.servlets.promo;

import java.io.IOException;
import java.util.ArrayList;

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
import com.martiply.model.Coupon;

@WebServlet(name = "Promotion", description = "Promotion control", urlPatterns = { BaseServlet.SERVLET_COUPON_HOME})
public class CouponHome extends BaseServlet {
	private static final long serialVersionUID = -1394370104324683220L;	
	private ApplicationContext ctx;
	private CouponDAO couponDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		couponDAO = (CouponDAO)ctx.getBean("couponDAO");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		final int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}		
		ArrayList<Coupon> coupons = couponDAO.getAllCoupons(ownerId);
		request.setAttribute(ATTR_LIST_COUPONS, coupons);
		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "cp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cp.jsp");		
		request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);
	}
}