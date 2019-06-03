package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

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

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.StandardDAO;
import com.martiply.utils.Rules;

import au.com.bytecode.opencsv.CSVReader;

@WebServlet(name = "MultiUpload", description = "Multiple item upload with csv", urlPatterns = { BaseServlet.SERVLET_CSV_MULTI_UPLOAD })
public class MultiUpload extends BaseServlet{
	private static final long serialVersionUID = 8899082753812674228L;	
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

		if (standardDAO.countItems(ownerId) > Rules.MAX_INVENTORY_ITEMS){
			out.println(String.format("You have reached maximum %d items in your inventory", Rules.MAX_INVENTORY_ITEMS));			
			return;
		};
		
		InputStream stream = null;
		
    	if (request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ) {   	
            try { 
    			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				if (items != null && items.size() == 1){
	                stream = items.get(0).getInputStream();

				} 			
    		} catch (FileUploadException e) {	                	
    			e.printStackTrace();
    			return;
    		}
    	}
    	
    	if (stream == null){
    		out.print("cannot open file");
    		return;
    	}
    	
    	CSVReader csvReader = new CSVReader(new InputStreamReader(stream));
    	V2CsvUpload v2CsvUpload = new V2CsvUpload(csvReader);
    	v2CsvUpload.read(ownerId);
    	
    	String error = v2CsvUpload.error;
    	if (error != null){
    		out.print(error);
    		return;
    	}
    	
    	standardDAO.insert(v2CsvUpload.items, v2CsvUpload.apparels);   	
		out.println("0");				
	}

}
