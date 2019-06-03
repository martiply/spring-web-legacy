package com.martiply.db.tables;

public class TableUser {

	public static final String SCHEMA = "main";

	public static final String TABLE_NAME = "user";

	public static final String UID = "uid"; //= twitter user Id

	public static final String TWITTER_TOKEN = "twitterToken";
	public static final String TWITTER_SECRET = "twitterSecret";
	public static final String TWITTER_URL = "twitterUrl";
	public static final String URL_IMG_PROFILE = "urlImgProfile";
	public static final String URL_IMG_BANNER_MOBILE_RETINA = "urlImgBannerMobileRetina";
	public static final String TWITTER_NAME = "twitterName";
	public static final String TWITTER_SCREEN_NAME = "twitterScreenName";
	public static final String TWITTER_LOCATION = "twitterLocation";
	public static final String TWITTER_DESCRIPTION = "twitterDescription";
	public static final String TWITTER_LANGUAGE = "twitterLanguage";
	public static final String TWITTER_TZ = "twitterTz";

	public static final String UID_F =  TABLE_NAME + "." +  UID;

	public static final String TWITTER_TOKEN_F = TABLE_NAME + "." + TWITTER_TOKEN;
	public static final String TWITTER_SECRET_F = TABLE_NAME + "." + TWITTER_SECRET;
	public static final String TWITTER_URL_F = TABLE_NAME + "." + TWITTER_URL;
	public static final String URL_IMG_PROFILE_F = TABLE_NAME + "." + URL_IMG_PROFILE;
	public static final String URL_IMG_BANNER_MOBILE_RETINA_F = TABLE_NAME + "." +  URL_IMG_BANNER_MOBILE_RETINA;
	public static final String TWITTER_NAME_F = TABLE_NAME + "." + TWITTER_NAME;
	public static final String TWITTER_SCREEN_NAME_F = TABLE_NAME + "." + TWITTER_SCREEN_NAME;
	public static final String TWITTER_LOCATION_F =  TABLE_NAME + "." + TWITTER_LOCATION;
	public static final String TWITTER_DESCRIPTION_F = TABLE_NAME + "." + TWITTER_DESCRIPTION;
	public static final String TWITTER_LANGUAGE_F = TABLE_NAME + "." + TWITTER_LANGUAGE;
	public static final String TWITTER_TZ_F = TABLE_NAME + "." + TWITTER_TZ;

}
