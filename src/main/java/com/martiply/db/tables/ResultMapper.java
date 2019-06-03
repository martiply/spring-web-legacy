package com.martiply.db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.martiply.model.ApparelExtension;
import com.martiply.model.BasicItem;
import com.martiply.model.Coupon;
import com.martiply.model.Owner;
import com.martiply.model.Store;
import com.martiply.model.User;
import com.martiply.model.internal.BidAndCurrency;

public class ResultMapper {

	public static Store buildStore(ResultSet rs, boolean isBare) throws SQLException {
		Store store = new Store();
		if (!isBare){
			store.setStoreId(rs.getInt(TableStore.STORE_ID_F));
			store.setName(rs.getString(TableStore.NAME_F));
			store.setZip(rs.getString(TableStore.ZIP_F));
			store.setAddress(rs.getString(TableStore.ADDRESS_F));
			store.setOpen(rs.getString(TableStore.OPEN_F));
			store.setClose(rs.getString(TableStore.CLOSE_F));
			store.setPhone(rs.getString(TableStore.PHONE_F));
			store.setLat(rs.getDouble(TableStore.LAT_F));
			store.setLng(rs.getDouble(TableStore.LNG_F));
			store.setTag(rs.getString(TableStore.TAG_F));
			store.setCurrency(rs.getString(TableStore.CURRENCY_F));
			store.setStory(rs.getString(TableStore.STORY_F));
			store.setTz(rs.getInt(TableStore.TZ_F));
		}else{
			store.setStoreId(rs.getInt( TableStore.STORE_ID));
			store.setName(rs.getString( TableStore.NAME));
			store.setZip(rs.getString( TableStore.ZIP));
			store.setAddress(rs.getString( TableStore.ADDRESS));
			store.setOpen(rs.getString( TableStore.OPEN));
			store.setClose(rs.getString( TableStore.CLOSE));
			store.setPhone(rs.getString( TableStore.PHONE));
			store.setLat(rs.getDouble( TableStore.LAT));
			store.setLng(rs.getDouble( TableStore.LNG));
			store.setTag(rs.getString( TableStore.TAG));
			store.setCurrency(rs.getString( TableStore.CURRENCY));
			store.setStory(rs.getString( TableStore.STORY));
//			store.setTweetId(rs.getLong( TableStore.TWEET_ID));
			store.setTz(rs.getInt( TableStore.TZ));
		}
		return store;
	}
	


	public static Store buildStoreAndCatalog(ResultSet rs) throws SQLException {
		Store store = buildStore(rs, false);
		Coupon coupon = buildCoupon(rs);
		store.setCoupon(coupon);
		return store;
	}

	public static Coupon buildCoupon(ResultSet rs) throws SQLException {
		long tweetId = rs.getLong(TableCoupon.CPID_F);
		if (tweetId > 0) {
			Coupon coupon = new Coupon();
			coupon.setCpId(tweetId);
			coupon.setContent(rs.getString(TableCoupon.CONTENT_F));
			coupon.setTerms(rs.getString(TableCoupon.TERMS_F));
			coupon.setEndSec(rs.getLong(TableCoupon.END_F));
			coupon.setBid(rs.getFloat(TableCoupon.BID_F));
			coupon.setLastSharer(rs.getString(TableCoupon.LAST_SHARER_F));
			coupon.setNumShares(rs.getInt(TableCoupon.SHARE_F));
			coupon.setStatus(rs.getString(TableCoupon.STATUS_F));
			return coupon;
		}
		return null;

	}

	public static Owner buildOwner(ResultSet rs) throws SQLException {
		Owner owner = new Owner();
		owner.setOwnerId(rs.getInt(TableOwner.OWNER_ID_F));
		owner.setEmail(rs.getString(TableOwner.EMAIL_F));
		owner.setName(rs.getString(TableOwner.NAME_F));
		owner.setCredit(rs.getFloat(TableOwner.CREDIT_F));
		owner.setLastPaid(rs.getLong(TableOwner.LAST_PAID_F));
		owner.setAddress(rs.getString(TableOwner.ADDRESS_F));
		owner.setPhone(rs.getString(TableOwner.PHONE));
		owner.setCurrency(rs.getString(TableOwner.CURRENCY_F));
		owner.setPaymentNote(rs.getString(TableOwner.PAYMENT_NOTE_F));
		owner.setUid(rs.getLong(TableOwner.UID_F));
		return owner;
	}

