package com.techelevator.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Patient;

@Component
public class PatientSqlDAO implements PatientDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public PatientSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Patient getPatientById(Long patientId) {
		String sqlGetPatient = "SELECT * FROM patients WHERE patient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPatient, patientId);
		results.next();
		return mapRowToPatient(results);
	}

	

	@Override
	public List<Patient> getPatientsByDoctor(Long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getPatientsByOffice(Long officeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getPatientsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Patient mapRowToPatient(SqlRowSet results) {
		Patient thePatient = new Patient();
		
		thePatient.setPatientId(results.getLong("patient_id"));
		thePatient.setFirstName(results.getString("first_name"));
		thePatient.setLastName(results.getString("last_name"));
		thePatient.setPrimaryDoctorId(results.getLong("primary_doctor_id"));
		thePatient.setAddress(results.getString("address"));
		thePatient.setBirthdate(results.getDate("date_of_birth").toLocalDate());
		
		return thePatient;
	}

}
