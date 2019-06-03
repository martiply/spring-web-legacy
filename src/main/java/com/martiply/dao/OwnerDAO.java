package com.martiply.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.martiply.db.tables.ResultMapper;
import com.martiply.db.tables.TableOwner;
import com.martiply.db.tables.TableUser;
import com.martiply.model.Owner;
import com.martiply.model.User;
import com.martiply.model.internal.Credentials;

public class OwnerDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public Credentials getCredentials(final String email){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(				
				"SELECT * FROM {0} LEFT JOIN {1} ON {2} WHERE {3} = ?",
				TableOwner.TABLE_NAME,
				TableUser.TABLE_NAME,
				TableOwner.UID_F + " = " + TableUser.UID_F,
				TableOwner.EMAIL				
				);
				
		List<Credentials> list  = jdbcTemplate.query(sql, new Object[]{email}, new RowMapper<Credentials>() {

			@Override
			public Credentials mapRow(ResultSet rs, int rowNum) throws SQLException {
				Credentials credentials = new Credentials();
				Owner owner = ResultMapper.buildOwner(rs);
				credentials.setOwner(owner);
				User user = ResultMapper.buildUser(rs);
				if (user.getUid() > 0){
					credentials.setUser(user);
				}
				
				return credentials;
			}
		});
		
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}	
	}
	
	public Credentials getCredentials(final int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(				
				"SELECT * FROM {0} LEFT JOIN {1} ON {2} WHERE {3} = ?",
				TableOwner.TABLE_NAME, //0
				TableUser.TABLE_NAME, //1
				TableOwner.UID_F + " = " + TableUser.UID_F, //2
				TableOwner.OWNER_ID	//3
				);
				
		List<Credentials> list  = jdbcTemplate.query(sql, new Object[]{ownerId}, new RowMapper<Credentials>() {

			@Override
			public Credentials mapRow(ResultSet rs, int rowNum) throws SQLException {
				Credentials credentials = new Credentials();
				credentials.setOwner(ResultMapper.buildOwner(rs));
				credentials.setUser(ResultMapper.buildUser(rs));		
				return credentials;
			}
		});
		
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}	
	}
	
	public Owner getOwner (int ownerId){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(				
				"SELECT * FROM {0} WHERE {1} = ?",
				TableOwner.TABLE_NAME,
				TableOwner.OWNER_ID				
				);
				
		List<Owner> list  = jdbcTemplate.query(sql, new Object[]{ownerId}, new RowMapper<Owner>() {

			@Override
			public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
				return ResultMapper.buildOwner(rs);
			}
		});
		
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}	
	}
	
	public void setOwner(Owner owner){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format(
				"INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}) " +
				"VALUES (?,?,?,?,?) " +
				"ON DUPLICATE KEY UPDATE " +
				"{6}=?, {7}=?, {8}=?, {9}=?, {10}=?",
				TableOwner.TABLE_NAME, //0
				TableOwner.EMAIL, //1
				TableOwner.NAME, //2
				TableOwner.PHONE, //3
				TableOwner.ADDRESS, //4
				TableOwner.CURRENCY, //5
				TableOwner.EMAIL, //6
				TableOwner.NAME, //7
				TableOwner.PHONE, //8
				TableOwner.ADDRESS, //9
				TableOwner.CURRENCY //10
				);
	
		jdbcTemplate.update(sql, new Object[]{owner.getEmail(), owner.getName(), owner.getPhone(), owner.getAddress(), owner.getCurrency(), 
				owner.getEmail(), owner.getName(), owner.getPhone(), owner.getAddress(), owner.getCurrency()});
			
	}
	
	
	public void updateUid(Owner owner){
		jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = MessageFormat.format("UPDATE {0} SET {1}=? WHERE {2}=?", 
				TableOwner.TABLE_NAME,
				TableOwner.UID,
				TableOwner.OWNER_ID			
				);
		
		jdbcTemplate.update(sql, new Object[]{owner.getUid(), owner.getOwnerId()});
		
	}
	
	public void addCredit(int ownerId, float credit, String paymentNote){		
		jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = MessageFormat.format(				
				"UPDATE {0} SET {1} = {2} + ?, {3} = ? WHERE {4} = ?",
				TableOwner.TABLE_NAME, //0
				TableOwner.CREDIT, //1
				TableOwner.CREDIT, //2
				TableOwner.PAYMENT_NOTE, //3				
				TableOwner.OWNER_ID	 //4
				);		
		jdbcTemplate.update(sql, new Object[]{credit, paymentNote, ownerId});			
	}
	
	public void subtractCredit(int ownerId, float sum){
		jdbcTemplate = new JdbcTemplate(dataSource);		
		String sql = MessageFormat.format(				
				"UPDATE {0} SET {1} = {2} - ? WHERE {3} = ?",
				TableOwner.TABLE_NAME, //0
				TableOwner.CREDIT, //1
				TableOwner.CREDIT, //2
				TableOwner.OWNER_ID	//3
				);		
		jdbcTemplate.update(sql, new Object[]{sum, ownerId});	
	}
	
	public long  loginClient(String email){
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("SELECT {0} FROM {1} WHERE {2} = ? AND {3} > 0", 
				TableOwner.UID, //0
				TableOwner.TABLE_NAME, //1
				TableOwner.EMAIL, //2
				TableOwner.UID //3
				);
		
		List<Long> res = jdbcTemplate.queryForList(sql, new Object[]{email}, Long.class);
		if (res.isEmpty()){
			return 0;
		}
		return res.get(0);
		
	}
	
	/**
	 * Check if user registered. This is used for client barcode login
	 * @param email registered emails
	 * @param twitterToken
	 * @param twitterSecret
	 * @return
	 */
	
	public boolean isClientRegistered(String email, String twitterToken, String twitterSecret){
		jdbcTemplate = new JdbcTemplate(dataSource);
		/*
		  SELECT EXISTS(SELECT 1 FROM owner WHERE email = 'marissa.ristiyana@gmail.com' AND uid = 
		  (SELECT user.uid FROM user WHERE twitterToken = '332666248-bRPV8aBLP4dlv9KGmrN2gA7Hyi5ZkFlC1vctuoix' AND twitterSecret = 'SihODy6he7OnIA8cs4dBrqPSucW2X1x7QkNlN2uNk2VzL'))
		 */
		String sql = MessageFormat.format("SELECT EXISTS(SELECT 1 FROM {0} WHERE {1} = ? AND {2} = "
				+ "(SELECT {3} FROM {4} WHERE {5} = ? AND {6} = ?))", 				
				TableOwner.TABLE_NAME, //0
				TableOwner.EMAIL, //1
				TableOwner.UID, //2
				TableUser.UID, //3
				TableUser.TABLE_NAME, //4
				TableUser.TWITTER_TOKEN, //5
				TableUser.TWITTER_SECRET //6
				);
		
		int count = jdbcTemplate.queryForObject(sql, new Object[]{email, twitterToken, twitterSecret}, Integer.class);
		if (count == 1){
			return true;			
		}
		return false;		
	}

}
