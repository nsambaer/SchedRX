package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;

@Component
public class PatientSqlDAO implements PatientDAO {

	private JdbcTemplate jdbcTemplate;

	public PatientSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Patient getPatientById(Long patientId) {
		String sqlGetPatient = "SELECT *, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last "
				+ "FROM patients p LEFT OUTER JOIN doctors d ON p.primary_doctor_id = d.doctor_id WHERE patient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPatient, patientId);
		results.next();
		return mapRowToPatient(results);
	}
	
	@Override
	public Patient getPatientByUsername(String username) {
		String sqlGetPatient = "SELECT *, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last "
							+ "FROM patients p INNER JOIN doctors d ON p.primary_doctor_id = d.doctor_id INNER JOIN users u ON p.patient_id = u.user_id WHERE u.username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPatient, username);
		results.next();
		return mapRowToPatient(results);
	}

	@Override
	public List<Patient> getPatientsByDoctor(Long doctorId) {
		List<Patient> patientList = new ArrayList<>();
		String sqlPatientsByDoctor = "SELECT *, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last "
				+ "FROM patients p INNER JOIN doctors d ON p.primary_doctor_id = d.doctor_id WHERE primary_doctor_id = ?";
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
		String sqlPatientsByOffice = "SELECT *, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last "
				+ "FROM patients p INNER JOIN doctors d ON p.primary_doctor_id = d.doctor_id WHERE d.office_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPatientsByOffice, officeId);
		while (results.next()) {
			Patient thePatient = mapRowToPatient(results);
			patientList.add(thePatient);
		}
		return null;
	}


	@Override
	public Patient createPatient(Patient patient) {
		String sqlCreatePatient = "INSERT INTO patients "
				+ "(patient_id, first_name, last_name, address, city, state, zip_code, phone, date_of_birth) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlCreatePatient, patient.getPatientId(), patient.getFirstName(), patient.getLastName(),
				patient.getAddress(), patient.getCity(), patient.getState(), patient.getZipCode(), patient.getPhone(),
				patient.getBirthdate());

		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		String SqlUpdatePatient = "UPDATE patients SET first_name = ?, last_name = ?, phone = ?, address = ?, city = ?, state = ?, zip_code = ?, "
				+ "primary_doctor_id = ?, date_of_birth = ? WHERE patient_id = ?";

		jdbcTemplate.update(SqlUpdatePatient, patient.getFirstName(), patient.getLastName(), patient.getPhone(), patient.getAddress(), patient.getCity(), patient.getState(), patient.getZipCode(), 
				patient.getPrimaryDoctor().getDoctorId(), patient.getBirthdate(), patient.getPatientId());
		
		return patient;
	}

	private Patient mapRowToPatient(SqlRowSet results) {
		Patient thePatient = new Patient();
		Doctor primaryDoctor = new Doctor();

		thePatient.setPatientId(results.getLong("patient_id"));
		thePatient.setFirstName(results.getString("patient_first"));
		thePatient.setLastName(results.getString("patient_last"));
		primaryDoctor.setDoctorId(results.getLong("doctor_id"));
		primaryDoctor.setFirstName(results.getString("doctor_first"));
		primaryDoctor.setLastName(results.getString("doctor_last"));
		primaryDoctor.setOfficeId(results.getLong("office_id"));
		thePatient.setPrimaryDoctor(primaryDoctor);
		thePatient.setAddress(results.getString("address"));
		thePatient.setState(results.getString("state"));
		thePatient.setCity(results.getString("city"));
		thePatient.setZipCode(results.getString("zip_code"));
		thePatient.setPhone(results.getString("phone"));
		thePatient.setBirthdate(results.getDate("date_of_birth").toLocalDate());

		return thePatient;
	}

}
