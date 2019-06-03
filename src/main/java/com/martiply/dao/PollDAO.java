package com.martiply.dao;

import javax.sql.DataSource;

import org.joda.time.Instant;
import org.springframework.jdbc.core.JdbcTemplate;

import com.martiply.db.tables.TableTrack;

public class PollDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
//	public String checkUidForTrackId(String uid){
//		String trackId =null;
//		String sql = "SELECT * FROM " + TableUser.SCHEMA + "." + TableUser.TABLE + 
//				" WHERE " + TableUser.UID + " = ?";
//		
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		try{
//		trackId = jdbcTemplate.queryForObject(sql, new Object[]{uid}, new RowMapper<String>(){
//
//			@Override
//			public String mapRow(ResultSet rs, int n) throws SQLException {
//				return rs.getString(TableUser.TRACK_ID);
//			}
//			
//		});
//		}catch(EmptyResultDataAccessException e){
//			 trackId = DigestUtils.sha1Hex(uid);
//			 
//			 String sql2 = "INSERT INTO " + TableUser.SCHEMA + "." + TableUser.TABLE + 
//						"( " + TableUser.UID + "," + TableUser.TRACK_ID + " ) " +
//						"VALUES " +
//						"( ?, ? )";  
//			 jdbcTemplate.update(sql2, new Object[]{uid, trackId});
//			 
//			 String sql3 = "CREATE TABLE IF NOT EXISTS " + TableTrack.SCHEMA + "." + trackId + " LIKE "+ TableTrack.SCHEMA + ".template";
//			 jdbcTemplate.update(sql3);
//		}	
//		return trackId;
//	}
	
//	public void poll(String trackId, double lat, double lng, String keyword){
//		Instant g = new Instant();
//		int currentSec = (int) (g.plus(Config.UTCP9).getMillis() / 1000);
//		String sql = "INSERT INTO " + TableTrack.SCHEMA + "." + trackId + 
//				" ( " + 
//				TableTrack.TIMESTAMP + " , " +
//				TableTrack.LATITUDE + " , " +
//				TableTrack.LONGITUDE + " , " + 
//				TableTrack.KEYWORD + 
//				" ) VALUES ( ?, ?, ?, ? )";
//		
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update(sql, new Object[]{currentSec, lat, lng, keyword});	
//		
//	}
	


}
