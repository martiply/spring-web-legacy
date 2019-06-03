package com.martiply.dao;


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
import com.martiply.db.tables.TableStandard;
import com.martiply.model.BasicItem;


public class InventoryDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
		
	/**
	 * Get basic items not in this store for admin purpose
	 * (now returns {@link BasicItem} with currency) 
	 * @param storeId
	 * @param ownerId
	 * @return
	 */
	public ArrayList<BasicItem> getBasicItemsNotInInventory(int storeId, int ownerId){					
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ? AND {2} NOT IN (SELECT {3} FROM {4} WHERE {5} = ?)", 
				TableStandard.TABLE_NAME, //0
				TableStandard.OWNER_ID_F, //1
				TableStandard.ID_F, //2
				TableInventory.ID_F, //3
				TableInventory.TABLE_NAME, //4
				TableInventory.STORE_ID_F //5
				);
			
		List<BasicItem> res = jdbcTemplate.query(sql, new Object[]{ownerId, storeId}, new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int arg1) throws SQLException {
				BasicItem basicItem = ResultMapper.buildBasicItem(rs);
				return basicItem;
			}
			
		});		
		return new ArrayList<BasicItem>(res);	
	}
	
	/**
	 * Register items in inventory.
	 * @param itemIds
	 * @param storeId
	 * @param ownerId
	 */
	
	public void putItems(final String[] itemIds, final int storeId, final int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}) VALUES(?, ?, ?)", 
				TableInventory.TABLE_NAME, //0
				TableInventory.OWNER_ID, //1
				TableInventory.STORE_ID, //2
				TableInventory.ID //3
				);
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ownerId);
				ps.setInt(2, storeId);
				ps.setString(3, itemIds[i]);				
			}
			
			@Override
			public int getBatchSize() {
				return itemIds.length;
			}
		});
	}	
	
	/**
	 * Get all items in the inventory even if the items have been deleted in standard
	 * It doesn't take any ownerId parameter
	 * (now returns {@link BasicItem} with currency) 
	 * @param storeId
	 * @return
	 */
	public ArrayList<BasicItem> getBasicItems(final int storeId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("SELECT * FROM {0} LEFT JOIN {1} ON {2} WHERE {3} = ?",
				TableInventory.TABLE_NAME, //0
				TableStandard.TABLE_NAME, //1
				TableInventory.ID_F + "=" + TableStandard.ID_F, //2
				TableInventory.STORE_ID_F //3
				);
				
		List<BasicItem> result = jdbcTemplate.query(sql, new Object[]{storeId}, new RowMapper<BasicItem>(){

			@Override
			public BasicItem mapRow(ResultSet rs, int i) throws SQLException {
				BasicItem basicItem = ResultMapper.buildBasicItem(rs);
				if (basicItem.getId() == null){
					basicItem.setId(rs.getString(TableInventory.ID_F));
				}
				return basicItem;
			}		
		});	
		return new ArrayList<BasicItem>(result);
		
	}
	
	
	/**
	 * Admin purpose
	 * @param ids
	 * @param storeId
	 */
	public void removeItems(String[] ids, int storeId){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String del="";
		Object[] params = new Object[ids.length];
		StringBuilder sb = new StringBuilder("DELETE FROM {0} WHERE {1} IN(");
		for (int i = 0; i < ids.length; i++){
			sb.append(del);
			sb.append("?");
			del = ",";
			params[i] = ids[i];			
		}
		sb.append(")");
		String sql = MessageFormat.format(sb.toString(), 		
				TableInventory.TABLE_NAME,
				TableInventory.ID
				) ;
		
		
		jdbcTemplate.update(sql, params);	
		
	}
	

	
}
