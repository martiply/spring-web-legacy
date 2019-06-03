package com.martiply.model;

public class User {

	//https://dev.twitter.com/docs/user-profile-images-and-banners#banners
	public static final String PREFIX_IMG_NORMAL = "_normal";
	public static final String PREFIX_IMG_BIGGER = "_bigger";
	public static final String PREFIX_IMG_MINI = "_mini";
	
	public static final String PREFIX_IMG_WEB = "/web";
	public static final String PREFIX_IMG_MOBILE = "/mobile";
	public static final String PREFIX_IMG_MOBILE_RETINA = "/mobile_retina";
	
	 long uid;
	 String twitterToken;
	 String twitterSecret;
	 String twitterUrl;
	 String urlImgProfile;
	 String urlImgBanner;
	 String twitterName;
	 String twitterScreenName;
	 String twitterLocation;
	 String twitterDescription;
	 String twitterLanguage;
	 String twitterTz;

	
	
	public static String getProfileImgSize(String urlImgProfile, String sizePrefix){
		int i = urlImgProfile.lastIndexOf('.');
		return new StringBuilder(urlImgProfile).insert(i, sizePrefix).toString();	
	}
	
	
	public String getTwitterDescription() {
		return twitterDescription;
	}

	public String getTwitterLanguage() {
		return twitterLanguage;
	}

	public String getTwitterLocation() {
		return twitterLocation;
	}

	public String getTwitterName() {
		return twitterName;
	}

	public String getTwitterScreenName() {
		return twitterScreenName;
	}

	public String getTwitterTz() {
		return twitterTz;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public String getUrlImgBanner() {
		return urlImgBanner;
	}

	public String getUrlImgProfile() {
		return urlImgProfile;
	}

	public void setTwitterDescription(String twitterDescription) {
		this.twitterDescription = twitterDescription;
	}

	public void setTwitterLanguage(String twitterLanguage) {
		this.twitterLanguage = twitterLanguage;
	}

	public void setTwitterLocation(String twitterLocation) {
		this.twitterLocation = twitterLocation;
	}

	public void setTwitterName(String twitterName) {
		this.twitterName = twitterName;
	}

	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}

	public void setTwitterTz(String twitterTz) {
		this.twitterTz = twitterTz;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public void setUrlImgBanner(String urlImgBanner) {
		this.urlImgBanner = urlImgBanner;
	}

	public void setUrlImgProfile(String urlImgProfile) {
		this.urlImgProfile = urlImgProfile;
	}

	public void setTwitterSecret(String twitterSecret) {
		this.twitterSecret = twitterSecret;
	}

	public void setTwitterToken(String twitterToken) {
		this.twitterToken = twitterToken;
	}

	public long getUid() {
		return uid;
	}
	
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public String getTwitterSecret() {
		return twitterSecret;
	}

	public String getTwitterToken() {
		return twitterToken;
	}



	
}
