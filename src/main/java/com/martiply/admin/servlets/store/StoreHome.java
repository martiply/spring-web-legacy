package com.martiply.admin.servlets.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.martiply.dao.StoreDAO;
import com.martiply.model.BasicItem;
import com.martiply.model.Store;

@WebServlet(name = "Store", description = "Store list", urlPatterns = { BaseServlet.SERVLET_STORE_HOME })
public class StoreHome extends BaseServlet {

	private static final long serialVersionUID = -8805160315911458200L;
	private StoreDAO storeDAO;
	private InventoryDAO inventoryDAO;
	ResourceBundle bundle;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		storeDAO = (StoreDAO) ctx.getBean("storeDAO");
		inventoryDAO = (InventoryDAO)ctx.getBean("inventoryDAO");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		final int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
			return;
		}
		bundle = ResourceBundle.getBundle("store");

		request.setAttribute(ATTR_PAGE_SIDE_ITEM, "store");

		String a = request.getParameter(PARAM_A);

		if (a == null) {
			passDefault(request, ownerId);
			if (session.getAttribute(SESSION_PAGE_NOT_AVAILABLE) != null) {
				request.setAttribute(SESSION_PAGE_NOT_AVAILABLE, session.getAttribute(SESSION_PAGE_NOT_AVAILABLE));
				session.removeAttribute(SESSION_PAGE_NOT_AVAILABLE);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);
			return;
		}

		final int storeId;
		switch (a) {

//		case "new":
//			request.setAttribute(LOAD_MAIN, "storeNew.jsp");
//			break;

		case "view":
			storeId = Integer.parseInt(request.getParameter(PARAM_I));

			ExecutorService executorService = Executors.newFixedThreadPool(2);
			Future<Store> storeFuture = executorService.submit(new Callable<Store>() {

				@Override
				public Store call() throws Exception {
					return storeDAO.getStore(storeId, ownerId);
				}
			});

			Future<ArrayList<BasicItem>> inventoryFuture = executorService.submit(new Callable<ArrayList<BasicItem>>() {

				@Override
				public ArrayList<BasicItem> call() throws Exception {
					return inventoryDAO.getBasicItems(storeId);
				}
			});
			
			executorService.shutdown();
			
			Store store;
			
			try {
			store =	 storeFuture.get();
			if (store == null){
				throw new IOException("Store not found for this owner: Unauthorized Access");
			}
			session.setAttribute(SESSION_STORE_ID_VIEW, storeId);
			request.setAttribute(ATTR_STORE, store);
			ArrayList<BasicItem> items = inventoryFuture.get();
			request.setAttribute(ATTR_LIST_ITEMS, items);
			request.setAttribute(ATTR_PAGE_CONTENT, "storeView.jsp");
			request.setAttribute(ATTR_DASHBOARD_TITLE, "#" + store.getStoreId());
			request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("StoreViewSubtitle"));
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			} catch (InterruptedException e) {
				throw new IOException(e.getMessage());
			} catch (ExecutionException e) {
				throw new IOException(e.getMessage());
			}
			break;

		case "edit":
			storeId = Integer.parseInt(request.getParameter(PARAM_I));
			store = storeDAO.getStore(storeId, ownerId);
			if (store == null) {
				passDefault(request, ownerId);
			} else {
				request.setAttribute(ATTR_STORE, store);
				request.setAttribute(ATTR_PAGE_CONTENT, "storeEdit.jsp");
				request.setAttribute(ATTR_DASHBOARD_TITLE, store.getName());
				request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("EditStoreInstruction"));
			}
			break;

		}
		request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response);
	}

	private void passDefault(HttpServletRequest request, int ownerId) {
		request.setAttribute(ATTR_TS_NOW,  System.currentTimeMillis() / 1000);
		request.setAttribute(ATTR_PAGE_CONTENT, "store.jsp");
		ArrayList<Store> stores = storeDAO.getAllStoresAndCatalogsByOwnerId(ownerId);
		request.setAttribute(ATTR_LIST_STORES, stores);
		request.setAttribute(ATTR_DASHBOARD_TITLE, bundle.getString("StoreListTitle"));
		request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("StoreListSubtitle"));
	}
}