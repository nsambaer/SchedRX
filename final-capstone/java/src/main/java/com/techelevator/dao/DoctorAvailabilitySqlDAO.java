package com.techelevator.dao;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DoctorAvailability;

@Component
public class DoctorAvailabilitySqlDAO implements DoctorAvailabilityDAO {
	
	private JdbcTemplate jdbc;
	
	public DoctorAvailabilitySqlDAO(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}

	@Override
	public DoctorAvailability getDoctorAvailability(Long doctorId) {
		
		
		String SqlSelectRegular = "SELECT * FROM doctor_availability WHERE doctor_id = ? AND specific_date = false;";
		String SqlSelectSpecific = "SELECT * FROM doctor_availability WHERE doctor_id = ? AND specific_date = true";
		
		SqlRowSet regularResults = jdbc.queryForRowSet(SqlSelectRegular, doctorId);
		SqlRowSet specificResults = jdbc.queryForRowSet(SqlSelectSpecific, doctorId);
		
		return mapRowsToAvailability(doctorId, regularResults, specificResults);		
		
	}

	@Override
	public DoctorAvailability setRegularAvailability(DoctorAvailability regularAvailability) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorAvailability setSpecificAvailability(DoctorAvailability specificAvailability) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorAvailability updateRegularAvailability(DoctorAvailability regularAvailability) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorAvailability updateSpecificAvailability(DoctorAvailability specificAvailability) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSpecificAvailability(DoctorAvailability specificAvailability) {
		// TODO Auto-generated method stub

	}

	
	private DoctorAvailability mapRowsToAvailability(Long doctorId, SqlRowSet regularResults, SqlRowSet specificResults) {
		DoctorAvailability drAvailability = new DoctorAvailability();
		
		drAvailability.setDoctorId(doctorId);
		drAvailability.setRegularOpenHours(mapRegularOpenToMap(regularResults));
		drAvailability.setRegularCloseHours(mapRegularCloseToMap(regularResults));
		drAvailability.setSpecificOpenHours(mapSpecificOpenToMap(specificResults));
		drAvailability.setSpecificCloseHours(mapSpecificCloseToMap(specificResults));
		return drAvailability;
	}
	
	public Map<String, LocalTime>mapRegularOpenToMap(SqlRowSet results) {
		Map<String, LocalTime> openHours = new HashMap<>();
		
		while (results.next()) {
			Time time = results.getTime("start_time");
			LocalTime lt = null;
			if (time != null) {
				lt = time.toLocalTime();
			}
			openHours.put(results.getString("day_of_week"), lt);
		}
		
		return openHours;
	}
	
	public Map<String, LocalTime>mapRegularCloseToMap(SqlRowSet results) {
		Map<String, LocalTime> closeHours = new HashMap<>();
		results.beforeFirst();
		while (results.next()) {
			Time time = results.getTime("end_time");
			LocalTime lt = null;
			if (time != null) {
				lt = time.toLocalTime();
			}
			closeHours.put(results.getString("day_of_week"), lt);
		}
		
		return closeHours;
	}
	
	public Map<LocalDate, LocalTime>mapSpecificOpenToMap(SqlRowSet results) {
		Map<LocalDate, LocalTime> openHours = new HashMap<>();
		
		while (results.next()) {
			Time time = results.getTime("start_time");
			LocalTime lt = null;
			if (time != null) {
				lt = time.toLocalTime();
			}
			openHours.put(results.getDate("availability_date").toLocalDate(), lt);
		}
		
		return openHours;
	}
	
	public Map<LocalDate, LocalTime>mapSpecificCloseToMap(SqlRowSet results) {
		Map<LocalDate, LocalTime> closeHours = new HashMap<>();
		results.beforeFirst();
		while (results.next()) {
			Time time = results.getTime("end_time");
			LocalTime lt = null;
			if (time != null) {
				lt = time.toLocalTime();
			}
			closeHours.put(results.getDate("availability_date").toLocalDate(), lt);
		}
		
		return closeHours;
	}
	
	
}
