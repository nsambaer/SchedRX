package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTypesSqlDAO implements AppointmentTypesDAO {

	private JdbcTemplate jdbc;
	
	public AppointmentTypesSqlDAO (JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}
	
	@Override
	public List<String> getAppointmentTypes() {
		List<String> typeList = new ArrayList<>();
		
		String SqlSelect = "SELECT * FROM appointment_types";
		
		SqlRowSet results = jdbc.queryForRowSet(SqlSelect);
		
		while (results.next()) {
			typeList.add(results.getString("appointment_type"));
		}
		
		return typeList;
	}
	
}
