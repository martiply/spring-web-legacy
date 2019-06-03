package com.martiply.model;

import java.util.ArrayList;

public class TweetStatus {
	private long retweetId; // if -1 than not retweeted
	private ArrayList<User> sharers;
	private int shareCount;


	public void setSharers(ArrayList<User> sharers) {
		this.sharers = sharers;
	}

	public ArrayList<User> getSharers() {
		return sharers;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setRetweetId(long retweetId) {
		this.retweetId = retweetId;
	}

	public long getRetweetId() {
		return retweetId;
	}

}
