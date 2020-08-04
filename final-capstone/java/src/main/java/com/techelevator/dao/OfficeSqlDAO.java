package com.techelevator.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Office;

public class OfficeSqlDAO implements OfficeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public OfficeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Office> getAllOffices() {
		List<Office> allOffices = new ArrayList<>();
		String sqlAllOffices = "SELECT * FROM offices";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllOffices);
		while (results.next()) {
			Office theOffice = mapRowToOffice(results);
			allOffices.add(theOffice);
		}
		return allOffices;
	}

	@Override
	public Office getOfficeById(Long officeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Office getOfficeByDoctor(Long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	private Office mapRowToOffice(SqlRowSet results) {
		Office office = new Office();
		
		office.setOfficeId(results.getLong("office_id"));
		office.setAddress(results.getString("address"));
		office.setCity(results.getString("city"));
		office.setState(results.getString("state"));
		office.setPhoneNumber(results.getString("phone"));
		office.setCost(results.getBigDecimal("cost_per_hour"));
		office.setOpenHours(mapRowToHours(results));
		office.setCloseHours(mapRowToHours(results));
		
		return office;
	}
	
	private Map<String, LocalTime> mapRowToHours(SqlRowSet results) {
		
		
		
		return null;
	}
	
	
	
	
	
	
	
}
