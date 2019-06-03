package com.martiply.admin.servlets.store;

import java.io.IOException;
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
import com.martiply.dao.StoreDAO;
import com.martiply.db.tables.TableStore;
import com.martiply.utils.Flow;

@WebServlet(name = "Ajax Store Update", description = "AJAX Store Update", urlPatterns = { "/asu" })
public class AjaxStoreUpdate extends BaseServlet {
	
	private static final long serialVersionUID = 8530488033931191779L;
	private StoreDAO storeDAO;
	private InventoryDAO inventoryDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		storeDAO = (StoreDAO)ctx.getBean("storeDAO");
		inventoryDAO = (InventoryDAO)ctx.getBean("inventoryDAO");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0){
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}


		int tz = Integer.parseInt(request.getParameter(TableStore.TZ));
		String currency;
		switch (tz) {
		case 9:
			currency = "JPY";
			break;

		default:
			currency = "IDR";
		}

		String oldStoreid = request.getParameter(TableStore.STORE_ID);

		com.martiply.model.Store newStore = new com.martiply.model.Store();
		newStore.setName(request.getParameter(TableStore.NAME));
		newStore.setLat(Double.parseDouble(request.getParameter(TableStore.LAT)));
		newStore.setLng(Double.parseDouble(request.getParameter(TableStore.LNG)));
		newStore.setZip(request.getParameter(TableStore.ZIP));
		newStore.setAddress(request.getParameter(TableStore.ADDRESS));
		newStore.setPhone(request.getParameter(TableStore.PHONE));
		newStore.setTag(request.getParameter(TableStore.TAG));
		newStore.setStory(request.getParameter(TableStore.STORY));
		newStore.setOpen(request.getParameter(TableStore.OPEN));
		newStore.setClose(request.getParameter(TableStore.CLOSE));
		newStore.setTz(tz);
		newStore.setCurrency(currency);

		String redirectUrl = getServletContext().getContextPath() + "/store/";
		if (oldStoreid == null || oldStoreid.trim().isEmpty()) {
			int newStoreId = Flow.newStore(storeDAO, inventoryDAO, ownerId, newStore);
			redirectUrl += newStoreId;
		}else{
			newStore.setStoreId(Integer.parseInt(oldStoreid));
			storeDAO.updateStore(ownerId, newStore);
			redirectUrl += oldStoreid;			
		}		
		response.sendRedirect(redirectUrl);
	}

}
