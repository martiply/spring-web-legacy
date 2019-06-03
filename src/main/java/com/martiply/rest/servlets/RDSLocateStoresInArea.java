package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.StoreDAO;
import com.martiply.model.MetaData;
import com.martiply.model.Store;

public class RDSLocateStoresInArea extends BaseWorker{
	
	public static final int MODE = 2;
	private static final float RADIUS = 2.0f;
	private static final int LIMIT = 20;
	
	private double lat, lng;
	private float r;
	private MetaData metaData = new MetaData();

	
	/**
	 * Locates stores in area
	 * @param request
	 * @param out
	 * @throws NumberFormatException
	 */
	public RDSLocateStoresInArea(HttpServletRequest request, PrintWriter out) throws NumberFormatException{
		setOut(out);
	    String latStr = request.getParameter(PARAM_LAT);
	    String lngStr = request.getParameter(PARAM_LNG);
	    String rString = request.getParameter(PARAM_RADIUS);
	    lat = Double.parseDouble(latStr);
	    lng = Double.parseDouble(lngStr);	
	    r = RADIUS;
	    if (rString != null){
	    	try{
	    		r = Float.parseFloat(rString);
	    	}catch (NumberFormatException e){
	    		
	    	}
	    }
	}
	
	@Override
	public void start(ApplicationContext ctx) {		
		final StoreDAO storeDAO = (StoreDAO)ctx.getBean("storeDAO");

    	ArrayList<Store> stores = storeDAO.findAreaStore(LIMIT, r, lat, lng);
    	metaData.setStoreInfos(stores);
    	outJson(metaData);			
	}
	
	

}
