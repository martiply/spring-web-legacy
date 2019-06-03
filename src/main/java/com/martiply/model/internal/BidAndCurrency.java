package com.martiply.model.internal;

public class BidAndCurrency {

	private String prettyCurrency;
	private String currency;
	private int defaultBid;
	private float incrementBid;
	private String incrementBidString; // to help with jquery spinner which cannot handle trailing zeros

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setDefaultBid(int defaultBid) {
		this.defaultBid = defaultBid;
	}

	public void setIncrementBid(float incrementBid) {
		this.incrementBid = incrementBid;
	}
	
	public float getIncrementBid() {
		return incrementBid;
	}

	public int getDefaultBid() {
		return defaultBid;
	}
	
	public void setIncrementBidString(String incrementBidString) {
		this.incrementBidString = incrementBidString;
	}
	
	public String getIncrementBidString() {
		return incrementBidString;
	}
	
	public void setPrettyCurrency(String prettyCurrency) {
		this.prettyCurrency = prettyCurrency;
	}
	
	public String getPrettyCurrency() {
		return prettyCurrency;
	}


}
