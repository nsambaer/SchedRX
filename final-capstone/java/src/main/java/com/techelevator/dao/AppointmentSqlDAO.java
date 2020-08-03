package com.techelevator.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Appointment;

@Component
public class AppointmentSqlDAO implements AppointmentDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public AppointmentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {
		String sqlGetAppointment = "SELECT * FROM appointments WHERE appointment_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointment, appointmentId);
		results.next();
		return mapRowToAppointment(results);
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(Long patientId) {
		List<Appointment> patientAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT * FROM appointments WHERE patient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, patientId);
		while(results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			patientAppointments.add(theAppointment);
		}
		return patientAppointments;
	}
	
	@Override
	public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
		List<Appointment> doctorAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT * FROM appointments WHERE doctor_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, doctorId);
		while(results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			doctorAppointments.add(theAppointment);
		}
		return doctorAppointments;
	}
	
	@Override
	public List<Appointment> getAppointmentsByOffice(Long officeId) {
		List<Appointment> officeAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT * FROM appointments WHERE office_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, officeId);
		while(results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			officeAppointments.add(theAppointment);
		}
		return officeAppointments;
	}
	
	@Override
	public List<Appointment> getAppointmentsByDate(LocalDate date) {
		List<Appointment> appointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT * FROM appointments WHERE appt_date = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, date);
		while(results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			appointments.add(theAppointment);
		}
		return appointments;
	}
	
	@Override
	public void createAppointment(Long patientId, Long doctorId, Long officeId, LocalDate appointmentDate,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime, String visitReason, Long appointmentTypeId) {
		String sqlCreateAppointment = "INSERT INTO appointments (patient_id, doctor_id, "
																+ "office_id, appt_date, "
																+ "appt_start_time, appt_end_time, "
																+ "visit_reason, appt_type_id "
									+ "VALUES(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateAppointment, patientId, doctorId, 
													officeId, appointmentDate, 
													appointmentStartTime, appointmentEndTime, 
													visitReason, appointmentTypeId);
		
	}
	
	private Appointment mapRowToAppointment(SqlRowSet results) {
		Appointment theAppointment = new Appointment();
		theAppointment.setAppointmentId(results.getLong("appointment_id"));
		theAppointment.setPatientId(results.getLong("patient_id"));
		theAppointment.setDoctorId(results.getLong("doctor_id"));
		theAppointment.setOfficeId(results.getLong("office_id"));
		theAppointment.setAppointmentDate(results.getDate("appt_date").toLocalDate());
		theAppointment.setAppointmentTime(results.getTime("appt_time").toLocalTime());
		theAppointment.setVisitReason(results.getString("visit_reason"));
		theAppointment.setAppointmentTypeId(results.getLong("appt_type_id"));
		
		return theAppointment;
	}

	

}
