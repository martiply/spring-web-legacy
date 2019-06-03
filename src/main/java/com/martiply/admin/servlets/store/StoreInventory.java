package com.martiply.admin.servlets.store;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.martiply.dao.InventoryDAO;
import com.martiply.db.tables.TableStore;
import com.martiply.model.BasicItem;

@WebServlet(name = "StoreInventory", description = "Store inventory", urlPatterns = { "/storeinv" })
public class StoreInventory extends BaseServlet {

	private static final long serialVersionUID = 2465738478812357966L;
	
	private static final String PARAM_A = "a";
	private static final String PARAM_ARRAY = "p[]";

 	private ResourceBundle bundle;
	private InventoryDAO inventoryDAO;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		inventoryDAO = (InventoryDAO)ctx.getBean("inventoryDAO");
		bundle = ResourceBundle.getBundle("store");
	}

	/* Can only enter this servlet from store/view/storeId. StoreId will be matched against session to prevent unauthorized access
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);			
		if (ownerId == 0){
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}
		
		int storeId = session.getAttribute(SESSION_STORE_ID_VIEW) != null ? (Integer)session.getAttribute(SESSION_STORE_ID_VIEW): 0;

		Integer i = Integer.parseInt(request.getParameter(PARAM_A));
		if (storeId != i){
			throw new IOException("Session mismatch: Unauthorized access");
		}

		ArrayList<BasicItem> items = inventoryDAO.getBasicItemsNotInInventory(storeId, ownerId);
		request.setAttribute("LIST_ITEMS", items);
		request.setAttribute(TableStore.STORE_ID, storeId);
		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "store");
	    request.setAttribute(ATTR_PAGE_CONTENT, "storeInventory.jsp");		
	    request.setAttribute(ATTR_DASHBOARD_TITLE, String.format(bundle.getString("StoreInventoryTitle"), storeId));
	    request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("StoreInventorySubtitle"));
	
	    request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();	  
		response.setContentType("text/plain");
		
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);			
		if (ownerId == 0){
			out.println("-1");
			return;
		}
		
		Integer storeId = (Integer)session.getAttribute(SESSION_STORE_ID_VIEW);

		if (storeId == null){
			throw new IOException("No store id: Unauthorized access");
		}
		
		String[] itemIds = request.getParameterValues(PARAM_ARRAY);
		
		if (itemIds == null){
			out.println("Input empty");
			return;
		}
		
		String a = request.getParameter(PARAM_A);
		
		switch (a) {
		case "add":
			inventoryDAO.putItems(itemIds, storeId, ownerId);
			break;
		case "remove":
			inventoryDAO.removeItems(itemIds, storeId);
			break;
		}
			
		out.println("0");
	}

	
}
