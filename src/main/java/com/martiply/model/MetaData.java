package com.martiply.model;

import java.util.ArrayList;

import com.martiply.model.internal.CouponComprehensiveStat;

public class MetaData extends BaseMetaData {

	private String apiName = "mbcu";
	private long durationMs;
	private String message;

	private ArrayList<Store> storeInfos; // feed, history

	private ArrayList<IPP> ipps;
	private ArrayList<BasicItem> items;
	private ArrayList<Coupon> catalogs;
	private Store storeInfo;
	private TweetStatus tweetStatus;
	private User user;
	

	private CouponComprehensiveStat catalogComprehensiveStat;
	
	
	public MetaData() {
		durationMs = System.currentTimeMillis();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Store getStoreInfo() {
		return storeInfo;
	}

	public TweetStatus getTweetStatus() {
		return tweetStatus;
	}

	public void setTweetStatus(TweetStatus tweetStatus) {
		this.tweetStatus = tweetStatus;
	}

	public void setStoreInfo(Store storeInfo) {
		this.storeInfo = storeInfo;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiName() {
		return apiName;
	}

	public ArrayList<IPP> getIpps() {
		return ipps;
	}

	public void setIpps(ArrayList<IPP> ipps) {
		this.ipps = ipps;
	}

	public void setStoreInfos(ArrayList<Store> storeInfos) {
		this.storeInfos = storeInfos;
	}

	public ArrayList<Store> getStoreInfos() {
		return storeInfos;
	}

	public void setCatalogs(ArrayList<Coupon> catalogs) {
		this.catalogs = catalogs;
	}

	public ArrayList<Coupon> getCatalogs() {
		return catalogs;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setDurationMs(long durationMs) {
		this.durationMs = durationMs;
	}

	public long getDurationMs() {
		return durationMs;
	}

	public void setItems(ArrayList<BasicItem> items) {
		this.items = items;
	}
	
	public ArrayList<BasicItem> getItems() {
		return items;
	}
	
	
	public void setCatalogComprehensiveStat(CouponComprehensiveStat catalogComprehensiveStat) {
		this.catalogComprehensiveStat = catalogComprehensiveStat;
	}
	
	public CouponComprehensiveStat getCatalogComprehensiveStat() {
		return catalogComprehensiveStat;
	}
}
