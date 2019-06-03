package com.martiply.twitter;

import java.io.File;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class Twittery {

	private static final String CONSUMER_KEY = "1J8G6JrXjVmqr2BHkFQI1hr9Y";
	/** Consumer Secret generated when you registered your app at https://dev.twitter.com/apps/ */
	private static final String CONSUMER_SECRET = "bzMYhRWc96EEMWEWPM9ZbtY3yXR7SZmKTLVyzbiLoru4XHJhJ5"; // XXX Encode in your app

	private Twitter mTwitter;
	public TwitterException te;

	/**
	 * Constructor to act on behalf of user
	 * 
	 * @param token
	 *            user's token
	 * @param secret
	 *            user's secret
	 */
	public Twittery(String token, String secret) {
		this.mTwitter = makeClient();
		AccessToken at = new AccessToken(token, secret);
		mTwitter.setOAuthAccessToken(at);
	}

	public static Twitter makeClient() {
		Twitter mTwitter = new TwitterFactory().getInstance();
		mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		return mTwitter;
	}

	public Status tweet(String msg) {
		Status res = null;
		try {
			res = mTwitter.updateStatus(msg);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Status retweet(long originalTweetId) {
		Status res = null;
		try {
			res = mTwitter.retweetStatus(originalTweetId);
		} catch (TwitterException e) {
			te = e;
		}
		return res;

	}

	/**
	 * get a status of (re)tweet (use status.getCurrentUserRetweetId() for the RTID)
	 * 
	 * @param tweetId
	 *            source tweet ID
	 * @return
	 */

	public Status getStatus(long sourceTweetId) {
		Status res = null;
		try {
			res = mTwitter.showStatus(sourceTweetId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean hasRetweeted(long sourceId) {
		boolean res = true;
		try {
			mTwitter.retweetStatus(sourceId);

		} catch (TwitterException e) {
			e.printStackTrace();
			res = false;
		}
		return res;

	}

	public User showUser(long twUid) {
		User res = null;
		try {
			res = mTwitter.showUser(twUid);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public long tweetPic(File file, String message) throws TwitterException  {
        StatusUpdate status = new StatusUpdate(message);
        status.setMedia(file);
        Status newStatus = mTwitter.updateStatus(status);
        return newStatus.getId();
	}
}
