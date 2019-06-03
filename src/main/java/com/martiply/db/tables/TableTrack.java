package com.martiply.db.tables;

public class TableTrack {
	

	public static final String TABLE_NAME = "user_keyword_track";
	
	public static final String UID = TableUser.UID;
	public static final String MILLIS = "millis";
	public static final String LAT = TableStore.LAT;
	public static final String LNG = TableStore.LNG;
	public static final String KEYWORD = "keyword";
	
	public static final String UID_F = TABLE_NAME + "." + UID;
	public static final String MILLIS_F = TABLE_NAME + "." + MILLIS;
	public static final String LAT_F = TABLE_NAME + "." + LAT;
	public static final String LNG_F = TABLE_NAME + "." + LNG;
	public static final String KEYWORD_F = TABLE_NAME + ".";
	
	
}
