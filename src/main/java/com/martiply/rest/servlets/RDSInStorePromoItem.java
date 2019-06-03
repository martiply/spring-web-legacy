package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.martiply.dao.InventoryDAO;
import com.martiply.dao.StoreDAO;
import com.martiply.model.MetaData;
import com.martiply.model.Store;

public class RDSInStorePromoItem extends BaseWorker{
	public static final int MODE = 7;
	
	private int storeId;
	private MetaData metaData = new MetaData();
	
	
	/**
	 * Find all {@link PromoItem} in a store
	 * @param request
	 * @param out
	 */
	public RDSInStorePromoItem(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		storeId = Integer.parseInt(request.getParameter(PARAM_STORE_ID));
	}

	@Override
	public void start(ApplicationContext ctx) {		
		StoreDAO storeDAO = (StoreDAO) ctx.getBean("storeDAO");
		Store storeInfo = storeDAO.getStore(storeId);
		
		if (storeInfo == null){
			metaData.setMessage("store not found");
			metaData.setStatus(-1);
			outJson(metaData);
			return;			
		}
		
//		InventoryDAO inventoryDAO = (InventoryDAO) ctx.getBean("inventoryDAO");
//		metaData.setIpps(inventoryDAO.getSales(storeInfo, false));	
		outJson(metaData);
	}

}
