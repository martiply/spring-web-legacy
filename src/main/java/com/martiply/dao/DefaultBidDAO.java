package com.martiply.dao;

import java.text.MessageFormat;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.martiply.db.tables.TableDefaultBid;
import com.martiply.model.internal.BidAndCurrency;

public class DefaultBidDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BidAndCurrency getDefaultBid(String currency) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ?", 
				TableDefaultBid.TABLE_NAME, 
				TableDefaultBid.CURRENCY);
		return (BidAndCurrency) jdbcTemplate.queryForObject(sql, new Object[] { currency.toUpperCase() }, new BeanPropertyRowMapper<BidAndCurrency>(BidAndCurrency.class));
	}

}
