package com.martiply.model.internal;

public class CouponDailyStat {
	
	
	private int nShares;
	private long midnightTimestamp;
	
	public long getMidnightTimestamp() {
		return midnightTimestamp;
	}
	
	public int getnShares() {
		return nShares;
	}
	
	public void setMidnightTimestamp(long midnightTimestamp) {
		this.midnightTimestamp = midnightTimestamp;
	}
	
	public void setnShares(int nShares) {
		this.nShares = nShares;
	}
	
	public void addOneShare(){
		nShares++;
		
	}
}
