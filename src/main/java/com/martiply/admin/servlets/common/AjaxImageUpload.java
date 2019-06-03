package com.martiply.admin.servlets.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.dao.StoreDAO;
import com.martiply.model.Store;
import com.martiply.utils.ImageUtils;
import com.martiply.utils.S3AdminUtil;
import com.martiply.utils.S3Util;

@WebServlet(name = "AJAX Image Upload", urlPatterns = { BaseServlet.SERVLET_IMAGE_UPLOAD })
public class AjaxImageUpload extends BaseServlet{	
	private static final long serialVersionUID = 3851115944614271614L;
	private StoreDAO storeDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		storeDAO = (StoreDAO)ctx.getBean("storeDAO");
	}
	
	/*
	 request form : ui/<i/s>-<storeId>-<id>
	 Serves only single file upload
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();	  
	    
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);			
		if (ownerId == 0){
			out.println("-1");
			return;
		}
		
	    BufferedImage bi = null;
        AIURequest aiuRequest = null;
		
    	if (request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ) {   	
            try { 
    			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				if (items != null && items.size() == 1){
	                bi = ImageIO.read(items.get(0).getInputStream());
	                aiuRequest = AIURequest.newInstance(items.get(0).getFieldName());
				} 			
    		} catch (FileUploadException e) {	                	
    			e.printStackTrace();
    			return;
    		}
    	}
    	
    	if (bi == null){
    		out.println("Unreadable file");
    		return;
    	}
    	
    	if (aiuRequest == null){
    		out.println("False params");
    		return;
    	}
    	
        if (!ImageUtils.verifySize(bi)){
        	out.println("Size has to be larger than "+ ImageUtils.IMG_MIN_HEIGHT + "px x " + ImageUtils.IMG_MIN_WIDTH + "px");
        	return;
        }
              
        switch (aiuRequest.a){       	
	        case VALUE_STORE : //Upload store pic       	        	
	            Store store = storeDAO.getStore(aiuRequest.storeId, ownerId);
	            if (store == null){
	            	out.print("Permission denied");
	            	return;
	            }
	            
	    		File source = File.createTempFile("s3-temp-img-mainSource", ".jpg");
	    		source.deleteOnExit();
	            ImageIO.write(bi, "jpg", source);	       
	    		
	            File large = File.createTempFile("s3-temp-img-mainLarge", ".jpg");
	            large.deleteOnExit();
	            ImageIO.write(ImageUtils.largeImage(bi), "jpg", large);	       

	            
	            File normal = File.createTempFile("s3-temp-img-mainNormal", ".jpg");
	            normal.deleteOnExit();
	            ImageIO.write(ImageUtils.normalImage(bi), "jpg", normal);	
   	
	            S3AdminUtil.storeFile(source, S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_SOURCE));
	            S3AdminUtil.storeFile(large, S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_LARGE));
	            S3AdminUtil.storeFile(normal, S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_NORMAL));     
		        
		        String img = S3Util.getS3Url(S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_LARGE));
		        out.print(img); // Success !
		        break;
		        
	        case VALUE_INVENTORY:        
	    		source = File.createTempFile("s3-temp-img-mainSource", ".jpg");
	    		source.deleteOnExit();
	            ImageIO.write(bi, "jpg", source);	       
	    		
	            large = File.createTempFile("s3-temp-img-mainLarge", ".jpg");
	            large.deleteOnExit();
	            ImageIO.write(ImageUtils.largeImage(bi), "jpg", large);	       
            
	            normal = File.createTempFile("s3-temp-img-mainNormal", ".jpg");
	            normal.deleteOnExit();
	            ImageIO.write(ImageUtils.normalImage(bi), "jpg", normal);	
	            
    	        File small = File.createTempFile("s3-temp-img-small", ".jpg");
    	        small.deleteOnExit();
    	        ImageIO.write(ImageUtils.smallImage(bi), "jpg", small);	   
    	        
    	        File xsmall = File.createTempFile("s3-temp-img-xsmall", ".jpg");
    	        xsmall.deleteOnExit();
    	        ImageIO.write(ImageUtils.xsmallImage(bi), "jpg", xsmall);	   

    	        S3AdminUtil.storeFile(source, S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_SOURCE));
    	        S3AdminUtil.storeFile(large,  S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_LARGE));
    	        S3AdminUtil.storeFile(normal, S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_NORMAL));  
    	        S3AdminUtil.storeFile(small, S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_SMALL));  
    	        S3AdminUtil.storeFile(xsmall, S3Util.getImageItemKey(aiuRequest.itemId,aiuRequest.which, S3Util.SIZE_XSMALL));  
	        	
		        img = S3Util.getS3Url(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_NORMAL));
		        out.print(img); // Success !
		        break;
		        
	        case VALUE_TEMP : // this is temp file just to assist Promo registration
	    		source = File.createTempFile("s3-temp-img-mainSource", ".jpg");
	    		source.deleteOnExit();
	            ImageIO.write(bi, "jpg", source);	     
	        		            
	            S3AdminUtil.storeFile(source, S3Util.getOwnerTempFileKey(ownerId, aiuRequest.temp));
    	        img = S3Util.getS3Url(S3Util.getOwnerTempFileKey(ownerId, aiuRequest.temp));
    	        out.print(img);
	        }              
	}
	

	public static class AIURequest{
		String a;
		String itemId;
		int storeId;
		int which; 
		String temp;
		
		public static AIURequest newInstance(String r) {
			if (r.startsWith(VALUE_TEMP)){
				AIURequest aiRequest = new AIURequest();
				aiRequest.temp = r;
				aiRequest.a = VALUE_TEMP;
				return aiRequest;
			}
			
			String[] els = r.split("-");
			if (els.length == 3){
				AIURequest aiurRequest = new AIURequest();
				aiurRequest.a = els[0];
				if (els[0].equals(VALUE_STORE)){
					aiurRequest.storeId = Integer.parseInt(els[1]);
				}else{
					aiurRequest.itemId = els[1];
				}
				aiurRequest.which = Integer.parseInt(els[2]);	
				return aiurRequest;
			}else{
				return null;
			}
		}
	}
	
}
