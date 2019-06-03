package com.martiply.admin.quartz;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;

import com.martiply.dao.CouponDAO;
import com.martiply.db.tables.TableCoupon;
import com.martiply.model.Coupon;
import com.martiply.utils.Flow;

public class CleanupTask  {

	public void process(){
		ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
		DateTime start = new DateTime();
		
		System.out.println("STARTING CLEAN UP TASK at " + start.toString());
		
		CouponDAO couponDAO = (CouponDAO)appCtx.getBean("couponDAO");
		
		couponDAO.markAllExpires();
		
		ArrayList<Coupon> exs = couponDAO.getDaysExpired(10);
		
		for (Coupon ex: exs){
			Flow.deleteCatalogResources(ex.getCpId());
			couponDAO.updateStatus(ex, TableCoupon.STATUS_RESOURCES_DELETED);
		}
		DateTime end = new DateTime();
		System.out.println("STARTING CLEAN UP TASK at " + end.toString());
		
	}
	
}
