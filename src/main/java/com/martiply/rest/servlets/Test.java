package com.martiply.rest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.dao.StoreDAO;
import com.martiply.model.Store;
import com.martiply.utils.GsonUtils;

@WebServlet(name = "Test", description = "Martiply Public API", urlPatterns = { "/test" })
public class Test extends HttpServlet {	
	private ApplicationContext ctx;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
	    PrintWriter out = resp.getWriter();	 
	    
		StoreDAO storeDAO = (StoreDAO) ctx.getBean("storeDAO");
//		ArrayList<Store> stores = storeDAO.getStoresCanCreateCoupon(1);
//		out.println(GsonUtils.toJson(stores));
		
	}
}
