package com.martiply.model;

public class SocMed {

	public static final String VAL_METHOD_PINTEREST = "pinterest";
	public static final String VAL_METHOD_PATH = "path";
	public static final String VAL_METHOD_FACEBOOK_LIKE = "facebookLike";
	public static final String VAL_METHOD_TUMBLR = "tumblr";
	public static final String VAL_METHOD_FOURSQUARE = "foursquare";
	public static final String VAL_METHOD_FACEBOOK_SHARE = "facebookShare";
	public static final String VAL_METHOD_OFF_APP = "offApp";
	public static final String VAL_METHOD_IN_APP = "inApp";
	
	
	 long cId;
	 String value;
	 String method;
	 double lat;
	 double lng;
	 long ts; 
	 int rank;
	
	public long getTs() {
		return ts;
	}
	
	public void setTs(long ts) {
		this.ts = ts;
	}
	

	
	public String getValue() {
		return value;
	}
	
	public String getMethod() {
		return method;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public void setcId(long cId) {
		this.cId = cId;
	}
	
	
	public long getcId() {
		return cId;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	

}
