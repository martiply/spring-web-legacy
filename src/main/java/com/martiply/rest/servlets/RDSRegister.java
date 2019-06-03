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

import com.martiply.dao.StoreDAO;
import com.martiply.dao.UserDAO;
import com.martiply.model.MetaData;
import com.martiply.model.Store;
import com.martiply.twitter.Twittery;

import twitter4j.User;

public class RDSRegister extends BaseWorker{
	
	public static final int MODE = 9;
		
	private MetaData metaData = new MetaData();
	private String twT, twS;
	private long uid; 
	
	
	public RDSRegister(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		try {
			this.uid = Long.parseLong(request.getParameter(PARAM_UID));			
			this.twT = request.getParameter(PARAM_TWITTER_TOKEN);
			this.twS = request.getParameter(PARAM_TWITTER_SECRET);		
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void start(ApplicationContext ctx) {
							
		if (uid == 0 || twT == null || twS == null || twT.trim().isEmpty() || twS.trim().isEmpty()  ){
			metaData.setStatus(-1);
			metaData.setMessage("empty param");			
			outJson(metaData);
			return;
		}
		
		final UserDAO userDAO = (UserDAO)ctx.getBean("userDAO");
		final StoreDAO storeDAO = (StoreDAO)ctx.getBean("storeDAO");
		
		Twittery twittery = new Twittery(twT, twS);
		final User user = twittery.showUser(uid);
	
		if (user == null){
			metaData.setStatus(-1);
			metaData.setMessage("get user failed");			
			outJson(metaData);
			return;
		}
		
	
		userDAO.registerTwitter(uid, twT, twS, user);				
   	
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<com.martiply.model.User> fu = executorService.submit(new Callable<com.martiply.model.User>() {

			@Override
			public com.martiply.model.User call() throws Exception {
				return  userDAO.getTwitterUser(uid);
			}
		});
		
		Future<ArrayList<Store>> fs = executorService.submit(new Callable<ArrayList<Store>>() {

			@Override
			public ArrayList<Store> call() throws Exception {
				return storeDAO.syncRetweetHistory(uid, System.currentTimeMillis() / 1000);
			}
		});
		
		executorService.shutdown();

		
		try {
			metaData.setStoreInfos(fs.get());
			metaData.setUser(fu.get());		
		} catch (InterruptedException | ExecutionException e) {
			metaData.setMessage(e.getMessage());
		}
		outJson(metaData);
	}

	
	
}
