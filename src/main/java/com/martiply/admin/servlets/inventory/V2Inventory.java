package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.StandardDAO;
import com.martiply.model.BasicItem;

@WebServlet(name = "Inventory", description = "Inventory landing page", urlPatterns = { BaseServlet.SERVLET_INVENTORY_HOME })
public class V2Inventory extends BaseServlet{

	private static final long serialVersionUID = -2179856815523132607L;
	private ResourceBundle bundle;
	private StandardDAO standardDAO;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		standardDAO = (StandardDAO)ctx.getBean("standardDAO");
		bundle = ResourceBundle.getBundle("inventory");		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);			
		if (ownerId == 0){
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}
	    request.setAttribute(ATTR_PAGE_SIDE_ITEM, "inventory");

		String a = request.getParameter(PARAM_A);
		String i = request.getParameter(PARAM_I);
		
		if (a == null){
			ArrayList<BasicItem> items = standardDAO.getItems(ownerId);
			request.setAttribute("LIST_ITEMS", items);	
			int n = standardDAO.countItems(ownerId);
			request.setAttribute("ITEM_COUNT", n);
		    request.setAttribute(ATTR_PAGE_CONTENT, "inventory.jsp"); 	
			request.setAttribute(ATTR_DASHBOARD_TITLE, bundle.getString("DashboardTitle"));
			request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("DashboardSubtitle"));
		}else if (a.equals("new")){	
		    request.setAttribute(ATTR_PAGE_CONTENT, "inventorySingle.jsp");		
		}else if (a.equals("preupload")){
			request.setAttribute(ATTR_PAGE_CONTENT, "inventoryUploadInfo.jsp");
		}else if (a.equals("csv")){
			request.setAttribute(ATTR_PAGE_CONTENT, "inventoryMultiple.jsp");
		}
		else if ( a.equals("view") || a.equals("edit")){
			if (i != null){
				BasicItem item = standardDAO.getItem(i, ownerId);
				if (item != null){					
					request.setAttribute("ITEM", item);							
					request.setAttribute(ATTR_PAGE_CONTENT, a.equals("view") ? "inventoryView.jsp" : "inventoryEdit.jsp");
				}else{
					request.setAttribute(ATTR_PAGE_CONTENT, "resourceNotFound.jsp");
				}			
			}else{
				ArrayList<BasicItem> items = standardDAO.getItems(ownerId);
				request.setAttribute("LIST_ITEMS", items);	
			    request.setAttribute(ATTR_PAGE_CONTENT, "inventory.jsp"); 	
			}
		}
	    request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);   	    
	}

}
