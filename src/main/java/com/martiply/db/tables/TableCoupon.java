package com.martiply.db.tables;

public class TableCoupon {

	public static final String SCHEMA = "main";

	public static final String TABLE_NAME = "coupon";
	public static final String CPID = "cpId";
	public static final String END = "end";	
	public static final String CONTENT = "content";
	public static final String TERMS = "terms";
	public static final String STATUS = "status";
	public static final String URL = "url";
	public static final String TYPE = "type";
	public static final String SHARE = "share";
	public static final String LAST_SHARER = "lastSharer";	
	public static final String OWNER_ID = TableOwner.OWNER_ID;	
	public static final String BID = "bid";
	
	
	public static final String STATUS_OK = "ok";
	public static final String STATUS_FUND_SUFFICIENT = "fund_insufficient";
	public static final String STATUS_EXPIRED = "expired";
	public static final String STATUS_MISSING_AT_TWITTER = "missing_at_twitter";
	public static final String STATUS_RESOURCES_DELETED = "resources_deleted";
	
	
	public static final String CPID_F = TABLE_NAME + "." + CPID;
	public static final String END_F = TABLE_NAME + "." + END;	
	public static final String CONTENT_F = TABLE_NAME + "." + CONTENT;
	public static final String TERMS_F = TABLE_NAME + "." + TERMS;
	public static final String STATUS_F= TABLE_NAME + "." + STATUS;
	public static final String URL_F = TABLE_NAME + "." + URL;
	public static final String TYPE_F = TABLE_NAME + "." + TYPE;
	public static final String SHARE_F = TABLE_NAME + "." + SHARE;
	public static final String LAST_SHARER_F = TABLE_NAME + "." + LAST_SHARER;
	
	public static final String OWNER_ID_F = TABLE_NAME + "." + OWNER_ID;
	
	public static final String BID_F = TABLE_NAME + "." + BID;
}
