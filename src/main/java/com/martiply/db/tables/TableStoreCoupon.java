package com.martiply.db.tables;

public class TableStoreCoupon {
	
	public static final String TABLE_NAME = "store_coupon";
	public static final String STORE_ID = TableStore.STORE_ID;
	public static final String CPID = TableCoupon.CPID;
	
	public static final String STORE_ID_F = TABLE_NAME + "." + STORE_ID;
	public static final String CPID_F = TABLE_NAME + "." + CPID;

}
