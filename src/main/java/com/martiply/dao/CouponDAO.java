package com.martiply.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.martiply.db.tables.ResultMapper;
import com.martiply.db.tables.TableCoupon;
import com.martiply.db.tables.TableDefaultBid;
import com.martiply.db.tables.TableOwner;
import com.martiply.db.tables.TableShareHistory;
import com.martiply.db.tables.TableUser;
import com.martiply.model.Coupon;
import com.martiply.model.Owner;
import com.martiply.model.User;
import com.martiply.model.internal.BidAndCurrency;
import com.martiply.model.internal.CouponComprehensiveStat;
import com.martiply.model.internal.CouponDailyStat;
import com.martiply.model.internal.CouponOwner;

public class CouponDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	private ResultSetExtractor<CouponComprehensiveStat> getRseCouponComprehensiveStat(final int dayLimit){
		
	 return	new ResultSetExtractor<CouponComprehensiveStat>(){

			@Override
			public CouponComprehensiveStat extractData(ResultSet rs) throws SQLException, DataAccessException {
				CouponComprehensiveStat res = new CouponComprehensiveStat();
				
				long epochDay = 0;
				
				int count = 0;
				ArrayList<CouponDailyStat> couponDailyStats = new ArrayList<>();
				float ranks = 0.0f;
				float rates = 0.0f;
				int scans = 0;
								
				while (rs.next()){
					
					if (couponDailyStats.size() < dayLimit){
					
					long epochDayFromCatalog = rs.getLong(TableShareHistory.TS) / 86400;
					
						if (epochDayFromCatalog != epochDay){
							epochDay = epochDayFromCatalog;
							CouponDailyStat couponDailyStat = new CouponDailyStat();
							couponDailyStat.setMidnightTimestamp((epochDay + 1) * 86400);
							couponDailyStat.setnShares(1);
							couponDailyStats.add(0, couponDailyStat);						
						}else{
							couponDailyStats.get(0).addOneShare();												
						}
					}
					
					count++;
					// must continue outside the daily stat
					ranks += rs.getInt(TableShareHistory.RANK);
					rates += rs.getFloat(TableShareHistory.RATE);
					
					if (rs.getLong(TableShareHistory.SCAN_TS) > 0L){
						scans++;
					}
				}
				
				res.setCatalogDailyStats(couponDailyStats);
				res.setMeanBid(count != 0 ? rates / count : 0f);
				res.setMeanRank(count != 0 ? ranks / count : 0f);
				res.setnShares(count);
				res.setScans(scans);

				return res;
			}};
		
		
	};
	
	
	
	
	/**
	 * Get catalog and owner object
	 * @param tweetId
	 * @return
	 */
	public CouponOwner getCouponAndOwnerByCouponIdAndStatus(long tweetId, String status){
		jdbcTemplate = new JdbcTemplate(dataSource);
		long currentSec =  System.currentTimeMillis() / 1000;
				
		String sql = MessageFormat.format("SELECT * FROM {0} JOIN {1} ON {2} JOIN {3} ON {4} WHERE {5} = ? AND {6} > ? AND {7} = ?", 
				 TableCoupon.TABLE_NAME, //0
				 TableOwner.TABLE_NAME, //1
				 TableCoupon.OWNER_ID_F + " = " + TableOwner.OWNER_ID_F, //2
				 TableDefaultBid.TABLE_NAME, //3
				 TableOwner.CURRENCY_F + " = " +TableDefaultBid.CURRENCY_F, //4
				 TableCoupon.CPID_F, //5
				 TableCoupon.END_F, //6
				 TableCoupon.STATUS_F //7
				);
				
		List<CouponOwner> res = jdbcTemplate.query(sql, new Object[]{tweetId, currentSec, status}, new RowMapper<CouponOwner>(){

			@Override
			public CouponOwner mapRow(ResultSet rs, int rowNum) throws SQLException {
				Owner owner = ResultMapper.buildOwner(rs);
				Coupon catalog = ResultMapper.buildCoupon(rs);
				BidAndCurrency bidAndCurrency = ResultMapper.buildBidAndCurrency(rs);
				CouponOwner co = new CouponOwner(catalog, owner, bidAndCurrency);
				return co;
			}			
		});
		if (res.isEmpty()){
			return null;
		}
		return res.get(0);		
	}
	
	
	public Coupon getCouponByCpId(long cpId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ? ", TableCoupon.TABLE_NAME, TableCoupon.CPID);
		List<Coupon> res = jdbcTemplate.query(sql, new Object[]{cpId}, new RowMapper<Coupon>(){
	
				@Override
				public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
					return ResultMapper.buildCoupon(rs);
				}
				
			});

		if (res.isEmpty()){
			return null;
		}
		return res.get(0);		
	}
	
	/**
	 * Get catalog safe mode
	 * @param tweetId
	 * @param ownerId
	 * @return
	 */
	public Coupon getCouponSafe(long tweetId, int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ? AND {2} = ? ", 
				 TableCoupon.TABLE_NAME, 
				 TableCoupon.CPID,
				 TableCoupon.OWNER_ID
				
				);
		List<Coupon> res = jdbcTemplate.query(sql, new Object[]{tweetId, ownerId}, new RowMapper<Coupon>(){
	
				@Override
				public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
					return ResultMapper.buildCoupon(rs);
				}
				
			});
		
		if (res.isEmpty()){
			return null;
		}

		return res.get(0);		
	}
	
	/**
	 * Update share count
	 * @param tweetId
	 * @param lastSharer
	 * @param increment
	 */	
	public void updateShare(Coupon coupon, String lastSharer, int increment){
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("UPDATE {0} SET {1} + ?, {2} = ? WHERE {3} = ?", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.SHARE + " = " + TableCoupon.SHARE, //1
				TableCoupon.LAST_SHARER, //2
				TableCoupon.CPID //3	
				);

		jdbcTemplate.update(sql, new Object[]{increment, lastSharer, coupon.getCpId()});
	}
	
	/**
	 * Update share and status
	 * @param tweetId
	 * @param lastSharer
	 * @param increment
	 * @param status
	 */
	public void updateShareAndStatus(Coupon coupon, String lastSharer, int increment, String status){
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("UPDATE {0} SET {1} + ?, {2} = ?, {3} = ? WHERE {4} = ?", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.SHARE + " = " + TableCoupon.SHARE, //1
				TableCoupon.LAST_SHARER, //2
				TableCoupon.STATUS, //3
				TableCoupon.CPID //4		
				);
		
		setStatus(coupon, status);		
		jdbcTemplate.update(sql, new Object[]{increment, lastSharer, coupon.getStatus(), coupon.getCpId()});	
	}
	
	public void updateStatus(Coupon catalog, String status){
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("UPDATE {0} SET {1} = ? WHERE {2} = ?", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.STATUS, //1
				TableCoupon.CPID //2		
				);
		
		setStatus(catalog, status);		
		jdbcTemplate.update(sql, new Object[]{catalog.getStatus(), catalog.getCpId()});			
	}
	
	public void newCouponSafe(Coupon coupon, int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("INSERT INTO {0} ({1},{2},{3},{4},{5},{6}) VALUES(?,?,?,?,?,?)", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.CPID, //1
				TableCoupon.CONTENT, //2
				TableCoupon.TERMS, //3
				TableCoupon.END, //4
				TableCoupon.BID, //5
				TableCoupon.OWNER_ID //6
				);
		
		jdbcTemplate.update(sql, new Object[]{coupon.getCpId(), coupon.getContent(), coupon.getTerms(), coupon.getEndSec(), coupon.getBid(), ownerId});		
	}
		
	public void delete(long tweetId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql =  MessageFormat.format(
				"DELETE FROM {0} WHERE {1} = ?",
				TableCoupon.TABLE_NAME,
				TableCoupon.CPID			
				);
		
		jdbcTemplate.update(sql, tweetId);
	}
	
		
	public ArrayList<Coupon> getAllCoupons(int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1}=?",
				TableCoupon.TABLE_NAME,
				TableCoupon.OWNER_ID
				);
		
		List<Coupon> res = jdbcTemplate.query(sql, new Object[]{ownerId}, new RowMapper<Coupon>(){

			@Override
			public Coupon mapRow(ResultSet rs, int arg1) throws SQLException {
				return ResultMapper.buildCoupon(rs);
			}
			
		});		
		return new ArrayList<Coupon>(res);			
	}
	
	
	
	public void updateBid(long tweetId, int ownerId, float newBid){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("UPDATE {0} SET {1}=? WHERE {2}=? AND {3}=?",
				TableCoupon.TABLE_NAME, //0
				TableCoupon.BID, //1
				TableCoupon.CPID, //2
				TableCoupon.OWNER_ID //3
				);
		
		jdbcTemplate.update(sql, new Object[]{newBid, tweetId, ownerId});
	}
	
	
	/**
	 * Get comprehensive catalog statistics for presentation purpose in the web. 
	 * @param tweetId
	 * @param catalog
	 * @param dayInterval
	 * @return
	 */
	public CouponComprehensiveStat getComprehensiveStat(Coupon coupon, final int dayInterval){
		jdbcTemplate = new JdbcTemplate(dataSource);
			
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ?  AND {2} = ? ORDER BY {3} DESC", 
				TableShareHistory.TABLE_NAME, //0
				TableShareHistory.STATUS, //1
				TableShareHistory.CPID, //2
				TableShareHistory.TS //3
				);
		
		return jdbcTemplate.query(sql, new Object[]{TableShareHistory.STATUS_SHARED, coupon.getCpId()}, getRseCouponComprehensiveStat(dayInterval));			
	}
	
	
	/**
	 * Get comprehensive catalog statistics for authorized owners. Used for QR client
	 * @param tweetId
	 * @param dayInterval
	 * @param email
	 * @param tToken
	 * @param tSecret
	 * @return
	 */	
	public CouponComprehensiveStat getComprehensiveStatAuthorized(long cId, final int dayInterval, String email, String tToken, String tSecret){
		jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ " JOIN {1} ON {2}"
				+ " JOIN {3} ON {4}"
				+ " JOIN {5} ON {6}"
				+ " WHERE {7} = ? AND {8} = ? "
				+ " AND {9} = ? AND {10} = ? AND {11} = ?"
				+ " ORDER BY {12} DESC", 
				TableShareHistory.TABLE_NAME,				
				TableCoupon.TABLE_NAME, 
				TableShareHistory.CPID_F + " = " + TableCoupon.CPID_F,				
				TableOwner.TABLE_NAME,				
				TableCoupon.OWNER_ID_F + " = " + TableOwner.OWNER_ID_F,				
				TableUser.TABLE_NAME,
				TableOwner.UID_F + " = " + TableUser.UID_F,						
				TableShareHistory.STATUS_F,
				TableShareHistory.CPID_F,
				TableOwner.EMAIL_F,
				TableUser.TWITTER_TOKEN_F,
				TableUser.TWITTER_SECRET_F,
				TableShareHistory.TS_F								
				);		
		return jdbcTemplate.query(sql, new Object[]{TableShareHistory.STATUS_SHARED,  cId, email, tToken, tSecret}, getRseCouponComprehensiveStat(dayInterval));					
	}
	

	/**
	 * Check scanTs given parameters. Return null if the entry is not found.
	 * @param shareId
	 * @param cId
	 * @param email
	 * @param tToken
	 * @param tSecret
	 * @param scanTs
	 * @return
	 */
	public Long getScanTs(long shareId, long cId, String email, String tToken, String tSecret){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("SELECT {0} FROM {1}"
				+ " WHERE {2} = (SELECT {3} FROM {4}"
				+ " JOIN {5} ON {6}"
				+ " JOIN {7} ON {8}"
				+ " WHERE {9} = ? AND {10} = ? AND {11} = ? "
				+ " AND {12} = ?) "
				+ " AND {13} = ? AND {14} = ?", 
				
				TableShareHistory.SCAN_TS, //0 
				TableShareHistory.TABLE_NAME, //1			
				TableShareHistory.CPID,	//2	
				TableCoupon.CPID, //3
				TableCoupon.TABLE_NAME, //4
				TableOwner.TABLE_NAME, //5
				TableCoupon.OWNER_ID_F + " = " + TableOwner.OWNER_ID_F, //6
				TableUser.TABLE_NAME, //7
				TableOwner.UID_F + " = " + TableUser.UID_F, //8					
				TableOwner.EMAIL_F, //9
				TableUser.TWITTER_TOKEN_F, //10
				TableUser.TWITTER_SECRET_F,	//11		
				TableCoupon.CPID_F,	//12				
				TableShareHistory.STATUS_F, //13			
				TableShareHistory.SHARE_ID_F //14			
				);
		
		List<Long> res = jdbcTemplate.queryForList(sql, new Object[]{email, tToken, tSecret,  cId, TableShareHistory.STATUS_SHARED,  shareId}, Long.class);
				
		
		if (res.isEmpty()){
			return null;			
		}else {
			return res.get(0);
		}
	}
	
	/**
	 * Update scan timestamp with authorization
	 * @param cId
	 * @param email
	 * @param tToken
	 * @param tSecret
	 * @param scanTs
	 */
	public void updateScanAuthorized(long shareId, long cId, String email, String tToken, String tSecret, long scanTs){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("UPDATE {0} SET {1} = ?"
				+ " WHERE {2} = (SELECT {3} FROM {4}"
				+ " JOIN {5} ON {6}"
				+ " JOIN {7} ON {8}"
				+ " WHERE {9} = ? AND {10} = ? AND {11} = ? "
				+ " AND {12} = ?) "
				+ " AND {13} = ? AND {14} = ? ", 				
				TableShareHistory.TABLE_NAME, //0
				TableShareHistory.SCAN_TS_F, //1
				TableShareHistory.CPID,	//2	
				TableCoupon.CPID, //3
				TableCoupon.TABLE_NAME, //4
				TableOwner.TABLE_NAME, //5
				TableCoupon.OWNER_ID_F + " = " + TableOwner.OWNER_ID_F, //6
				TableUser.TABLE_NAME, //7
				TableOwner.UID_F + " = " + TableUser.UID_F,	//8					
				TableOwner.EMAIL_F, //9
				TableUser.TWITTER_TOKEN_F, //10
				TableUser.TWITTER_SECRET_F,//11
				TableCoupon.CPID_F,	//12			
				TableShareHistory.STATUS_F, //13			
				TableShareHistory.SHARE_ID_F //14	
				);		
		 jdbcTemplate.update(sql, new Object[]{scanTs,  email, tToken, tSecret,  cId, TableShareHistory.STATUS_SHARED,  shareId});				
	}
	
	
	
	public void markAllExpires(){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("UPDATE {0} SET {1}=? WHERE {2} < ? NOT LIKE ?",
				TableCoupon.TABLE_NAME, //0
				TableCoupon.STATUS, //1
				TableCoupon.END //2
				);
		
		long nowts = System.currentTimeMillis() / 1000;
		
		jdbcTemplate.update(sql, new Object[]{TableCoupon.STATUS_EXPIRED, nowts, "%"+ TableCoupon.STATUS_RESOURCES_DELETED+"%"});
		
	}
	
	public ArrayList<Coupon> getDaysExpired(int days){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} LIKE ? AND {3} < ?", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.STATUS, //1
				TableCoupon.END //2
				);
		
		long daysInPast = System.currentTimeMillis() / 1000 - days * 86400;
		
		List<Coupon> res = jdbcTemplate.query(sql, new Object[]{"%" + TableCoupon.STATUS_EXPIRED + "%" ,  daysInPast}, new RowMapper<Coupon>(){

			@Override
			public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {				
				return ResultMapper.buildCoupon(rs);
			}
		});
		
		return new ArrayList<Coupon>(res);
		
		
	}
	
	
	private void setStatus(Coupon catalog, String newStatus){
		String status = catalog.getStatus();		
		if (status == null || status.trim().isEmpty()){
			catalog.setStatus(newStatus);
		}else if (!status.contains(newStatus)){
			catalog.setStatus(status + newStatus);
		}
	}
	

	
	/**
	 * Insert to share_user.u_uid. If record exists, set status = shared
	 * @param uid
	 * @param socMed
	 */
	public void insertShare(long uid, long cId, long retweetId, long ts, String value, String method, double lat, double lng, int rank, float rate){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE {11} = ?", 
				TableShareHistory.TABLE_NAME, //0
				TableShareHistory.UID,			//1
				TableShareHistory.SHARE_ID, //2
				TableShareHistory.CPID, //3
				TableShareHistory.TS, //4
				TableShareHistory.VALUE, //5
				TableShareHistory.METHOD, //6
				TableShareHistory.LAT, //7
				TableShareHistory.LNG,	//8
				TableShareHistory.RANK, //9
				TableShareHistory.RATE, //10
				TableShareHistory.STATUS //11
				);		
		jdbcTemplate.update(sql, new Object[]{uid, retweetId, cId, ts, value, method, lat, lng, rank, rate, TableShareHistory.STATUS_SHARED});	
		
	}

	public void updateShareStatus(long shareId, String newStatus){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("UPDATE {0} SET {1} = ? WHERE {2} = ?",
				TableShareHistory.TABLE_NAME,
				TableShareHistory.STATUS,
				TableShareHistory.SHARE_ID
				);
		
		jdbcTemplate.update(sql, new Object[]{newStatus, shareId});		
	}
	
	
	/**
	 * Get the photo urls of (3) people who have shared the promo
	 * 
	 * @param catalogId
	 * @return
	 */
	public ArrayList<User> getSharerProfilePics(long catalogId, int num) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> res;

		String sql = MessageFormat.format("SELECT {0} FROM {1} JOIN {2} ON {3} WHERE {4} = ? AND {5} IS NOT NULL ORDER BY RAND() LIMIT ?", 
				TableUser.URL_IMG_PROFILE,
				TableUser.TABLE_NAME, 
				TableShareHistory.TABLE_NAME,
				TableUser.UID_F+ " = " + TableShareHistory.UID_F,
				TableShareHistory.STATUS_F,
				TableUser.URL_IMG_PROFILE);
				
		res = jdbcTemplate.query(sql, new Object[] {TableShareHistory.STATUS_SHARED, num}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUrlImgProfile(rs.getString(TableUser.URL_IMG_PROFILE));
				return user;
			}
		});

		return (ArrayList<User>) res;
	}

	/**
	 * Count the number of people who shared the promo
	 * 
	 * @param catalogId
	 * @return
	 */
	public int countShares(long catalogId) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = MessageFormat.format("SELECT COUNT(*) FROM {0} WHERE {1} = ? AND {2} = ?", 
				TableShareHistory.TABLE_NAME,
				TableShareHistory.CPID,
				TableShareHistory.STATUS
				);

		return jdbcTemplate.queryForObject(sql, new Object[]{catalogId, TableShareHistory.STATUS_SHARED}, Integer.class);
	}
	
			
	/**
	 * Get all active and past (depending on nDaysBeforeNow) catalogs of owner with credentials. This is used for barcode client
	 * @param email
	 * @param twitterToken
	 * @param twitterSecret
	 * @param currentTs
	 * @param nDaysBeforeNow
	 * @return
	 */
	public ArrayList<Coupon> getCatalogsFromNdayBeforeNow(String email, String twitterToken, String twitterSecret, long currentTs, int nDaysBeforeNow){
		jdbcTemplate = new JdbcTemplate(dataSource);
		/*
		 SELECT * FROM coupon WHERE ownerId = 
		(SELECT ownerId FROM owner JOIN user ON owner.uid = user.uid WHERE owner.email = 'someemail@email.com' 
		AND user.twitterToken = '2401765076-8nDdCJwv3WMWyzHzjdxERArAmQpUa2m25xvFafK' 
		AND user.twitterSecret = 'siT8FPMwde6VHr5wL4y1ANbfy17JLRKDPolx6BsF4g7b1') AND end > 1442818364 - 24 * 3600 * 7 	 
		 */
		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ "WHERE {1} = (SELECT {2} FROM {3} JOIN {4} ON {5} WHERE {6} = ? "
				+ "AND {7} = ? AND {8} = ?) "
				+ "AND {9} > {10} - 24 * 3600 * {11}", 
				TableCoupon.TABLE_NAME, //0
				TableCoupon.OWNER_ID, //1
				TableOwner.OWNER_ID, //2
				TableOwner.TABLE_NAME, //3
				TableUser.TABLE_NAME, //4
				TableOwner.UID_F +  " = " + TableUser.UID_F,
				TableOwner.EMAIL_F,
				TableUser.TWITTER_TOKEN_F,
				TableUser.TWITTER_SECRET_F,
				TableCoupon.END,
				String.valueOf(currentTs),
				String.valueOf(nDaysBeforeNow));
		
		List<Coupon> res = jdbcTemplate.query(sql, new Object[]{email, twitterToken, twitterSecret}, new RowMapper<Coupon>(){

			@Override
			public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
				Coupon c = ResultMapper.buildCoupon(rs);
				return c;
			}						
		}); 
		
		return new ArrayList<Coupon>(res);
		
	}
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
