package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;

@Component
public class DoctorSqlDAO implements DoctorDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public DoctorSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Doctor getDoctorById(Long doctorId) {
		String sqlGetDoctor = "SELECT * FROM doctors WHERE doctor_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetDoctor, doctorId);
		results.next();
		return mapRowToDoctor(results);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> allDoctors = new ArrayList<>();
		String sqlAllDoctors = "SELECT * FROM doctors";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllDoctors);
		while(results.next()) {
			Doctor theDoctor = mapRowToDoctor(results);
			allDoctors.add(theDoctor);
		}
		return allDoctors;
	}
	
	@Override
	public List<Doctor> getDoctorsByOffice(Long officeId) {
		List<Doctor> doctorList = new ArrayList<>();
		String sqlDoctorsByOffice = "SELECT * FROM doctors WHERE office_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlDoctorsByOffice, officeId);
		while(results.next()) {
			Doctor theDoctor = mapRowToDoctor(results);
			doctorList.add(theDoctor);
		}
		
		return doctorList;
	}
	
	public Doctor mapRowToDoctor(SqlRowSet results) {
		Doctor theDoctor = new Doctor();
		theDoctor.setDoctorId(results.getLong("doctor_id"));
		theDoctor.setOfficeId(results.getLong("office_id"));
		theDoctor.setFirstName(results.getString("first_name"));
		theDoctor.setLastName(results.getString("last_name"));
		return theDoctor;
	}
}
