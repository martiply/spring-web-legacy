package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.StoreDAO;
import com.martiply.model.BasicItem;
import com.martiply.model.MetaData;

public class RDSInStoreSearch extends BaseWorker {
	public static final int MODE = 6;
	private String input;
	private int storeId;
	private MetaData metaData = new MetaData();

	/**
	 * Find items in a store given keyword
	 * @param request
	 * @param out
	 * @throws NumberFormatException
	 */
	public RDSInStoreSearch(HttpServletRequest request, PrintWriter out) throws NumberFormatException {
		setOut(out);
		input = request.getParameter(PARAM_SEARCH_BY_KEYWORD);
		storeId = Integer.parseInt(request.getParameter(PARAM_STORE_ID));
	}

	@Override
	public void start(ApplicationContext ctx) {						
		StoreDAO storeDAO = (StoreDAO) ctx.getBean("storeDAO");		
		ArrayList<BasicItem> items = storeDAO.findInOneStore(input, storeId, 50);		
		if (items.isEmpty()){
			metaData.setMessage("item cannot be found");
		}else{
			metaData.setItems(items);
		}
		outJson(metaData);		
	}

}
