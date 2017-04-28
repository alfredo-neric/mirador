package com.sistema.restaurant.mirador.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class UtilJdbc {
	
	private static JdbcTemplate jdbcTemplate;

	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		UtilJdbc.jdbcTemplate = jdbcTemplate;
	}
	
}
