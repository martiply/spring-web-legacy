package com.martiply.model.internal;

import java.util.ArrayList;

public class CouponComprehensiveStat {
	
	private ArrayList<CouponDailyStat> catalogDailyStats;
	private float meanBid;
	private float meanRank;
	private int nShares;
	private int scans;
	

	
	public void setMeanBid(float meanBid) {
		this.meanBid = meanBid;
	}
	
	public void setMeanRank(float meanRank) {
		this.meanRank = meanRank;
	}
		
	public ArrayList<CouponDailyStat> getCatalogDailyStats() {
		return catalogDailyStats;
	}

	public void setCatalogDailyStats(ArrayList<CouponDailyStat> catalogDailyStats) {
		this.catalogDailyStats = catalogDailyStats;
	}

	public float getMeanBid() {
		return meanBid;
	}
	
	public float getMeanRank() {
		return meanRank;
	}

	public void setnShares(int nShares) {
		this.nShares = nShares;
	}
	
	public int getnShares() {
		return nShares;
	}
	
	public int getScans() {
		return scans;
	}
	
	public void setScans(int scans) {
		this.scans = scans;
	}
	
}
