package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
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
		List<Patient> patientList = new ArrayList<>();
		String sqlPatientsByDoctor = "SELECT * FROM patients WHERE primary_doctor_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPatientsByDoctor, doctorId);
		while (results.next()) {
			Patient thePatient = mapRowToPatient(results);
			patientList.add(thePatient);
		}
		return patientList;
	}

	@Override
	public List<Patient> getPatientsByOffice(Long officeId) {
		List<Patient> patientList = new ArrayList<>();
		String sqlPatientsByOffice = "SELECT * FROM patients p "
									+ "INNER JOIN doctors d "
									+ "ON p.primary_doctor_id = d.doctor_id "
									+ "WHERE d.office_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPatientsByOffice, officeId);
		while (results.next()) {
			Patient thePatient = mapRowToPatient(results);
			patientList.add(thePatient);
		}
		return null;
	}

	@Override
	public List<Patient> getPatientsByDate(LocalDate date) {
		List<Patient> patientList = new ArrayList<>();
		String sqlPatientsByDate = "SELECT * FROM patients p "
								+ "INNER JOIN appointments a "
								+ "ON p.patient_id = a.patient_id "
								+ "WHERE appt_date = ? "
								+ "ORDER BY p.last_name ASC, "
								+ "p.first_name ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPatientsByDate, date);
		while (results.next()) {
			Patient thePatient = mapRowToPatient(results);
			patientList.add(thePatient);
		}
		return patientList;
	}
	
	@Override
	public Patient createPatient(Patient patient) {
		String sqlCreatePatient = "INSERT INTO patients "
				+ "(patient_id, first_name, last_name, address, city, state, zip_code, phone, date_of_birth) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlCreatePatient, patient.getPatientId(), patient.getFirstName(),
							patient.getLastName(), patient.getAddress(), patient.getCity(),
							patient.getState(), patient.getZipCode(), patient.getPhone(), patient.getBirthdate());

		return patient;
	}
	
	private Patient mapRowToPatient(SqlRowSet results) {
		Patient thePatient = new Patient();
		
		thePatient.setPatientId(results.getLong("patient_id"));
		thePatient.setFirstName(results.getString("first_name"));
		thePatient.setLastName(results.getString("last_name"));
		thePatient.setPrimaryDoctorId(results.getLong("primary_doctor_id"));
		thePatient.setAddress(results.getString("address"));
		thePatient.setState(results.getString("state"));
		thePatient.setCity(results.getString("city"));
		thePatient.setZipCode(results.getString("zip_code"));
		thePatient.setPhone(results.getString("phone"));
		thePatient.setBirthdate(results.getDate("date_of_birth").toLocalDate());
		
		return thePatient;
	}

	

}
