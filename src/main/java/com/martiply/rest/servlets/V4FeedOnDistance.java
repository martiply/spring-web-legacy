package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.StoreDAO;
import com.martiply.db.tables.TableCoupon;
import com.martiply.model.MetaData;
import com.martiply.model.Store;

public class V4FeedOnDistance extends BaseWorker{

	
	public static final int MODE_A = 13; 
	
	private double lat, lng ;
	private int mode, refId;
	private long uid;

private MetaData metaData = new MetaData();
	

	public V4FeedOnDistance(HttpServletRequest request, PrintWriter out, int mode) throws NumberFormatException{
		setOut(out);
	    String latStr = request.getParameter(PARAM_LAT);
	    String lngStr = request.getParameter(PARAM_LNG);
	    String refId = request.getParameter(PARAM_REF_BID_ID);
	    try {
	    	this.uid = Long.parseLong(request.getParameter(PARAM_UID));
	    }catch(NumberFormatException e){
	    	e.printStackTrace();
	    }

	    this.refId = Integer.parseInt(refId);
	    lat = Double.parseDouble(latStr);
	    lng = Double.parseDouble(lngStr);
	    this.mode = mode;	    
	}
	

	@Override
	public void start(ApplicationContext ctx) {
		
		/* In V4, catalog refers to tweet
		 * one store, one tweet
		 * parameters need adjusting: rank of tweet. will be based on value
		 */
		StoreDAO storeDAO = (StoreDAO) ctx.getBean("storeDAO");
		
		
		switch (mode) {
		case MODE_A:
			float baseBid = 0f;
			ArrayList<Store> feedItems;		

//			if (baseCatalogId > 0 ){
//				Catalog catalog = storeDAO.getCatalog(baseCatalogId);
//				baseBid = catalog.getBid();
//				feedItems = feedDAO.getAreaPromos(lat, lng, baseBid, Config.MAX_ROWS_RETURNED);
//			}else{
			feedItems = storeDAO.getClientValidAreaOffers(lat, lng, 3.0f, 30, System.currentTimeMillis() / 1000, TableCoupon.STATUS_OK);
//			}				
			metaData.setStoreInfos(feedItems);	
			outJson(metaData);

			return;
		default:
			break;
		}
		

		
	
	}
}
