package com.martiply.rest.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.model.MetaData;


@WebServlet(name = "PublicAPI", description = "Martiply Public API", urlPatterns = { "/q" })
public class Public extends HttpServlet{

	private static final long serialVersionUID = -406666909781294926L;
	public static final String MODE = "mode";
	private ApplicationContext ctx;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
	    PrintWriter out = response.getWriter();	    
	    
    	String modeParam = request.getParameter(MODE);
    	if (modeParam == null){
    		return;
    	}
    	int mode = Integer.parseInt(modeParam);
    	
    	try{
    	switch (mode){
    	    	
    	case RDSLocateStoresInArea.MODE : new RDSLocateStoresInArea(request, out).start(ctx);break; // http://localhost/AWSPlantoshopApi/q?mode=2&lat=-6.893993&lng=107.587126 
    	case RDSSearchInArea.MODE : new RDSSearchInArea(request, out).start(ctx);break; // http://localhost:8080/martiply/q?mode=3&lat=-6.906917&lng=107.615815&keyword=matcha&uid=332666248
    	case RDSInStoreSearch.MODE: new RDSInStoreSearch(request, out).start(ctx);break; // http://localhost/AWSPlantoshopApi/q?mode=6&storeId=10007&keyword=matcha
    	case RDSInStorePromoItem.MODE : new RDSInStorePromoItem(request, out).start(ctx);break; // http://localhost/AWSPlantoshopApi/q?mode=7&storeId=3 //unused
    	case RDSRegister.MODE : new RDSRegister(request, out).start(ctx);break; // http://localhost/AWSPlantoshopApi/q?mode=9&uid=2401765076&twT=2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK&twS=siT8FPMwde6VHr5wL4y1ANbfy17JLRKDPolx6BsF4g7b1
    	case V4Retweet.MODE : new V4Retweet(request, out).start(ctx);break; // http://localhost/AWSPlantoshopApi/q?mode=10&cpId=597619989487624193&uid=2401765076&twT=2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK&lat=-6.906917&lng=107.615815&refId=597619989487624193&rank=2
    	case StoreInfoTweetStatus.MODE : new StoreInfoTweetStatus(request, out).start(ctx);break; //http://localhost/AWSPlantoshopApi/q?mode=12&storeId=4&uid=215911927
    	case V4FeedOnDistance.MODE_A : new V4FeedOnDistance(request, out, V4FeedOnDistance.MODE_A).start(ctx); break;  // http://localhost/AWSPlantoshopApi/q?mode=13&lat=-6.906917&lng=107.615815&uid=2401765076&refId=7
    	case V4RefreshProfile.MODE : new V4RefreshProfile(request, out).start(ctx);break; // http://localhost:8080/martiply/q?mode=14&uid=2401765076
    	case QRClientLogin.MODE: new QRClientLogin(request, out).start(ctx);break; // http://localhost:8080/AWS/q?mode=15&twS=ZVdl4X93AsrbEzFp0VcPSRsUqhk7tuJPO1WCuDTdEwDZj&twT=2401765076-7XcNShOFwPeI2nhkPC2kY9xxh3ev6UnC2jPyWg4&email=someemail%40email.com
    	case QRClientGetCoupons.MODE: new QRClientGetCoupons(request, out).start(ctx);break; //q?mode=16&twS=siT8FPMwde6VHr5wL4y1ANbfy17JLRKDPolx6BsF4g7b1&twT=2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK&email=someemail@email.com
    	case QRClientStats.MODE: new QRClientStats(request, out).start(ctx);break; //q?mode=17&twS=siT8FPMwde6VHr5wL4y1ANbfy17JLRKDPolx6BsF4g7b1&twT=2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK&email=someemail@email.com&cpId=597619989487624193
    	case QRClientCheck.MODE: new QRClientCheck(request, out).start(ctx);break; //q?mode=18&scanTs=1431884484&cpId=597619989487624193&shareId=645843956119416832&twS=siT8FPMwde6VHr5wL4y1ANbfy17JLRKDPolx6BsF4g7b1&twT=2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK&email=someemail%40email.com
    	
    	default : MetaData metaData = new MetaData();
    	          metaData.setMessage("Unknown mode");
    	          out.print(metaData);
    	}
    	}catch (NumberFormatException e){
    		e.printStackTrace();
    		
    	}
	}
	
	
	
}
