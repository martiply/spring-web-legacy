package com.martiply.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.martiply.db.tables.ResultMapper;
import com.martiply.db.tables.TableTrack;
import com.martiply.db.tables.TableUser;
import com.martiply.model.User;

public class UserDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Update tokens with existing twitter user id or insert new with all credentials
	 * 
	 * @param twuid
	 * @param twta
	 * @param twts
	 */
	public void registerTwitter(long uid, String twT, String twS, twitter4j.User user) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE " + "{13} = ?, {14} = ?, {15} = ?, {16} = ?, {17} = ?, {18} = ?, {19} = ?, {20} = ?, {21} = ?, {22} = ?, {23} = ?", 	
				TableUser.TABLE_NAME, TableUser.UID, TableUser.TWITTER_TOKEN, TableUser.TWITTER_SECRET, TableUser.TWITTER_URL, TableUser.TWITTER_NAME, TableUser.TWITTER_SCREEN_NAME, TableUser.TWITTER_DESCRIPTION, TableUser.TWITTER_LOCATION, TableUser.TWITTER_LANGUAGE, TableUser.TWITTER_TZ, TableUser.URL_IMG_PROFILE, TableUser.URL_IMG_BANNER_MOBILE_RETINA, TableUser.TWITTER_TOKEN, TableUser.TWITTER_SECRET,
				TableUser.TWITTER_URL, TableUser.TWITTER_NAME, TableUser.TWITTER_SCREEN_NAME, TableUser.TWITTER_DESCRIPTION, TableUser.TWITTER_LOCATION, TableUser.TWITTER_LANGUAGE, TableUser.TWITTER_TZ, TableUser.URL_IMG_PROFILE, TableUser.URL_IMG_BANNER_MOBILE_RETINA);

		jdbcTemplate.update(sql,
				new Object[] { uid, twT, twS, user.getURL(), user.getName(), user.getScreenName(), user.getDescription(), user.getLocation(), user.getLang(), user.getTimeZone(), user.getOriginalProfileImageURL(), user.getProfileBannerMobileRetinaURL(), twT, twS, user.getURL(), user.getName(), user.getScreenName(), user.getDescription(), user.getLocation(), user.getLang(), user.getTimeZone(),
						user.getOriginalProfileImageURL(), user.getProfileBannerMobileRetinaURL() });

	}

	public User getTwitterUser(long uid, String twT) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ? AND {2} = ?", 
				TableUser.TABLE_NAME, //0
				TableUser.UID, //1
				TableUser.TWITTER_TOKEN //2
				);
		User res = null;
		try {
			res = jdbcTemplate.queryForObject(sql, new Object[] { uid, twT }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return ResultMapper.buildUser(rs);
				}
			});

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	public User getTwitterUser(long uid) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ?", 
				TableUser.TABLE_NAME, 
				TableUser.UID);
				
		List<User> res = jdbcTemplate.query(sql, new Object[] { uid }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setUid(rs.getLong(TableUser.UID));
					user.setTwitterToken(rs.getString(TableUser.TWITTER_TOKEN));
					user.setTwitterSecret(rs.getString(TableUser.TWITTER_SECRET));
					user.setTwitterDescription(rs.getString(TableUser.TWITTER_DESCRIPTION));
					user.setTwitterLanguage(rs.getString(TableUser.TWITTER_LANGUAGE));
					user.setTwitterLocation(rs.getString(TableUser.TWITTER_LOCATION));
					user.setTwitterName(rs.getString(TableUser.TWITTER_NAME));
					user.setTwitterScreenName(rs.getString(TableUser.TWITTER_SCREEN_NAME));
					user.setTwitterTz(rs.getString(TableUser.TWITTER_TZ));
					user.setTwitterUrl(rs.getString(TableUser.TWITTER_URL));
					user.setUrlImgBanner(rs.getString(TableUser.URL_IMG_BANNER_MOBILE_RETINA));
					user.setUrlImgProfile(rs.getString(TableUser.URL_IMG_PROFILE));
					return user;
				}
			});
		if (res.isEmpty()){
			return null;
		}		
		return res.get(0);
	}

	/**
	 * Insert a keyword to user track table
	 * 
	 * @param uid
	 * @param millis
	 * @param keyword
	 * @param lat
	 * @param lng
	 */
	public void insertToKeywordTrack(long uid, long millis, String keyword, double lat, double lng) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}) VALUES(?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE {6} = ?", 
				TableTrack.TABLE_NAME, //0
				TableTrack.UID,  //1
				TableTrack.MILLIS, //2
				TableTrack.KEYWORD, //3
				TableTrack.LAT, //4
				TableTrack.LNG, //5
				TableTrack.UID//6

		);
		jdbcTemplate.update(sql, new Object[] { uid, millis, keyword, lat, lng, uid });
	}
	

}
