package com.martiply.admin.servlets.promo;

import java.io.IOException;

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
import com.martiply.model.internal.CouponComprehensiveStat;

@WebServlet(name = "ViewPromo", description = "view coupon details", urlPatterns = { BaseServlet.SERVLET_COUPON_VIEW })
public class ViewCoupon extends BaseServlet{

	private static final long serialVersionUID = 3216549910883610923L;
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
			response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
			return;
		}
				
		long cpId = Long.parseLong(request.getParameter(PARAM_A));
		Coupon coupon = couponDAO.getCouponSafe(cpId, ownerId);		
		CouponComprehensiveStat catalogComprehensiveStat = couponDAO.getComprehensiveStat(coupon, 10);
		
		request.setAttribute(ATTR_COUPON, coupon);
		request.setAttribute(ATTR_STATS, catalogComprehensiveStat);
		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "cp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cpView.jsp");		
		request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);		
	}	

}
