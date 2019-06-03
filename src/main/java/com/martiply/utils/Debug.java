package com.martiply.utils;

import javax.servlet.http.HttpSession;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.db.tables.TableOwner;

public class Debug {

	
	public static void overrideSession(HttpSession session){
		session.setAttribute(TableOwner.OWNER_ID, 10000);
		session.setAttribute(BaseServlet.SESSION_CURRENCY, "IDR");
		session.setAttribute(BaseServlet.SESSION_PRETTY_CURRENCY, "Rp.");
		session.setAttribute(BaseServlet.SESSION_DEFAULT_BID, 1000);
		session.setAttribute(BaseServlet.SESSION_BID_INCREMENT, 200);
		session.setAttribute(BaseServlet.SESSION_BID_INCREMENT_STRING, new java.text.DecimalFormat("#").format(200));		
	}
}
