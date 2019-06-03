package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.CouponDAO;
import com.martiply.model.MetaData;

public class QRClientStats extends BaseWorker{
	
	public static final int MODE = 17;
	
	private String email, twT, twS;
	private Long cpId;
	private MetaData metaData = new MetaData();

	
	public QRClientStats(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		this.email = request.getParameter(PARAM_EMAIL);
		this.twT = request.getParameter(PARAM_TWITTER_TOKEN);
		this.twS = request.getParameter(PARAM_TWITTER_SECRET);		
		try {
			this.cpId = Long.parseLong(request.getParameter(PARAM_CPID));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
	}

	@Override
	public void start(ApplicationContext ctx) {
		if (email==null||twT==null||twS==null||cpId==null){
			metaData.setStatus(-1);
			metaData.setMessage("Null params");
		}else{
			CouponDAO couponDAO = (CouponDAO)ctx.getBean("couponDAO");
			metaData.setCatalogComprehensiveStat(couponDAO.getComprehensiveStatAuthorized(cpId, 10, email, twT, twS));
		}
		outJson(metaData);
	}
	
}
