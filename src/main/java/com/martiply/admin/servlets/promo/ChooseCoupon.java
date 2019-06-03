package com.martiply.admin.servlets.promo;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.martiply.dao.StoreDAO;
import com.martiply.model.Store;

@WebServlet(name = "Choose Coupon", description = "Choose existing or new coupon", urlPatterns = { BaseServlet.SERVLET_COUPON_CHOOSE})
public class ChooseCoupon extends BaseServlet {
	private static final long serialVersionUID = 5806208865563172442L;
	private ApplicationContext ctx;
	private StoreDAO storeDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		storeDAO = (StoreDAO) ctx.getBean("storeDAO");

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		final int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
			return;
		}
		
		ArrayList<Store> availableStores = storeDAO.getStoresCanCreateCoupon(ownerId,  System.currentTimeMillis() / 1000);
		session.setAttribute(SESSION_AVAILABLE_STORES, availableStores);
		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "cp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cp.jsp");
		request.setAttribute(ATTR_PAGE_CONTENT, "cpChoose.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			out.println("-1");
			return;
		}

		String cpId = request.getParameter(PARAM_A);
		String cpContent = request.getParameter(PARAM_I);
		
		if (cpId == null || cpContent == null) {
			out.println("False parameters");
			return;
		}
		session.setAttribute(SESSION_CP_ID, cpId);
		session.setAttribute(SESSION_CP_CONTENT, cpContent);
		out.println("0");

	}
}
