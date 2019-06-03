package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.StandardDAO;
import com.martiply.model.BasicItem;
import com.martiply.utils.S3AdminUtil;
import com.martiply.utils.S3Util;

@WebServlet(name = "Item Delete", description = "Delete item from inventory", urlPatterns = { BaseServlet.SERVLET_ITEM_DELETE })
public class DeleteItem extends BaseServlet{
	private static final long serialVersionUID = 8623233986644058888L;
	private StandardDAO standardDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		standardDAO = (StandardDAO)ctx.getBean("standardDAO");
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
		
		String[] a = request.getParameterValues(PARAM_A_ARRAY);
		
		final ArrayList<BasicItem> items = new ArrayList<>();
		final ArrayList<KeyVersion> s3keys = new ArrayList<>();
		for (String id : a){
			BasicItem item = new BasicItem();
			item.setOwnerId(ownerId);
			item.setId(id);
			items.add(item);
			addAllImageKeys(s3keys, id);
		}
		
		ExecutorService executorService =  Executors.newFixedThreadPool(2);
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				standardDAO.delete(items);
			}
		});
		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				S3AdminUtil.deleteObjects(s3keys);
			}
		});

		
    	executorService.shutdown();
    	try {
    		executorService.awaitTermination(30, TimeUnit.SECONDS);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
		}
		
		out.println("0");
		
	}
	
	private void addAllImageKeys(ArrayList<KeyVersion> s3keys, String id){
		for (int i = 0; i < 8; i++){
			s3keys.add(new KeyVersion(S3Util.getImageItemKey(id, i, S3Util.SIZE_XSMALL)));
			s3keys.add(new KeyVersion(S3Util.getImageItemKey(id, i, S3Util.SIZE_SMALL)));
			s3keys.add(new KeyVersion(S3Util.getImageItemKey(id, i, S3Util.SIZE_NORMAL)));
			s3keys.add(new KeyVersion(S3Util.getImageItemKey(id, i, S3Util.SIZE_LARGE)));
			s3keys.add(new KeyVersion(S3Util.getImageItemKey(id, i, S3Util.SIZE_SOURCE)));	
		}
		
		s3keys.add(new KeyVersion(S3Util.makeImageFolderKey(id)));
		
	}
	

}
