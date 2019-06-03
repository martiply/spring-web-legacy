package com.martiply.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.acra.ReportField;
import org.springframework.jdbc.core.JdbcTemplate;

public class AcraDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public void insert(Date date, String reportId, String installationId, String appVName, String appVCode, String packageName, String phoneModel, String brand, String product, 
			String androidVersion, String build, String totalMemSize, String availableMemSize, String stackTrace, String initialConfig, String crashConfig, String deviceFeatures){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
				"acra",
				"time",
			    ReportField.REPORT_ID.name(),
			    ReportField.INSTALLATION_ID.name(),
			    ReportField.APP_VERSION_NAME.name(),
			    ReportField.APP_VERSION_CODE.name(),
			    ReportField.PACKAGE_NAME.name(),
			    ReportField.PHONE_MODEL.name(),
			    ReportField.BRAND.name(),
			    ReportField.PRODUCT.name(),
			    ReportField.ANDROID_VERSION.name(),
			    ReportField.BUILD.name(),
			    ReportField.TOTAL_MEM_SIZE.name(),
			    ReportField.AVAILABLE_MEM_SIZE.name(),
			    ReportField.STACK_TRACE.name(),
			    ReportField.INITIAL_CONFIGURATION.name(),
			    ReportField.CRASH_CONFIGURATION.name(),
			    ReportField.DEVICE_FEATURES.name()				
				);
		jdbcTemplate.update(sql, new Object[]{new Date(), reportId, installationId, appVName, appVCode, packageName, phoneModel, brand, product, androidVersion, build, totalMemSize, availableMemSize,
		stackTrace, initialConfig, crashConfig, deviceFeatures});
		
	}
}
