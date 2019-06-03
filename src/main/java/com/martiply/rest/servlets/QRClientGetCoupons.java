package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.CouponDAO;
import com.martiply.model.MetaData;

public class QRClientGetCoupons extends BaseWorker{
	
	
	public static final int MODE = 16;
	
	private String email, twT, twS;

	private MetaData metaData = new MetaData();
	
	public QRClientGetCoupons(HttpServletRequest request, PrintWriter out) {
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
			CouponDAO couponDAO = (CouponDAO)ctx.getBean("couponDAO");
			metaData.setCatalogs(couponDAO.getCatalogsFromNdayBeforeNow(email, twT, twS, System.currentTimeMillis() / 1000, 7));
		}
		outJson(metaData);		
	}
	
}
