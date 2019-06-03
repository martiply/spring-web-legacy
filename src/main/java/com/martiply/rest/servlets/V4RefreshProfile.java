package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.UserDAO;
import com.martiply.model.MetaData;
import com.martiply.model.User;
import com.martiply.twitter.Twittery;

public class V4RefreshProfile extends BaseWorker{
	
	public static final int MODE = 14;

	
	private MetaData metaData = new MetaData();
	private long uid; 
	
	public V4RefreshProfile(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		try {
			this.uid = Long.parseLong(request.getParameter(PARAM_UID));				
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void start(ApplicationContext ctx) {
		if (uid == 0 ){
			metaData.setStatus(-1);
			metaData.setMessage("empty param");			
			outJson(metaData);
			return;
		}
		
		UserDAO userDAO = (UserDAO)ctx.getBean("userDAO");
		
		User user = userDAO.getTwitterUser(uid);
		if (user == null ){
			metaData.setStatus(-1);
			metaData.setMessage("user is not registered");			
			outJson(metaData);
			return;
		}
		
		Twittery twittery = new Twittery(user.getTwitterToken(), user.getTwitterSecret());
		twitter4j.User userTwitter = null;
		try {
			userTwitter = twittery.showUser(uid);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		if (userTwitter == null){
			metaData.setStatus(-1);
			metaData.setMessage("get user failed");			
			outJson(metaData);
			return;
		}
			
		userDAO.registerTwitter(uid, user.getTwitterToken(), user.getTwitterSecret(), userTwitter);		
		User jsonUser = userDAO.getTwitterUser(uid);
		metaData.setUser(jsonUser);		
		outJson(metaData);		
	}

}
