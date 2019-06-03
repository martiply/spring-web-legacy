package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.OwnerDAO;
import com.martiply.model.MetaData;

public class QRClientLogin extends BaseWorker {
	
	public static final int MODE = 15;
	
	private String email, twT, twS;

	private MetaData metaData = new MetaData();

	
	public QRClientLogin(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		this.email = request.getParameter(PARAM_EMAIL);
		this.twT = request.getParameter(PARAM_TWITTER_TOKEN);
		this.twS = request.getParameter(PARAM_TWITTER_SECRET);	
	}
	
	@Override
	public void start(ApplicationContext ctx) {
		if (email==null||twT==null||twS==null){
			metaData.setStatus(-1);
			metaData.setMessage("Null params");
		}else{
			OwnerDAO ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO");		
			boolean isClientRegistered = ownerDAO.isClientRegistered(email, twT, twS);		
			metaData.setMessage(isClientRegistered ? null: "not registered");
			metaData.setStatus(isClientRegistered ? 0 : -1);
		}
		outJson(metaData);
	}
	
	

}
