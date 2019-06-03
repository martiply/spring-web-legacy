package com.martiply.model;

public class OnlineInfo extends BasicItem {

	private String url;
	private int shipping;
	private String shippingConditions;  //above 5000 yen free shipping

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setShippingConditions(String shippingConditions) {
		this.shippingConditions = shippingConditions;
	}
	
	public String getShippingConditions() {
		return shippingConditions;
	}
	
	public int getShipping() {
		return shipping;
	}
	

	
	
	
}
