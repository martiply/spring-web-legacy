package com.martiply.rest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acra.ReportField;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.dao.AcraDAO;

@WebServlet(name = "Acra", description = "Acra Reporting from Android", urlPatterns = { "/acra" })
public class AcraReport extends HttpServlet{
	
	private static final long serialVersionUID = -6208187274119057242L;
	private ApplicationContext ctx; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
        String reportId = request.getParameter(ReportField.REPORT_ID.name());
        String installId = request.getParameter(ReportField.INSTALLATION_ID.name());
        String appVName = request.getParameter(ReportField.APP_VERSION_NAME.name());
        String appVCode = request.getParameter(ReportField.APP_VERSION_CODE.name());
        String packageName = request.getParameter(ReportField.PACKAGE_NAME.name());
        String phoneModel = request.getParameter(ReportField.PHONE_MODEL.name());
        String brand = request.getParameter(ReportField.BRAND.name());
        String product = request.getParameter(ReportField.PRODUCT.name());
        String androidVersion = request.getParameter(ReportField.ANDROID_VERSION.name());
        String build = request.getParameter(ReportField.BUILD.name());
        String totalMemSize = request.getParameter(ReportField.TOTAL_MEM_SIZE.name());
        String availableMemSize = request.getParameter(ReportField.AVAILABLE_MEM_SIZE.name());
        String stackTrace = request.getParameter(ReportField.STACK_TRACE.name());
        String initialConfig = request.getParameter(ReportField.INITIAL_CONFIGURATION.name());
        String crashConfig = request.getParameter(ReportField.CRASH_CONFIGURATION.name());
        String deviceFeatures = request.getParameter(ReportField.DEVICE_FEATURES.name());
        
        if (reportId == null || reportId.trim().isEmpty() || stackTrace == null || stackTrace.trim().isEmpty()){
        	return;
        }
        

		AcraDAO acraDAO = (AcraDAO) ctx.getBean("acraDAO");
		acraDAO.insert(new Date(), reportId, installId, appVName, appVCode, packageName, phoneModel, brand, product, androidVersion, build, totalMemSize, 
				availableMemSize, stackTrace, initialConfig, crashConfig, deviceFeatures);
			
        
		

	}

}
