package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.StandardDAO;
import com.martiply.dao.UserDAO;
import com.martiply.model.IPP;
import com.martiply.model.MetaData;

public class RDSSearchInArea extends BaseWorker{
	public static final int MODE = 3;
	private static final float SEARCH_RADIUS = 2.0f;
	private static final int LIMIT = 50;
	
	private double lat, lng;
	private String keyword, uid;
	private float r;
	public String er = "|";
	private MetaData metaData = new MetaData();
	
	/**
	 * Searches items given keyword in an area and adds keyword to track table
	 * @param request
	 * @param out
	 * @throws NumberFormatException
	 */
		
	public RDSSearchInArea(HttpServletRequest request, PrintWriter out) throws NumberFormatException {
		setOut(out);
		keyword = request.getParameter(PARAM_SEARCH_BY_KEYWORD);
	    String latStr = request.getParameter(PARAM_LAT);
	    String lngStr = request.getParameter(PARAM_LNG);
	    String rString = request.getParameter(PARAM_RADIUS);
	    uid = request.getParameter(PARAM_UID);
	    lat = Double.parseDouble(latStr);
	    lng = Double.parseDouble(lngStr);
	    r = SEARCH_RADIUS;
	    if (rString != null){
	    	try{
	    		r = Float.parseFloat(rString);
	    	}catch (NumberFormatException e){
	    		
	    	}
	    }
	}
	
	public void start(ApplicationContext ctx){			
		final StandardDAO standardDAO = (StandardDAO)ctx.getBean("standardDAO");
		final UserDAO userDAO = (UserDAO)ctx.getBean("userDAO");
		final long millis = System.currentTimeMillis();
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<ArrayList<IPP>> fi = executorService.submit(new Callable<ArrayList<IPP>>() {

			@Override
			public ArrayList<IPP> call() throws Exception {
				// TODO Auto-generated method stub
				return standardDAO.searchAreaItems(LIMIT, r, lat, lng, keyword);
			}
		});
		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				long uidLong = 0;
				if(uid != null){
					try{
						uidLong = Long.parseLong(uid);
					}catch (NumberFormatException e){}					
				}
				userDAO.insertToKeywordTrack(uidLong, millis, keyword, lat, lng);			
			}
		});
		
		
		executorService.shutdown();
		try {
			metaData.setIpps(fi.get());
		} catch (InterruptedException | ExecutionException e) {
			metaData.setMessage(e.getMessage());
		}
		outJson(metaData);					
	}
	

}
