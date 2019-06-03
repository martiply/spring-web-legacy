package com.martiply.admin.servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.dao.OwnerDAO;
import com.martiply.db.tables.TableOwner;
import com.martiply.model.internal.Credentials;
import com.martiply.twitter.Twittery;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

@WebServlet(name = "Login", description = "Login by twitter", urlPatterns = { BaseServlet.SERVLET_LOGIN })
public class Login extends BaseServlet {
	private static final long serialVersionUID = 8068396355837667278L;
	private ApplicationContext ctx;
	private OwnerDAO ownerDAO;
	private ResourceBundle bundle;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO"); 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null){
			String error = (String) session.getAttribute(BaseServlet.SESSION_CALLBACK_ERROR);
			if (error != null){
				request.setAttribute(ATTR_ERROR, error);
				session.invalidate();
			}else{
				final int ownerId = getOwnerSessionActive(session);
				if (ownerId != 0) {
					response.sendRedirect(getServletContext().getContextPath() + SERVLET_HOME);
					return;
				}
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response); 

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bundle = ResourceBundle.getBundle("login", request.getLocale());
	    PrintWriter out = response.getWriter();	  
		response.setContentType("text/plain");
		
		String email = request.getParameter(TableOwner.EMAIL);
		Credentials cred = ownerDAO.getCredentials(email);
		if (cred == null){
			out.println(bundle.getString("Login.EmailNotRegistered"));
			return;	
		}	
		Twitter twitter = Twittery.makeClient();		
		HttpSession session = request.getSession(true);		 		
		try {
			StringBuffer callbackURL = request.getRequestURL();
			callbackURL.replace(callbackURL.lastIndexOf("/"), callbackURL.length(), "").append(SERVLET_TWITTER_CALLBACK);
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
	        synchronized (session) {
	            session.setAttribute(BaseServlet.SESSION_TEMPORARY_CREDENTIALS, cred);
	            session.setAttribute(BaseServlet.SESSION_REQUEST_TOKEN, requestToken);
			} 
			out.println(requestToken.getAuthorizationURL());	     	    
		}catch(TwitterException e){
			out.println(bundle.getString("Login.TwitterVerficationFailed"));
			session.invalidate();
			e.printStackTrace();
		}
	}
}
