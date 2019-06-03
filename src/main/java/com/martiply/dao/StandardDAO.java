package com.martiply.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.martiply.db.tables.ResultMapper;
import com.martiply.db.tables.TableInventory;
import com.martiply.db.tables.TableOwner;
import com.martiply.db.tables.TableStandard;
import com.martiply.db.tables.TableStandardApparel;
import com.martiply.db.tables.TableStore;
import com.martiply.model.ApparelExtension;
import com.martiply.model.BasicItem;
import com.martiply.model.IPP;
import com.martiply.model.Store;
import com.martiply.utils.HashUtils;

public class StandardDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	/**
	 * Get a single item by item id, used by admin
	 * (now returns {@link BasicItem} with currency) 
	 * @param id
	 * @param ownerId
	 * @return
	 */
	public BasicItem getItem(String id, int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql = MessageFormat.format("SELECT * FROM {0} LEFT JOIN {1} ON {2} JOIN {3} ON {4} WHERE {5} = ? AND {6} = ?",
				TableStandard.TABLE_NAME, //0
				TableStandardApparel.TABLE_NAME, //1
				TableStandard.ID_F + " = " + TableStandardApparel.ID_F, //2
				TableOwner.TABLE_NAME, //3
				TableOwner.OWNER_ID_F  + " = " + TableStandard.OWNER_ID_F, //4
				TableStandard.TABLE_NAME + "." + TableStandard.ID, //5
				TableStandard.TABLE_NAME + "." + TableStandard.OWNER_ID //6
				);
						
		List<BasicItem> res = jdbcTemplate.query(sql, new Object[]{id, ownerId},  new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int arg1) throws SQLException {				
				BasicItem item = ResultMapper.buildBasicItem(rs);
				ApparelExtension apparelExtension = ResultMapper.buildApparelExtension(rs);
				item.setApparelExtension(apparelExtension);
				return item;
			}
			
		});
		if (!res.isEmpty()){
			return res.get(0);
		}
		return null;	
	}
	
	
	private String makeSqlInsertToStandard(){
		String sql = MessageFormat.format("INSERT INTO {0} "
				+ "({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}) VALUES "
				+ "(?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?, ?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "{16}=?, {17}=?, {18}=?, {19}=?, {20}=?, {21}=?, {22}=?, {23}=?",
				TableStandard.TABLE_NAME, //0				
				TableStandard.ID, //1
				TableStandard.OWNER_ID, //2
				TableStandard.ID_TYPE, //3
				TableStandard.GTIN, //4
				TableStandard.ID_CUSTOM, //5
				TableStandard.BRAND, //6
				TableStandard.NAME, //7
				TableStandard.COND, //8
				TableStandard.CATEGORY, //9
				TableStandard.PRICE, //10
				TableStandard.DESCRIPTION, //11
				TableStandard.URL, //12
				TableStandard.SALE_PRICE, //13
				TableStandard.SALE_START, //14
				TableStandard.SALE_END, //15				
				// only these values are changeable				
				TableStandard.ID_CUSTOM, //16
				TableStandard.CATEGORY, //17
				TableStandard.PRICE, //18
				TableStandard.DESCRIPTION, //19
				TableStandard.URL, //20
				TableStandard.SALE_PRICE, //21
				TableStandard.SALE_START, //22
				TableStandard.SALE_END //23
				);
		return sql;
	}
	
	private String makeSqlInsertToApparel(){
		 String sql = MessageFormat.format("INSERT INTO {0} "
			 		+ "({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}) VALUES "
			 		+ "(?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?) ON DUPLICATE KEY UPDATE "
			 		+ " {10}=?, {11}=?, {12}=?, {13}=?, {14}=?, {15}=?, {16}=?, {17}=?", 
					TableStandardApparel.TABLE_NAME, //0
					
					TableStandardApparel.ID, //1
					TableStandardApparel.GENDER, //2
					TableStandardApparel.AGE, //3
					TableStandardApparel.SIZE_SYSTEM, //4
					TableStandardApparel.SIZE, //5
					TableStandardApparel.COLOR, //6
					TableStandardApparel.MATERIAL, //7
					TableStandardApparel.FEATURE, //8
					TableStandardApparel.GROUP_ID, //9

					TableStandardApparel.GENDER, //10
					TableStandardApparel.AGE, //11
					TableStandardApparel.SIZE_SYSTEM, //12
					TableStandardApparel.SIZE, //13
					TableStandardApparel.COLOR, //14
					TableStandardApparel.MATERIAL, //15
					TableStandardApparel.FEATURE, //16
					TableStandardApparel.GROUP_ID //17

					);
		return sql;
	}
	
	private String makeSqlDelete(){
		String sql = MessageFormat.format("DELETE {0}, {1} FROM {2} LEFT JOIN {3} ON {4} WHERE {5}=? AND {6}=?",
				TableStandard.TABLE_NAME, //0
				TableStandardApparel.TABLE_NAME, //1
				TableStandard.TABLE_NAME, //2
				TableStandardApparel.TABLE_NAME, //3
				TableStandard.ID_F + " = " + TableStandardApparel.ID_F, //4
				TableStandard.ID_F, //5
				TableStandard.OWNER_ID_F //6			
				);
		
		return sql;
		
	}
	
	/**
	 * Delete single item
	 * @param item
	 */	
	public void delete(BasicItem item){
		jdbcTemplate = new JdbcTemplate(dataSource);		
		jdbcTemplate.update(makeSqlDelete(), new Object[]{item.getId(), item.getOwnerId()});
	}
	
	/**
	 * Delete batch items
	 * @param items
	 */
	public void delete(final ArrayList<BasicItem> items){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.batchUpdate(makeSqlDelete(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BasicItem item = items.get(i);
				ps.setString(1, item.getId());
				ps.setInt(2, item.getOwnerId());				
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
		});
	}
	
	/**
	 * Batch insert for CSV / multiupload. Needs separate lists for {@link BasicItem} and {@link ApparelExtension}
	 * @param items  List of {@link BasicItem}
	 * @param apparels List of {@link ApparelExtension} with id set
	 */
	
	public void insert(final ArrayList<BasicItem> items, final ArrayList<ApparelExtension> apparels){		
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.batchUpdate(makeSqlInsertToStandard(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BasicItem item = items.get(i);
				ps.setString(1, item.getId()); 
				ps.setInt(2, item.getOwnerId()); 
				ps.setString(3, item.getIdType());
				ps.setString(4, item.getGtin());
				ps.setString(5, item.getIdCustom());
				ps.setString(6, item.getBrand());
				ps.setString(7, item.getName());
				ps.setString(8, item.getCondition());
				ps.setString(9, item.getCategory());
				ps.setFloat(10, item.getPrice());
				ps.setString(11, item.getDescription());
				ps.setString(12,  item.getUrl());
				ps.setFloat(13, item.getSalePrice());
				ps.setInt(14, item.getSaleStart());
				ps.setInt(15, item.getSaleEnd());

				ps.setString(16, item.getIdCustom());
				ps.setString(17, item.getCategory());
				ps.setFloat(18, item.getPrice());
				ps.setString(19, item.getDescription());
				ps.setString(20, item.getUrl());
				ps.setFloat(21, item.getSalePrice());
				ps.setInt(22, item.getSaleStart());
				ps.setInt(23, item.getSaleEnd());				
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
		});
		
		if (apparels == null || apparels.isEmpty()){
			return;
		}
		
		jdbcTemplate.batchUpdate(makeSqlInsertToApparel(), new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ApparelExtension apparel = apparels.get(i);
				
				ps.setString(1, apparel.getId());
				
				ps.setString(2, apparel.getGender());
				ps.setString(3, apparel.getAge());
				ps.setString(4, apparel.getSizeSystem());
				ps.setString(5, apparel.getSize());
				ps.setString(6, apparel.getColor());
				ps.setString(7, apparel.getMaterial());
				ps.setString(8, apparel.getFeature());
				ps.setString(9, apparel.getGroupId());
				
				ps.setString(10, apparel.getGender());
				ps.setString(11, apparel.getAge());
				ps.setString(12, apparel.getSizeSystem());
				ps.setString(13, apparel.getSize());
				ps.setString(14, apparel.getColor());
				ps.setString(15, apparel.getMaterial());
				ps.setString(16, apparel.getFeature());
				ps.setString(17, apparel.getGroupId());
				
			}

			@Override
			public int getBatchSize() {
				return apparels.size();
			}			
		});
		
	}
	
	/**
	 * Checked for V2
	 * @param basicItem
	 */
	public void insert(BasicItem basicItem){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(makeSqlInsertToStandard(), new Object[]{
				basicItem.getId(), 
				basicItem.getOwnerId(), 
				basicItem.getIdType(), 
				basicItem.getGtin(), 
				basicItem.getIdCustom(), 
				basicItem.getBrand(), 
				basicItem.getName(), 
				basicItem.getCondition(),
				basicItem.getCategory(),
				basicItem.getPrice(),
				basicItem.getDescription(),
				basicItem.getUrl(),
				basicItem.getSalePrice(),
				basicItem.getSaleStart(),
				basicItem.getSaleEnd(),
				
				basicItem.getIdCustom(),
				basicItem.getCategory(),
				basicItem.getPrice(),
				basicItem.getDescription(),
				basicItem.getUrl(),
				basicItem.getSalePrice(),
				basicItem.getSaleStart(),
				basicItem.getSaleEnd(),
		});
		
		ApparelExtension apparelExtension = basicItem.getApparelExtension();
		if (apparelExtension != null){
			
			jdbcTemplate.update(makeSqlInsertToApparel(), new Object[]{
					basicItem.getId(), 
					
					apparelExtension.getGender(), 
					apparelExtension.getAge(),
					apparelExtension.getSizeSystem(),
					apparelExtension.getSize(),
					apparelExtension.getColor(),
					apparelExtension.getMaterial(),
					apparelExtension.getFeature(),
					apparelExtension.getGroupId(),
					
					apparelExtension.getGender(), 
					apparelExtension.getAge(),
					apparelExtension.getSizeSystem(),
					apparelExtension.getSize(),
					apparelExtension.getColor(),
					apparelExtension.getMaterial(),
					apparelExtension.getFeature(),
					apparelExtension.getGroupId(),
					
			});
		
		}		
	}
	
	/**
	 * Get items by their partial keys from form. Maximum query 50
	 * @param ownerId
	 * @param ids
	 * @return
	 */
	
	public ArrayList<BasicItem> getItems(int ownerId, String[] partialKeys){	
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] params = new Object[partialKeys.length + 1];
		params[0] = ownerId;
		StringBuilder sb = new StringBuilder("SELECT * FROM {0} LEFT JOIN {1} ON {2} WHERE {3}=? AND {4} IN");
		sb.append("(");
		String delimiter = "";
		for (int i = 0; i < partialKeys.length; i++){
			params[i+1] = partialKeys[i];
			sb.append(delimiter);
			delimiter = ",";
			sb.append("?");			
		}
		sb.append(")");
		
		String sql = MessageFormat.format(sb.toString(), 
				TableStandard.TABLE_NAME, //0
				TableStandardApparel.TABLE_NAME, //1
				TableStandard.ID_F + " = " + TableStandardApparel.ID_F, //2
				TableStandard.OWNER_ID_F, //3
				TableStandard.ID_F //4
				);
		
		List<BasicItem> res = jdbcTemplate.query(sql, params, new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int arg1) throws SQLException {			
				BasicItem item = ResultMapper.buildBasicItem(rs);
				ApparelExtension apparelExtension = ResultMapper.buildApparelExtension(rs);
				item.setApparelExtension(apparelExtension);
				return item;
			}
			
		});		
		return new ArrayList<BasicItem>(res);					
	}
	
	/**
	 * Get all items for admin  use
	 * (now returns {@link BasicItem} with currency) 
	 * @param ownerId owner's id
	 * @return
	 */
	public ArrayList<BasicItem> getItems(int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = MessageFormat.format("SELECT * FROM {0} "
				+ " LEFT JOIN {1} ON {2} "
				+ " JOIN {3} ON {4}"
				+ " WHERE {5} = ?", 
				TableStandard.TABLE_NAME, //0		
				TableStandardApparel.TABLE_NAME, //1
				TableStandard.ID_F + " = " + TableStandardApparel.ID_F, //2
				TableOwner.TABLE_NAME, //3
				TableOwner.OWNER_ID_F + " = " + TableStandard.OWNER_ID_F, //4
				TableStandard.OWNER_ID_F //5
				);
		
		List<BasicItem> res = jdbcTemplate.query(sql, new Object[]{ownerId}, new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int arg1) throws SQLException {
				
				BasicItem item = ResultMapper.buildBasicItem(rs);
				ApparelExtension apparelExtension = ResultMapper.buildApparelExtension(rs);
				item.setApparelExtension(apparelExtension);
				return item;
			}
			
		});		
		return new ArrayList<BasicItem>(res);	
	}
	
	
	
	public int countItems(int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql = MessageFormat.format("SELECT COUNT(*) FROM {0} WHERE {1} = ?", 
				TableStandard.TABLE_NAME,
				TableStandard.OWNER_ID		
				);
		
		return jdbcTemplate.queryForObject(sql, new Object[]{ownerId}, Integer.class);			
	}
			
	
	/**
	 * Make id for item to be inserted
	 * uses MD5. At lease delicious does it too
	 * @param item
	 * @return
	 */
	public static String makeKey(BasicItem item){
		String key = null;
		switch (item.getIdType()){
		
		case BasicItem.ID_TYPE_CUSTOM:
			key = String.format("%d_%s_%s_%s_%s", item.getOwnerId(), item.getIdCustom(), item.getBrand(), item.getName(), item.getCondition());				
			break;
		case BasicItem.ID_TYPE_GTIN:
			key = String.format("%d_%s_%s_%s", item.getOwnerId(), item.getIdCustom(), item.getGtin(), item.getCondition());
			break;
		}
		
		try{
			if (key != null){
				return HashUtils.md5(key);
			}
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return key;		
	}
		
	public ArrayList<IPP> searchAreaItems(int limit, float radius, double lat, double lng, String keyword){
		jdbcTemplate = new JdbcTemplate(dataSource);		
//		"SELECT * FROM ("
//				+ "SELECT z.storeId, z.name, z.lat, z.lng, z.currency, z.tag, z.zip, z.address, z.phone, z.open, z.close, z.tweetId, z.story, z.tz,"
//				+ "itid, s.ownerId, s.idType, s.gtin, s.idCustom, s.brand, itn, s.cond, s.category, s.price, s.description, s.url,"
//				+ " s.salePrice, s.saleStart, s.saleEnd, s.hit,"
//				+ " p.radius, p.distance_unit"
//				+ "* DEGREES(ACOS(COS(RADIANS(p.latpoint))"
//				+ "* COS(RADIANS(z.lat))"
//				+ "* COS(RADIANS(p.longpoint - z.lng))"
//				+ "+ SIN(RADIANS(p.latpoint))"
//				+ "* SIN(RADIANS(z.lat)))) AS distance"
//				+ " FROM store AS z "
//				+ " JOIN("
//				+ "SELECT ? AS latpoint, ? AS longpoint,"
//				+ String.valueOf(radius) + " AS radius, 111.045 AS distance_unit"
//				+ ") AS p ON 1=1"
//				+ " JOIN inventory ON inventory.storeId = z.storeId"
//				+ " JOIN (SELECT standard.id AS itid, standard.ownerId,  standard.idType, standard.gtin, standard.idCustom, standard.brand,"
//				+ " standard.name AS itn, standard.cond, standard.category, standard.price, standard.description, standard.url,"
//				+ " standard.salePrice, standard.saleStart, standard.saleEnd, standard.hit"
//				+ " FROM standard WHERE MATCH (standard.name, standard.category, standard.brand) AGAINST (?)) AS s ON inventory.id = itid"
//				+ " WHERE z.lat"
//				+ " BETWEEN p.latpoint - (p.radius / p.distance_unit)"
//				+ " AND p.latpoint + (p.radius / p.distance_unit)"
//				+ " AND z.lng"
//				+ " BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
//				+ " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
//				+ ") AS d LIMIT ?";
			
		String sql = MessageFormat.format("SELECT * FROM ("
				+ "SELECT z.{0}, z.{1}, z.{2}, z.{3}, z.{4}, z.{5}, z.{6}, z.{7}, z.{8}, z.{9}, z.{10}, z.{11}, z.{12},"
				+ "itid, s.{13}, s.{14}, s.{15}, s.{16}, s.{17}, itn, s.{18}, s.{19}, s.{20}, s.{21}, s.{22},"
				+ " s.{23}, s.{24}, s.{25}, s.{26},"
				+ " p.radius, p.distance_unit"
				+ "* DEGREES(ACOS(COS(RADIANS(p.latpoint))"
				+ "* COS(RADIANS(z.{27}))"
				+ "* COS(RADIANS(p.longpoint - z.{28}))"
				+ "+ SIN(RADIANS(p.latpoint))"
				+ "* SIN(RADIANS(z.{29})))) AS distance"
				+ " FROM store AS z "
				+ " JOIN("
				+ "SELECT ? AS latpoint, ? AS longpoint, {30} AS radius, 111.045 AS distance_unit"
				+ ") AS p ON 1=1"
				+ " JOIN {31} ON {32} = z.{33}"
				+ " JOIN (SELECT {34} AS itid, {35}, {36}, {37}, {38}, {39},"
				+ " {40} AS itn, {41}, {42}, {43}, {44}, {45},"
				+ " {46}, {47}, {48}, {49}"
				+ " FROM {50} WHERE MATCH ({51}, {52}, {53}) AGAINST (?)) AS s ON {54} = itid"
				+ " WHERE z.{55}"
				+ " BETWEEN p.latpoint - (p.radius / p.distance_unit)"
				+ " AND p.latpoint + (p.radius / p.distance_unit)"
				+ " AND z.{56}"
				+ " BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
				+ " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
				+ ") AS d LIMIT ?",
				
				TableStore.STORE_ID,TableStore.NAME,TableStore.LAT,TableStore.LNG,TableStore.CURRENCY,TableStore.TAG,TableStore.ZIP,TableStore.ADDRESS,TableStore.PHONE,TableStore.OPEN,
				TableStore.CLOSE,TableStore.STORY,TableStore.TZ, // 12
				TableStandard.OWNER_ID, TableStandard.ID_TYPE, TableStandard.GTIN, TableStandard.ID_CUSTOM, TableStandard.BRAND, //17
				TableStandard.COND, TableStandard.CATEGORY, TableStandard.PRICE, TableStandard.DESCRIPTION, TableStandard.URL, //22
				TableStandard.SALE_PRICE, TableStandard.SALE_START, TableStandard.SALE_END, TableStandard.HIT, //26
				TableStore.LAT, TableStore.LNG, TableStore.LAT, String.valueOf(radius), //30
				TableInventory.TABLE_NAME, TableInventory.STORE_ID_F, TableStore.STORE_ID, //33
				TableStandard.ID_F, TableStandard.OWNER_ID_F, TableStandard.ID_TYPE_F, TableStandard.GTIN_F, TableStandard.ID_CUSTOM_F, TableStandard.BRAND_F, // 39
				TableStandard.NAME_F, TableStandard.COND_F, TableStandard.CATEGORY_F, TableStandard.PRICE_F, TableStandard.DESCRIPTION_F, TableStandard.URL_F, //45
				TableStandard.SALE_PRICE_F, TableStandard.SALE_START_F, TableStandard.SALE_END_F, TableStandard.HIT_F, //49
				TableStandard.TABLE_NAME, TableStandard.NAME_F, TableStandard.CATEGORY_F, TableStandard.BRAND_F, TableInventory.ID_F, //54
				TableStore.LAT, TableStore.LNG	
				);

		List<IPP> result = jdbcTemplate.query(sql, new Object[]{lat, lng, keyword, limit}, new RowMapper<IPP>(){

			@Override
			public IPP mapRow(ResultSet rs, int rowNum) throws SQLException {
				IPP ipp = new IPP();
				BasicItem basicItem = new BasicItem();
				basicItem.setId(rs.getString("itid"));
				basicItem.setOwnerId(rs.getInt(TableStandard.OWNER_ID));
				basicItem.setIdType(rs.getString(TableStandard.ID_TYPE));
				basicItem.setGtin(rs.getString(TableStandard.GTIN));
				basicItem.setIdCustom(rs.getString(TableStandard.ID_CUSTOM));
				basicItem.setBrand(rs.getString(TableStandard.BRAND));
				basicItem.setName(rs.getString("itn"));
				basicItem.setDescription(rs.getString(TableStandard.DESCRIPTION));
				basicItem.setUrl(rs.getString(TableStandard.URL));
				basicItem.setCategory(rs.getString(TableStandard.CATEGORY));
				basicItem.setHits(rs.getInt(TableStandard.HIT));
				basicItem.setPrice(rs.getInt(TableStandard.PRICE));
				basicItem.setSalePrice(rs.getInt(TableStandard.SALE_PRICE));
				basicItem.setSaleStart(rs.getInt(TableStandard.SALE_START));
				basicItem.setSaleEnd(rs.getInt(TableStandard.SALE_END));
				basicItem.setCondition(rs.getString(TableStandard.COND));		
				
				basicItem.setCurrency(rs.getString( TableStore.CURRENCY));
				Store store = ResultMapper.buildStore(rs, true);				
				ipp.setBasicItem(basicItem);
				ipp.setStore(store);
						
				return ipp;
			}
			
		});
		
		if (result.isEmpty()){
			return null;
		}else{
			return new ArrayList<IPP>(result);
		}
	}

	

}
