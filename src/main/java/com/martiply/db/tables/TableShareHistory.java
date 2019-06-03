package com.martiply.db.tables;

public class TableShareHistory {

	public static final String TABLE_NAME = "share_history";
	
	public static final String PLATFORM = "platform";
	public static final String SHARE_ID = "shareId";	
	public static final String CPID = "cpId";
	public static final String LAT = TableStore.LAT;
	public static final String LNG = TableStore.LNG;	
	public static final String UID = TableUser.UID; 
	public static final String TS = "ts";	
	public static final String RATE = "rate";
	public static final String RANK = "rank";	
	public static final String SCAN_TS ="scanTs";
	public static final String VALUE = "value";
	public static final String METHOD = "method";
	public static final String STATUS = "status";
	
	public static final String PLATFORM_F = TABLE_NAME + "." + PLATFORM;
	public static final String SHARE_ID_F = TABLE_NAME + "." + SHARE_ID;	
	public static final String CPID_F = TABLE_NAME + "." + CPID;
	public static final String LAT_F = TABLE_NAME + "." + LAT;
	public static final String LNG_F = TABLE_NAME + "." + LNG;	
	public static final String UID_F = TABLE_NAME + "." + UID; 
	public static final String TS_F = TABLE_NAME + "." + TS;	
	public static final String RATE_F = TABLE_NAME + "." +  RATE;
	public static final String RANK_F = TABLE_NAME + "." + RANK;	
	public static final String SCAN_TS_F = TABLE_NAME + "." + SCAN_TS;
	public static final String VALUE_F = TABLE_NAME + "." + VALUE;
	public static final String METHOD_F= TABLE_NAME + "." + METHOD;
	public static final String STATUS_F = TABLE_NAME + "." + STATUS;
	
	
	public static final String  STATUS_UNSHARED = "unshared";
	public static final String  STATUS_SHARED = "shared";


}
