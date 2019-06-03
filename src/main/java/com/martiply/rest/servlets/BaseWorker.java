package com.martiply.rest.servlets;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.martiply.model.MetaData;
import com.martiply.utils.GsonUtils;

public class BaseWorker extends GetParams {	
	String uid;
	
	public PrintWriter out;
	
	
	public void start(ApplicationContext ctx){
		return;		
	};
	
	public void outJson(Object object){
		if (object instanceof MetaData){
			MetaData m = (MetaData)object;
			m.setDurationMs(System.currentTimeMillis() - m.getDurationMs());			
		}
		out.print(GsonUtils.toJson(object));		
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}
		
	public void parseUid(HttpServletRequest request){
		String u = request.getParameter(PARAM_UID);
		if (u != null && !u.trim().isEmpty()){
			uid = u;
		}		
	}
	
	public void failIncomplete(MetaData metaData){
			metaData.setStatus(-1);
			metaData.setMessage("empty param");			
			outJson(metaData);
			return;
	}
	
}
