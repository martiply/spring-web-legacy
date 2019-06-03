package com.martiply.rest.servlets;

import com.martiply.db.tables.TableCoupon;
import com.martiply.db.tables.TableShareHistory;
import com.martiply.db.tables.TableStore;

public class GetParams {
	
	public static final String PARAM_LAT = TableStore.LAT;
	public static final String PARAM_LNG = TableStore.LNG;
	public static final String PARAM_CPID = TableCoupon.CPID;
	public static final String PARAM_STORE_ID = TableStore.STORE_ID;
	public static final String PARAM_RANK = TableShareHistory.RANK; // rank of promo in home
//	public static final String PARAM_BID_ID = "bidId"; // use this to refer to the id of catalog whose bid is the price 
	public static final String PARAM_REF_BID_ID = "refId";
	public static final String PARAM_RADIUS = "r";
	public static final String PARAM_EMAIL = "email";
	
	public static final String PARAM_SEARCH_BY_KEYWORD = "keyword";
	public static final String PARAM_MODE = "mode";
	public static final String PARAM_KEYWORD = "keyword";

	public static final String PARAM_UID = "uid";
	public static final String PARAM_TWITTER_TOKEN = "twT";
	public static final String PARAM_TWITTER_SECRET = "twS";
	
	public static final String PARAM_SHAREID = "shareId";
	public static final String PARAM_SCAN_TS = "scanTs";
	


}
