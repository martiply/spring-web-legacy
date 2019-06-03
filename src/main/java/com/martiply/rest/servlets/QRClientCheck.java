package com.martiply.rest.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.martiply.dao.CouponDAO;
import com.martiply.model.MetaData;

public class QRClientCheck extends BaseWorker {
	
	public static final int MODE = 18;
	
	private String email, twT, twS;
	private Long cpId, shareId, scantTs;
	private MetaData metaData = new MetaData();
	
	
	public QRClientCheck(HttpServletRequest request, PrintWriter out) {
		setOut(out);
		this.email = request.getParameter(PARAM_EMAIL);
		this.twT = request.getParameter(PARAM_TWITTER_TOKEN);
		this.twS = request.getParameter(PARAM_TWITTER_SECRET);	
		try{
			this.cpId = Long.parseLong(request.getParameter(PARAM_CPID));
			this.shareId = Long.parseLong(request.getParameter(PARAM_SHAREID));
			this.scantTs = Long.parseLong(request.getParameter(PARAM_SCAN_TS));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(ApplicationContext ctx) {
		if (email==null||twT==null||twS==null||cpId==null||shareId==null||scantTs==null){
			metaData.setStatus(-1);
			metaData.setMessage("Null params");
			outJson(metaData);
			return;
		}
		
		CouponDAO couponDAO = (CouponDAO)ctx.getBean("couponDAO");
		
		Long scanTs = couponDAO.getScanTs(shareId, cpId, email, twT, twS);
		
		if (scanTs == null){
			metaData.setStatus(-1);
			metaData.setMessage("Share entry not found or wrong params");		
		}else if (scanTs > 0L){
			metaData.setStatus(-1);
			metaData.setMessage("already scanned:" + scanTs);			
		}else {
			couponDAO.updateScanAuthorized(shareId, cpId, email, twT, twS, scantTs);
			metaData.setStatus(0);
			metaData.setMessage("New scan registered");			
		}
		
		outJson(metaData);	
	}

}
