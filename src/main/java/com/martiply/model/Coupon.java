package com.martiply.model;

public class Coupon {

	private long cpId;

	private long endSec;
	private String content;
	private String terms;
	private String lastSharer;
	private float bid;	
	private int[] sharers;
	private int numShares;
	private String status;

	public float getBid() {
		return bid;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}

	
	public int[] getSharers() {
		return sharers;
	}
	
	public void setSharers(int[] sharers) {
		this.sharers = sharers;
	}
	
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
	
	public int getNumShares() {
		return numShares;
	}
			
			

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	
	public long getCpId() {
		return cpId;
	}

	public void setCpId(long cpId) {
		this.cpId = cpId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setEndSec(long endSec) {
		this.endSec = endSec;
	}
	
	public long getEndSec() {
		return endSec;
	}


	public String getLastSharer() {
		return lastSharer;
	}
	
	public void setLastSharer(String lastSharer) {
		this.lastSharer = lastSharer;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}


}
