package com.martiply.admin.servlets.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.CouponDAO;
import com.martiply.dao.OwnerDAO;
import com.martiply.dao.StandardDAO;
import com.martiply.db.tables.TableCoupon;
import com.martiply.model.Coupon;
import com.martiply.model.Owner;

@WebServlet(name = "Home", urlPatterns = { BaseServlet.SERVLET_HOME })
public class Home extends BaseServlet{
	
	private static final long serialVersionUID = -7717842247648104133L;
	private ApplicationContext ctx;
	private StandardDAO standardDAO;
	private OwnerDAO ownerDAO;
	private CouponDAO couponDAO;
	private ResourceBundle bundle;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		standardDAO = (StandardDAO)ctx.getBean("standardDAO");
		ownerDAO = (OwnerDAO)ctx.getBean("ownerDAO");
		couponDAO = (CouponDAO)ctx.getBean("couponDAO");
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		HttpSession session = request.getSession(false);
		final int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			response.sendRedirect(getServletContext().getContextPath() + SERVLET_LOGIN);
			return;
		}

		bundle = ResourceBundle.getBundle("home", request.getLocale());
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Owner> fowner = executorService.submit(new Callable<Owner>(){
		    public Owner call() throws Exception {
				return ownerDAO.getOwner(ownerId);
		    }
		});		
		Future<Integer> fnitem = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return standardDAO.countItems(ownerId);
			}
		});		
		Future<ArrayList<Coupon>> fCats = executorService.submit(new Callable<ArrayList<Coupon>>() {

			@Override
			public ArrayList<Coupon> call() throws Exception {
				return couponDAO.getAllCoupons(ownerId);
			}
		});
		
		executorService.shutdown();
				
		try {			
			ArrayList<String> errorStrings = new ArrayList<String>();
			for (Coupon coupon : fCats.get()){
				String status = coupon.getStatus();
				if (!status.trim().isEmpty()){
					if (status.contains(TableCoupon.STATUS_MISSING_AT_TWITTER)){
						errorStrings.add(String.format(bundle.getString("PromoError"), coupon.getCpId(), bundle.getString("ErrorTwitterBroken")));
					}else if (status.contains(TableCoupon.STATUS_FUND_SUFFICIENT)){
						errorStrings.add(String.format(bundle.getString("PromoError"), coupon.getCpId(), bundle.getString("ErrorNoMoney")));					
					}else if (status.contains(TableCoupon.STATUS_EXPIRED)){
						errorStrings.add(String.format(bundle.getString("PromoExpired"), coupon.getCpId()));					
					}						
				}
			}				
			request.setAttribute(BaseServlet.ATTR_ITEM_COUNT, (Integer)fnitem.get());
			session.setAttribute(SESSION_CREDIT, ((Owner)fowner.get()).getCredit());	
			request.setAttribute(ATTR_DASHBOARD_TITLE, bundle.getString("Title"));
			request.setAttribute(ATTR_DASHBOARD_SUBTITLE, bundle.getString("Subtitle"));
			request.setAttribute(ATTR_PAGE_SIDE_ITEM, "home");
			request.setAttribute(ATTR_PAGE_CONTENT, "home.jsp");
			request.setAttribute(BaseServlet.ATTR_NOTIFICATIONS, errorStrings);			
			request.getRequestDispatcher("/WEB-INF/jsp/mainContainer.jsp").forward(request, response); 
		} catch (InterruptedException | ExecutionException e) {
		    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  "An unknown error occurred");
		}				
	}

}
