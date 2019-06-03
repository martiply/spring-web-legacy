package com.martiply.admin.servlets.common;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.dao.DefaultBidDAO;
import com.martiply.dao.OwnerDAO;
import com.martiply.dao.UserDAO;
import com.martiply.db.tables.TableOwner;
import com.martiply.model.Owner;
import com.martiply.model.User;
import com.martiply.model.internal.BidAndCurrency;
import com.martiply.model.internal.Credentials;
import com.martiply.twitter.Twittery;
import com.martiply.utils.MyStringUtils;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@WebServlet(name = "TwitterCallback", urlPatterns = { BaseServlet.SERVLET_TWITTER_CALLBACK })
public class Callback extends BaseServlet {

	private static final long serialVersionUID = 5465991170234755214L;
	private ApplicationContext ctx;
	private OwnerDAO ownerDAO;
	private UserDAO userDAO;
	private DefaultBidDAO defaultBidDAO;

	private ResourceBundle bundle;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ownerDAO = (OwnerDAO) ctx.getBean("ownerDAO");
		userDAO = (UserDAO) ctx.getBean("userDAO");
		defaultBidDAO = (DefaultBidDAO) ctx.getBean("defaultBidDAO"); 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bundle = ResourceBundle.getBundle("login", request.getLocale());

		HttpSession session = request.getSession(false);
		if (session == null) {
			// time out
			HttpSession session2 = request.getSession();
			returnToLogin(bundle.getString("Callback.Timeout"), session2, response);
			return;
		}

		Credentials cred = (Credentials) session.getAttribute(BaseServlet.SESSION_TEMPORARY_CREDENTIALS);
		RequestToken token = (RequestToken) session.getAttribute(BaseServlet.SESSION_REQUEST_TOKEN);
		final String oauthVerifier = request.getParameter("oauth_verifier");

		if (cred == null || token == null) {
			returnToLogin(bundle.getString("Callback.TwitterLoginFailed"), session, response);
			return;
		}

		if (oauthVerifier == null || oauthVerifier.trim().isEmpty()) {
			returnToLogin(bundle.getString("Callback.TwitterProblem"), session, response);
			return;
		}

		Twitter twitter = Twittery.makeClient();
		AccessToken at = null;
		try {
			at = twitter.getOAuthAccessToken(token, oauthVerifier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (at == null) {
			returnToLogin(bundle.getString("Callback.TwitterProblem"), session, response);
			return;
		}

		final Owner owner = cred.getOwner();
		User user = cred.getUser();
		final long uid = at.getUserId();
		final String twT = at.getToken();
		final String twS = at.getTokenSecret();

		if (owner.getUid() == 0) { // new user
			// new user or maybe twitter is changing his token after invalidating-validating the app
			Twittery twittery = new Twittery(at.getToken(), at.getTokenSecret());
			twitter4j.User userTw = null;
			try {
				userTw = twittery.showUser(at.getUserId());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			if (userTw == null){
				returnToLogin(bundle.getString("Callback.TwitterProblem"), session, response);
				return;					
			}
			
			owner.setUid(uid);

			ExecutorService executorService = Executors.newFixedThreadPool(2);
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					ownerDAO.updateUid(owner);
				}
			});

			executorService.execute(new RunnableRegisterTwitter(uid, twT, twS, userTw, userDAO));
			executorService.shutdown();
		}else if (owner.getUid() != at.getUserId() || !user.getTwitterToken().equals(twT) || !user.getTwitterSecret().equals(twS)){ // token mismatch
			returnToLogin(bundle.getString("Callback.WrongToken"), session, response);
			return;					
		}

		session.invalidate();

		session = request.getSession();
		BidAndCurrency bidAndCurrency = defaultBidDAO.getDefaultBid(owner.getCurrency());
		bidAndCurrency.setPrettyCurrency(MyStringUtils.makePrettyCurrency(bidAndCurrency.getCurrency()));
		bidAndCurrency.setIncrementBidString(new java.text.DecimalFormat("#").format(bidAndCurrency.getIncrementBid()));

		synchronized (session) {
			session.setAttribute(TableOwner.OWNER_ID, owner.getOwnerId());
			session.setAttribute(SESSION_CURRENCY, bidAndCurrency.getCurrency());
			session.setAttribute(SESSION_PRETTY_CURRENCY, bidAndCurrency.getPrettyCurrency());
			session.setAttribute(SESSION_DEFAULT_BID, bidAndCurrency.getDefaultBid());
			session.setAttribute(SESSION_BID_INCREMENT, bidAndCurrency.getIncrementBid());
			session.setAttribute(SESSION_BID_INCREMENT_STRING, bidAndCurrency.getIncrementBidString());
//			Debug.overrideSession(session);
			session.setMaxInactiveInterval(60 * 60 * 5);
		}
		response.sendRedirect(getServletContext().getContextPath() + SERVLET_HOME);
	}
	
	private static class RunnableRegisterTwitter implements Runnable {
		long uid;
		String twT, twS;
		twitter4j.User userTw;
		UserDAO userDAO;
		
		public RunnableRegisterTwitter(long uid, String twT, String twS, twitter4j.User userTw, UserDAO userDAO) {
			this.uid = uid;
			this.twS = twS;
			this.twT = twT;
			this.userTw = userTw;
			this.userDAO = userDAO;
		}
		
		@Override
		public void run() {
			userDAO.registerTwitter(uid, twT, twS, userTw);			
		}		
	}
	

	private void returnToLogin(String error, HttpSession session, HttpServletResponse response) throws IOException{
		session.setAttribute(BaseServlet.SESSION_CALLBACK_ERROR, error);
		response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
	}
}
