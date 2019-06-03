package com.martiply.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.martiply.db.tables.ResultMapper;
import com.martiply.db.tables.TableCoupon;
import com.martiply.db.tables.TableStoreCoupon;
import com.martiply.db.tables.TableInventory;
import com.martiply.db.tables.TableShareHistory;
import com.martiply.db.tables.TableStandard;
import com.martiply.db.tables.TableStore;
import com.martiply.model.BasicItem;
import com.martiply.model.Coupon;
import com.martiply.model.Store;

public class StoreDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Store getStore(int storeId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(				
				"SELECT * FROM {0} WHERE {1}=?", 
				TableStore.TABLE_NAME,
				TableStore.STORE_ID 
				);
		
		List<Store> res = jdbcTemplate.query(sql, new Object[]{storeId}, new RowMapper<Store>() {

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				return ResultMapper.buildStore(rs, false);				
			}
		});
		
		if (!res.isEmpty()){
			return res.get(0);
		}
		return null;
		
	}
	
	public Store getStore(int storeId, int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(			
				"SELECT * FROM {0} WHERE {1}=? AND {2}=?", 
				TableStore.TABLE_NAME,
				TableStore.STORE_ID,
				TableStore.OWNER_ID
				);
		
		List<Store> res = jdbcTemplate.query(sql, new Object[]{storeId, ownerId}, new RowMapper<Store>() {

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				return ResultMapper.buildStore(rs, false);				
			}
		});
		
		if (!res.isEmpty()){
			return res.get(0);
		}
		return null;		
	}
	
	/**
	 * Get store info and its coupon. 
	 * If no coupon is available, return store only
	 * @param storeId
	 * @return
	 */
	public Store getStoreInfoAndActiveTwitterCoupon(int storeId, long currentTs){
		jdbcTemplate = new JdbcTemplate(dataSource);
			
//		SELECT * FROM store 
//		LEFT JOIN 
//		(SELECT cp_twitter.tweetId, cp_twitter.ownerId, cp_twitter.tweetContent, cp_twitter.terms, cp_twitter.end, cp_twitter.bid, cp_twitter.share, cp_twitter.lastSharer,
//		cp_twitter.status, 
//		coupon.storeId AS b FROM cp_twitter
//		JOIN coupon ON coupon.tweetId=cp_twitter.tweetId WHERE cp_twitter.end > 1000000 ) 
//		AS cp_twitter ON store.storeId=b WHERE store.storeId = 2
								
		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ " LEFT JOIN ( SELECT {1}, {2} FROM {3} JOIN {4} ON {5} WHERE {6} > ? )"
				+ " AS {7} ON {8} WHERE {9} = ? " ,
				
				TableStore.TABLE_NAME, //0
				TableCoupon.TABLE_NAME + "." + TableCoupon.CPID + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.OWNER_ID + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.CONTENT + "," +
				TableCoupon.TABLE_NAME + "." + TableCoupon.TERMS + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.END + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.BID + "," +
				TableCoupon.TABLE_NAME + "." + TableCoupon.SHARE + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.LAST_SHARER + "," + TableCoupon.TABLE_NAME + "." + TableCoupon.STATUS, //1
				TableStoreCoupon.TABLE_NAME + "." + TableStoreCoupon.STORE_ID + " AS b", //2
				TableCoupon.TABLE_NAME, //3
				TableStoreCoupon.TABLE_NAME, //4
				TableStoreCoupon.CPID_F+ " = " + TableCoupon.CPID_F, //5
				TableCoupon.END_F, //6
				TableCoupon.TABLE_NAME, //7
				TableStore.STORE_ID_F + " = b", //8
				TableStore.STORE_ID_F); //9
			

		List<Store>	list = jdbcTemplate.query(sql, new Object[]{currentTs, storeId}, new RowMapper<Store>(){
	
				@Override
				public Store mapRow(ResultSet rs, int rowNum)throws SQLException {				
					Store store = ResultMapper.buildStore(rs, false);	
					Coupon coupon = ResultMapper.buildCoupon(rs);					
					store.setCoupon(coupon);
					return store;
				}				
			});
		
		if (list.isEmpty()){
			return null;
		}		
		return list.get(0);		
	}
	
	/**
	 * V4 get valid offers in a geofenced area
	 * @param lat 
	 * @param lng
	 * @param limit
	 * @return {@link FeedItem}
	 */
	public ArrayList<Store> getClientValidAreaOffers( double lat,  double lng, float radius, int limit, long currentTs, String cpOkStatus){
		jdbcTemplate = new JdbcTemplate(dataSource);
		/*	
		SELECT * FROM 
		(SELECT coupon.ownerId, coupon.cpId, coupon.content, coupon.terms, 
		coupon.end, coupon.share, store.storeId, store.name, store.lat, store.lng, 
		store.currency, store.tag, store.zip, store.address, store.phone, store.open, 
		store.close, store.story, store.tz, p.radius, 
		p.distance_unit* DEGREES(ACOS(COS(RADIANS(p.latpoint))* COS(RADIANS(store.lat))* COS(RADIANS(p.longpoint - store.lng)) + 
		SIN(RADIANS(p.latpoint))* SIN(RADIANS(store.lat)))) AS distance FROM store AS store 
		JOIN(SELECT -6.906917 AS latpoint, 107.615815 AS longpoint, 3.0 AS radius, 111.045 AS distance_unit) AS p ON 1=1  
		JOIN store_coupon ON store_coupon.storeId = store.storeId  
		JOIN coupon ON coupon.cpId = store_coupon.cpId  
		WHERE store.lat 
		BETWEEN p.latpoint - (p.radius / p.distance_unit) 
		AND p.latpoint + (p.radius / p.distance_unit)  
		AND store.lng BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))  
		AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))  
		AND coupon.end > 1442778074 AND coupon.status = 'ok' GROUP BY store_coupon.cpId  
		ORDER BY coupon.bid DESC LIMIT 30) AS d 
		*/
		
		String sql = MessageFormat.format(
				"SELECT * FROM (SELECT {0}, {1}, {2}, {3}, {4},"
				+ " {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15},"
				+ " {16}, {17}, {18},"
				+ " p.radius, p.distance_unit* DEGREES(ACOS(COS(RADIANS(p.latpoint))* COS(RADIANS({19}))* COS(RADIANS(p.longpoint - {20})) "
				+ " + SIN(RADIANS(p.latpoint))* SIN(RADIANS({21})))) AS distance FROM {22} AS {23} "
				+ " JOIN(SELECT ? AS latpoint, ? AS longpoint, ? AS radius, 111.045 AS distance_unit) AS p ON 1=1 "
				+ " JOIN {24} ON {25} "
				+ " JOIN {26} ON {27} "
				+ " WHERE {28} BETWEEN p.latpoint - (p.radius / p.distance_unit) AND p.latpoint + (p.radius / p.distance_unit) "
				+ " AND {29} BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) "
				+ " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) "
				+ " AND {30} > ?"
				+ " AND {31} = ?"
				+ " GROUP BY {32} "
				+ " ORDER BY {33} DESC LIMIT {34}) AS d "
				, 
				TableCoupon.OWNER_ID_F, // 0
				TableCoupon.CPID_F, //1
				TableCoupon.CONTENT_F, //2
				TableCoupon.TERMS_F, //3
				TableCoupon.END_F, //4
				TableCoupon.SHARE_F, //5
				TableStore.STORE_ID_F, //6
				TableStore.NAME_F, //7
				TableStore.LAT_F, //8
				TableStore.LNG_F, //9
				TableStore.CURRENCY_F, //10
				TableStore.TAG_F, //11
				TableStore.ZIP_F, //12
				TableStore.ADDRESS_F, //13
				TableStore.PHONE_F, //14
				TableStore.OPEN_F, //15
				TableStore.CLOSE_F, //16
				TableStore.STORY_F, //17
				TableStore.TZ_F, //18
				TableStore.LAT_F, //19
				TableStore.LNG_F, //20
				TableStore.LAT_F, //21
				TableStore.TABLE_NAME, //22
				TableStore.TABLE_NAME, //23
				TableStoreCoupon.TABLE_NAME, // 24
				TableStoreCoupon.STORE_ID_F + " = " + TableStore.STORE_ID_F, //25
				TableCoupon.TABLE_NAME, //26
				TableCoupon.CPID_F + " = " + TableStoreCoupon.CPID_F, //27
				TableStore.LAT_F, //28
				TableStore.LNG_F, //29
				TableCoupon.END_F, //30
				TableCoupon.STATUS_F, //31			
				TableStoreCoupon.CPID_F, //32
				TableCoupon.BID_F, //33
				limit //34
				);


		Object[] inParams = new Object[]{lat, lng, radius, currentTs, cpOkStatus};		
		List<Store> res = jdbcTemplate.query(sql, inParams,
				
				new RowMapper<Store>(){

					@Override
					public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
						Store s = ResultMapper.buildStore(rs, true);
						Coupon coupon = new Coupon();
						coupon.setCpId(rs.getLong(TableCoupon.CPID));
						coupon.setContent(rs.getString(TableCoupon.CONTENT));
						coupon.setTerms(rs.getString(TableCoupon.TERMS));
						coupon.setEndSec(rs.getLong(TableCoupon.END));
						coupon.setNumShares(rs.getInt(TableCoupon.SHARE));
						s.setCoupon(coupon);
						return s;
					}
			
			
		});
		return (ArrayList<Store>) res;			
	}

	public Coupon getCatalog(int catalogId){
		jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ?", 
				TableCoupon.TABLE_NAME,
				TableCoupon.CPID			
				);
		
		return jdbcTemplate.queryForObject(sql, new Object[]{catalogId}, new RowMapper<Coupon>(){

			@Override
			public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
				return ResultMapper.buildCoupon(rs);
			}			
		});
	}
	
	/**
	 * Find user's retweet history. This method is called after login to app
	 * @param twUid
	 * @param twT
	 * @param currentMillis
	 */	
	public ArrayList<Store> syncRetweetHistory(long uid, long currentMillis){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		/*
		SELECT * FROM store WHERE store.storeId IN (
				SELECT store_coupon.storeId FROM store_coupon
				JOIN coupon ON store_coupon.tweetId = coupon.cpId
				WHERE store_coupon.tweetId IN (SELECT share_history.cpId FROM share_history WHERE  share_history.uid = '166841261' AND share_history.status = 1)
				AND coupon.end > 1000
				)
		*/
				
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} IN"
				+ " ( SELECT {2} FROM {3} "
				+ " JOIN {4} ON {5} "
				+ " WHERE {6} IN (SELECT {7} FROM {8} WHERE {9} = ? AND {10} = ?) "
				+ " AND {11} > ? )",
				TableStore.TABLE_NAME, //0
				TableStore.STORE_ID_F, //1
				TableStoreCoupon.STORE_ID_F, //2
				TableStoreCoupon.TABLE_NAME, //3
				TableCoupon.TABLE_NAME, //4
				TableStoreCoupon.CPID_F + " = " + TableCoupon.CPID_F, //5
				TableStoreCoupon.CPID_F, //6
				TableShareHistory.CPID_F, //7
				TableShareHistory.TABLE_NAME, //8
				TableShareHistory.UID_F, //9
				TableShareHistory.STATUS_F, //10
				TableCoupon.END_F); //11
		
	
		List<Store> res = jdbcTemplate.query(sql, new Object[]{uid, TableShareHistory.STATUS_SHARED, currentMillis}, new RowMapper<Store>(){

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {			
				Store store = ResultMapper.buildStore(rs, false);	
				Coupon catalog = ResultMapper.buildCoupon(rs);					
				store.setCoupon(catalog);
				return store;
			}
						
		});		
		return (ArrayList<Store>)res;		
	}
	
	public int getRank(long catalogId){
		jdbcTemplate = new JdbcTemplate(dataSource);

		String query = MessageFormat.format("SELECT position FROM ("+
				"SELECT *, @rownum:=@rownum+1 position " +
				"FROM {0}, (SELECT @rownum:=0) r "
				+ " ORDER BY {1} )"
				+ " AS position"
				+ " WHERE {2} = ?",
				TableCoupon.TABLE_NAME,
				TableCoupon.BID,
				TableCoupon.CPID			
				);
		
		try{
			return jdbcTemplate.queryForObject(query, new Object[]{catalogId}, Integer.class);	
		}catch (EmptyResultDataAccessException e){
			return -1;
		}					
	}
		
	public int newStore(int ownerId, Store store){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(				
				"INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}) VALUES (:{15}, :{16}, :{17}, :{18}, :{19}, :{20}, :{21}, :{22}, :{23}, :{24}, :{25}, :{26}, :{27}, :{28})",
				TableStore.TABLE_NAME, //0
				TableStore.NAME, //1
				TableStore.LAT, //2
				TableStore.LNG, //3
				TableStore.TZ, //4
				TableStore.ZIP, //5
				TableStore.ADDRESS, //6
				TableStore.PHONE, //7
				TableStore.OPEN, //8
				TableStore.CLOSE, //9
				TableStore.TAG, //10
				TableStore.CURRENCY, //11
				TableStore.STORY,	//12
				TableStore.CREATE_TS, //13
				TableStore.OWNER_ID, //14
				TableStore.NAME, //15
				TableStore.LAT, //16
				TableStore.LNG, //17
				TableStore.TZ, //18
				TableStore.ZIP, //19
				TableStore.ADDRESS, //20
				TableStore.PHONE, //21
				TableStore.OPEN, //22
				TableStore.CLOSE, //23
				TableStore.TAG, //24
				TableStore.CURRENCY, //25
				TableStore.STORY,//26
				TableStore.CREATE_TS, //27
				TableStore.OWNER_ID //28
				
				);
		
		long currentSec = System.currentTimeMillis() / 1000;
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(TableStore.NAME, store.getName());
		parameters.put(TableStore.LAT, store.getLat());
		parameters.put(TableStore.LNG, store.getLng());
		parameters.put(TableStore.TZ, store.getTz());
		parameters.put(TableStore.ZIP, store.getZip());
		parameters.put(TableStore.ADDRESS, store.getAddress());
		parameters.put(TableStore.PHONE, store.getPhone());
		parameters.put(TableStore.OPEN, store.getOpen());
		parameters.put(TableStore.CLOSE, store.getClose());
		parameters.put(TableStore.TAG, store.getTag());
		parameters.put(TableStore.CURRENCY, store.getCurrency());
		parameters.put(TableStore.STORY, store.getStory());
		parameters.put(TableStore.CREATE_TS, currentSec);
		parameters.put(TableStore.OWNER_ID, ownerId);
		
        SqlParameterSource paramSource = new MapSqlParameterSource(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
 
        namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().intValue();		
	}
	
	
	
	public void deleteStore(int storeId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("DELETE FROM {0} WHERE {1} = ?", 
				TableStore.TABLE_NAME,
				TableStore.STORE_ID			
				);
		
		jdbcTemplate.update(sql, new Object[]{storeId});		
	}
	
	
	
	public void updateStore(int ownerId, Store store){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(
				"UPDATE {0} SET {1}=?, {2}=?, {3}=?, {4}=?, {5}=?, {6}=?, {7}=?, {8}=?, {9}=?, {10}=?, {11}=?, {12}=? WHERE {13}=? AND {14}=?",
				TableStore.TABLE_NAME, //0
				TableStore.NAME, //1
				TableStore.LAT, //2
				TableStore.LNG, //3
				TableStore.TZ, //4
				TableStore.ZIP, //5
				TableStore.ADDRESS, //6
				TableStore.PHONE, //7
				TableStore.OPEN, //8
				TableStore.CLOSE, //9
				TableStore.TAG, //10
				TableStore.CURRENCY, //11
				TableStore.STORY, //12
				TableStore.STORE_ID, //13
				TableStore.OWNER_ID //14
				);
		
		jdbcTemplate.update(sql, new Object[]{store.getName(), store.getLat(), 
				store.getLng(), store.getTz(), store.getZip(), store.getAddress(), store.getPhone(), store.getOpen(), store.getClose(), store.getTag(), store.getCurrency(), store.getStory(), store.getStoreId(), ownerId });
	}
	
	public void addTwitterCoupon(int tweetId, int storeId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format(
				"INSERT INTO {0} ({1}, {2}) VALUES (?, ?)",
				TableStoreCoupon.TABLE_NAME,
				TableStoreCoupon.STORE_ID,
				TableStoreCoupon.CPID);
		
		jdbcTemplate.update(sql, new Object[]{tweetId, storeId});				
	}
	
	
	public ArrayList<Store> getAllStoresAndCatalogsByOwnerId(int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		/*
		SELECT * FROM store
		LEFT JOIN ( 
		SELECT cp_twitter.tweetId, cp_twitter.tweetContent, cp_twitter.THEREST store_coupon.storeId AS d
		FROM cp_twitter JOIN store_coupon ON  cp_twitter.tweetId = store_coupon.tweetId
		) 
		AS cp_twitter
		ON store.storeId = d
		WHERE store.ownerId = 1
		*/
		
		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ " LEFT JOIN ( SELECT {1}, {2} AS d "
				+ " FROM {3} JOIN {4} ON  {5} ) "
				+ " AS {6} ON {7} = d "
				+ " WHERE {8} = ?",
				TableStore.TABLE_NAME, //0
				TableCoupon.CPID_F + "," + TableCoupon.OWNER_ID_F + "," +  TableCoupon.CONTENT_F + "," +  TableCoupon.TERMS_F + "," + 
				TableCoupon.END_F + "," + TableCoupon.BID_F + "," + TableCoupon.SHARE_F + "," + TableCoupon.LAST_SHARER_F + "," + TableCoupon.STATUS_F, //1
				TableStoreCoupon.STORE_ID_F, //2
				TableCoupon.TABLE_NAME, //3
				TableStoreCoupon.TABLE_NAME, //4
				TableCoupon.CPID_F + " = " + TableStoreCoupon.CPID_F, //5
				TableCoupon.TABLE_NAME, //6
				TableStore.STORE_ID_F, //7
				TableStore.OWNER_ID_F); //8

		
		List<Store> res = jdbcTemplate.query(sql, new Object[]{ownerId}, new RowMapper<Store>() {

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				return ResultMapper.buildStoreAndCatalog(rs);				
			}
		});
		
		return new ArrayList<>(res);	
	}
	
	/**
	 * Get store info and its catalog. Not expected to return null
	 * Unlike public method, this one always returns even if catalog has expired
	 * @param storeId
	 * @return
	 */
	public Store getStoreAndCatalogByStoreId(int storeId){
		jdbcTemplate = new JdbcTemplate(dataSource);

		/*
		SELECT * FROM store
		LEFT JOIN ( 
		SELECT cp_twitter.tweetId, cp_twitter.tweetContent, cp_twitter.THEREST store_coupon.storeId AS d
		FROM cp_twitter JOIN store_coupon ON  cp_twitter.tweetId = store_coupon.tweetId
		) 
		AS cp_twitter
		ON store.storeId = d
		WHERE store.storeId = 1
		*/
		
		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ " LEFT JOIN ( SELECT {1}, {2} AS d "
				+ " FROM {3} JOIN {4} ON  {5} ) "
				+ " AS {6} ON {7} = d "
				+ " WHERE {8} = ?",
				TableStore.TABLE_NAME, //0
				TableCoupon.CPID_F + "," + TableCoupon.OWNER_ID_F + "," +  TableCoupon.CONTENT_F + "," +  TableCoupon.TERMS_F + "," + 
				TableCoupon.END_F + "," + TableCoupon.BID_F + "," + TableCoupon.SHARE_F + "," + TableCoupon.LAST_SHARER_F + "," + TableCoupon.STATUS_F, //1
				TableStoreCoupon.STORE_ID_F, //2
				TableCoupon.TABLE_NAME, //3
				TableStoreCoupon.TABLE_NAME, //4
				TableCoupon.CPID_F + " = " + TableStoreCoupon.CPID_F, //5
				TableCoupon.TABLE_NAME, //6
				TableStore.STORE_ID_F, //7
				TableStore.STORE_ID_F); //8
		

		List<Store>	list = jdbcTemplate.query(sql, new Object[]{storeId}, new RowMapper<Store>(){
	
				@Override
				public Store mapRow(ResultSet rs, int rowNum)throws SQLException {				
					Store store = ResultMapper.buildStore(rs, false);	
					Coupon coupon = ResultMapper.buildCoupon(rs);					
					store.setCoupon(coupon);
					return store;
				}				
			});
		
		if (list.isEmpty()){
			return null;
		}
		
		Store res = list.get(0);	
		return res;		
	}
		
	/**
	 * Find items in one store by keyword
	 * (now returns {@link BasicItem} with currency) 
	 * @param keyword
	 * @param si
	 * @param attachStoreInfo should {@link Store} be attached in {@link PlacePrice}. Not attaching it will make result shorter 
	 * @return
	 */
	public ArrayList<BasicItem> findInOneStore(String keyword, int storeId, int limit) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		Object[] params;
		String sql; 
		if (keyword != null && !keyword.trim().isEmpty()){
			sql = MessageFormat.format("SELECT * FROM {0}"
			+ " JOIN {1} ON {2}"
			+ " JOIN {3} ON {4}"
			+ " WHERE {5} = ?"
			+ " AND MATCH ({6}, {7}, {8}) AGAINST (?) LIMIT ?", 
			TableInventory.TABLE_NAME, //0
			TableStore.TABLE_NAME, //1
			TableInventory.STORE_ID_F+ " = " + TableStore.STORE_ID_F, //2
			TableStandard.TABLE_NAME, //3
			TableInventory.ID_F + " = " + TableStandard.ID_F, //4
			TableInventory.STORE_ID_F, //5
			TableStandard.NAME_F, //6
			TableStandard.CATEGORY_F, //7
			TableStandard.BRAND_F //8
			);
			params = new Object[]{storeId, keyword, limit}; 
		}else{
			sql = MessageFormat.format("SELECT * FROM {0}"
			+ " JOIN {1} ON {2}"
			+ " JOIN {3} ON {4}"
			+ " WHERE {5} = ?"
			+ " ORDER BY RAND() LIMIT ?", 
			TableInventory.TABLE_NAME, //0
			TableStore.TABLE_NAME, //1
			TableInventory.STORE_ID_F+ " = " + TableStore.STORE_ID_F, //2
			TableStandard.TABLE_NAME, //3
			TableInventory.ID_F + " = " + TableStandard.ID_F, //4
			TableInventory.STORE_ID_F //5
			);
			params = new Object[]{storeId, limit};
		}
						
		List<BasicItem> result = jdbcTemplate.query(sql, params, new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				BasicItem basicItem = ResultMapper.buildBasicItem(rs);
				basicItem.setCurrency(rs.getString(TableStore.CURRENCY_F));
				return basicItem;
			}});
			
		return new ArrayList<BasicItem>(result);
	}
	
	/**
	 * Find stores that have no association with any active catalog (could be tweetId = 0 or expired)
	 * USe this to determine if new coupon can be created
	 * @param ownerId
	 * @return
	 */
	public ArrayList<Store> getStoresCanCreateCoupon(int ownerId, long tsNow){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		/*
			SELECT * FROM store  WHERE store.ownerId = 1
			AND store.storeId NOT IN (
			SELECT store_coupon.storeId  FROM store_coupon 
			JOIN coupon ON store_coupon.cpId = coupon.cpId
			WHERE end > 1443295615 )
		*/
		
		String sql = MessageFormat.format(
				" SELECT * FROM {0}  WHERE {1} = ?"
				+ " AND {2} NOT IN ("
				+ " SELECT {3}  FROM {4} "
				+ " JOIN {5} ON {6} "
				+ " WHERE {7} > ?)"
				,
				TableStore.TABLE_NAME, //0
				TableStore.OWNER_ID_F, //1
				TableStore.STORE_ID_F, //2
				TableStoreCoupon.STORE_ID_F, //3
				TableStoreCoupon.TABLE_NAME, //4
				TableCoupon.TABLE_NAME, //5
				TableStoreCoupon.CPID_F + " = " + TableCoupon.CPID_F, //6
				TableCoupon.END_F
				);
				
		List<Store> res = jdbcTemplate.query(sql, new Object[]{ownerId, tsNow}, new RowMapper<Store>(){

			@Override
			public Store mapRow(ResultSet rs, int arg1) throws SQLException {
				Store store = ResultMapper.buildStore(rs, false);	
				return store;
			}
			
		});

		return new ArrayList<Store>(res);

	}

	public void insertNewCoupon2LookupTable(int[] storeIds, long cpId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		
		String sql = MessageFormat.format("INSERT INTO {0} ({1},{2}) VALUES (?, ?)",
				TableStoreCoupon.TABLE_NAME,
				TableStoreCoupon.STORE_ID,
				TableStoreCoupon.CPID								
			);
				
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, storeIds[i]);
				ps.setLong(2, cpId);
			}
			
			@Override
			public int getBatchSize() {
				return storeIds.length;
			}
		});
		
	}
	
	/**
	 * Get stores in an area
	 * @param limit
	 * @param radius
	 * @param lat
	 * @param lng
	 * @return
	 */
	public ArrayList<Store> findAreaStore(int limit, float radius, final double lat, final double lng) {	
		
//		String sql = "SELECT * FROM ("
//				+ "SELECT *, "
//				+ "p.distance_unit* DEGREES(ACOS(COS(RADIANS(p.latpoint))"
//				+ "* COS(RADIANS(z.lat))"
//				+ "* COS(RADIANS(p.longpoint - z.lng))"
//				+ "+ SIN(RADIANS(p.latpoint))"
//				+ "* SIN(RADIANS(z.lat)))) AS distance"
//				+ " FROM store AS z"
//				+ " JOIN ("
//				+ "SELECT ? AS latpoint, ? AS longpoint,"
//				+ " ? AS radius, 111.045 AS distance_unit"
//				+ ") AS p ON 1=1"
//				+ " WHERE z.lat"
//				+ " BETWEEN p.latpoint - (p.radius / p.distance_unit)"
//				+ " AND p.latpoint + (p.radius / p.distance_unit)"
//				+ " AND z.lng"
//				+ " BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
//				+ " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
//				+ ") AS d"
//				+ " WHERE distance <= radius"
//				+ " ORDER BY distance"
//				+ " LIMIT ? ";
		
		
		String sql = MessageFormat.format("SELECT * FROM ("
				+ "SELECT *, "
				+ "p.distance_unit* DEGREES(ACOS(COS(RADIANS(p.latpoint))"
				+ "* COS(RADIANS(z.{0}))"
				+ "* COS(RADIANS(p.longpoint - z.{1}))"
				+ "+ SIN(RADIANS(p.latpoint))"
				+ "* SIN(RADIANS(z.{2})))) AS distance"
				+ " FROM {3} AS z"
				+ " JOIN ("
				+ "SELECT ? AS latpoint, ? AS longpoint,"
				+ " ? AS radius, 111.045 AS distance_unit"
				+ ") AS p ON 1=1"
				+ " WHERE z.{4}"
				+ " BETWEEN p.latpoint - (p.radius / p.distance_unit)"
				+ " AND p.latpoint + (p.radius / p.distance_unit)"
				+ " AND z.{5}"
				+ " BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
				+ " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
				+ ") AS d"
				+ " WHERE distance <= radius"
				+ " ORDER BY distance"
				+ " LIMIT ? ", 
				TableStore.LAT, TableStore.LNG, TableStore.LAT, //2
				TableStore.TABLE_NAME, //3
				TableStore.LAT, TableStore.LNG //5
				);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Store> storeInfos =  jdbcTemplate.query(sql, new Object[] {lat, lng, radius, limit},new RowMapper<Store>(){

			@Override
			public Store mapRow(ResultSet rs, int n) throws SQLException {
				Store store = ResultMapper.buildStore(rs, true);
				store.setDistance(rs.getFloat("distance"));
				return store;
			}
									
		});
		return new ArrayList<Store>(storeInfos);
	}
}