	public static BasicItem buildBasicItem(ResultSet rs) throws SQLException {
		BasicItem basicItem = new BasicItem();
		basicItem.setId(rs.getString(TableStandard.ID_F));
		basicItem.setOwnerId(rs.getInt(TableStandard.OWNER_ID_F));
		basicItem.setIdType(rs.getString(TableStandard.ID_TYPE_F));
		basicItem.setGtin(rs.getString(TableStandard.GTIN_F));
		basicItem.setIdCustom(rs.getString(TableStandard.ID_CUSTOM_F));
		basicItem.setBrand(rs.getString(TableStandard.BRAND_F));
		basicItem.setName(rs.getString(TableStandard.NAME_F));
		basicItem.setDescription(rs.getString(TableStandard.DESCRIPTION_F));
		basicItem.setUrl(rs.getString(TableStandard.URL_F));
		basicItem.setCategory(rs.getString(TableStandard.CATEGORY_F));
		basicItem.setHits(rs.getInt(TableStandard.HIT_F));
//		basicItem.setImgNum(rs.getInt(TableStandard.TABLE_NAME + "." + TableStandard.IMG_NUM));
		basicItem.setPrice(rs.getInt(TableStandard.PRICE_F));
		basicItem.setSalePrice(rs.getInt(TableStandard.SALE_PRICE_F));
		basicItem.setSaleStart(rs.getInt(TableStandard.SALE_START_F));
		basicItem.setSaleEnd(rs.getInt(TableStandard.SALE_END_F));
		basicItem.setCondition(rs.getString(TableStandard.COND_F));
		return basicItem;
	}



	public static ApparelExtension buildApparelExtension(ResultSet rs) throws SQLException {
		ApparelExtension apparelExtension = new ApparelExtension();
		String id = rs.getString(TableStandardApparel.TABLE_NAME + "." + TableStandardApparel.ID);
		if (id == null) {
			return null;
		}
		apparelExtension.setGroupId(rs.getString(TableStandardApparel.GROUP_ID_F));
		apparelExtension.setGender(rs.getString(TableStandardApparel.GENDER_F));
		apparelExtension.setAge(rs.getString(TableStandardApparel.AGE_F));
		apparelExtension.setSize(rs.getString(TableStandardApparel.SIZE_F));
		apparelExtension.setSizeSystem(rs.getString(TableStandardApparel.SIZE_SYSTEM_F));
		apparelExtension.setColor(rs.getString(TableStandardApparel.COLOR_F));
		apparelExtension.setMaterial(rs.getString(TableStandardApparel.MATERIAL_F));
		apparelExtension.setFeature(rs.getString(TableStandardApparel.FEATURE_F));
		return apparelExtension;
	}
	
	public static User buildUser(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUid(rs.getLong(TableUser.UID_F));
		user.setTwitterToken(rs.getString(TableUser.TWITTER_TOKEN_F));
		user.setTwitterSecret(rs.getString(TableUser.TWITTER_SECRET_F));
		user.setTwitterDescription(rs.getString(TableUser.TWITTER_DESCRIPTION_F));
		user.setTwitterLanguage(rs.getString(TableUser.TWITTER_LANGUAGE_F));
		user.setTwitterLocation(rs.getString(TableUser.TWITTER_LOCATION_F));
		user.setTwitterName(rs.getString(TableUser.TWITTER_NAME_F));
		user.setTwitterScreenName(rs.getString(TableUser.TWITTER_SCREEN_NAME_F));
		user.setTwitterTz(rs.getString(TableUser.TWITTER_TZ_F));
		user.setTwitterUrl(rs.getString(TableUser.TWITTER_URL_F));
		user.setUrlImgBanner(rs.getString(TableUser.URL_IMG_BANNER_MOBILE_RETINA_F));
		user.setUrlImgProfile(rs.getString(TableUser.URL_IMG_PROFILE_F));		
		return user;
	}
	
	public static BidAndCurrency buildBidAndCurrency(ResultSet rs) throws SQLException{
		BidAndCurrency bidAndCurrency = new BidAndCurrency();
		bidAndCurrency.setCurrency(rs.getString(TableDefaultBid.CURRENCY_F));
		bidAndCurrency.setDefaultBid(rs.getInt(TableDefaultBid.DEFAULT_BID_F));
		bidAndCurrency.setIncrementBid(rs.getFloat(TableDefaultBid.INCREMENT_BID_F));
		return bidAndCurrency;	
	}

}
