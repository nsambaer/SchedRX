package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Appointment;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.Patient;

@Component
public class AppointmentSqlDAO implements AppointmentDAO {

	private JdbcTemplate jdbcTemplate;

	public AppointmentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {
		String sqlGetAppointment = "SELECT a.*, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last, "
				+ "o.*, at.appointment_type FROM "
				+ "appointments a INNER JOIN appointment_types at ON a.appt_type_id = at.appointment_types_id "
				+ "INNER JOIN patients p ON a.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON a.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON a.office_id = o.office_id "
				+ "WHERE appointment_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointment, appointmentId);
		results.next();
		return mapRowToAppointment(results);
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(Long patientId) {
		List<Appointment> patientAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT a.*, a.patient_id AS patientID, p.first_name AS patient_first, p.last_name AS patient_last, a.doctor_id AS doctorID, d.first_name AS doctor_first, d.last_name AS doctor_last, "
				+ "a.office_id AS officeID, o.*, at.appointment_type FROM "
				+ "appointments a INNER JOIN appointment_types at ON a.appt_type_id = at.appointment_types_id "
				+ "INNER JOIN patients p ON a.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON a.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON a.office_id = o.office_id "
				+ "WHERE a.patient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, patientId);
		while (results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			patientAppointments.add(theAppointment);
		}
		return patientAppointments;
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
		List<Appointment> doctorAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT a.*, a.patient_id AS patientID, p.first_name AS patient_first, p.last_name AS patient_last, a.doctor_id AS doctorID, d.first_name AS doctor_first, d.last_name AS doctor_last, "
				+ "a.office_id AS officeID, o.*, at.appointment_type FROM "
				+ "appointments a INNER JOIN appointment_types at ON a.appt_type_id = at.appointment_types_id "
				+ "INNER JOIN patients p ON a.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON a.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON a.office_id = o.office_id "
				+ "WHERE a.doctor_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, doctorId);
		while (results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			doctorAppointments.add(theAppointment);
		}
		return doctorAppointments;
	}

	@Override
	public List<Appointment> getAppointmentsByOffice(Long officeId) {
		List<Appointment> officeAppointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT a.*, a.patient_id AS patientID, p.first_name AS patient_first, p.last_name AS patient_last, a.doctor_id AS doctorID, d.first_name AS doctor_first, d.last_name AS doctor_last, "
				+ "a.office_id AS officeID, o.*, at.appointment_type FROM "
				+ "appointments a INNER JOIN appointment_types at ON a.appt_type_id = at.appointment_types_id "
				+ "INNER JOIN patients p ON a.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON a.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON a.office_id = o.office_id "
				+ "WHERE a.office_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, officeId);
		while (results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			officeAppointments.add(theAppointment);
		}
		return officeAppointments;
	}

	@Override
	public List<Appointment> getAppointmentsByDate(LocalDate date) {
		List<Appointment> appointments = new ArrayList<>();
		String sqlGetAppointments = "SELECT a.*, a.patient_id AS patientID, p.first_name AS patient_first, p.last_name AS patient_last, a.doctor_id AS doctorID, d.first_name AS doctor_first, d.last_name AS doctor_last, "
				+ "a.office_id AS officeID, o.*, at.appointment_type FROM "
				+ "appointments a INNER JOIN appointment_types at ON a.appt_type_id = at.appointment_types_id "
				+ "INNER JOIN patients p ON a.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON a.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON a.office_id = o.office_id "
				+ "WHERE appt_date = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAppointments, date);
		while (results.next()) {
			Appointment theAppointment = mapRowToAppointment(results);
			appointments.add(theAppointment);
		}
		return appointments;
	}

	@Override
	public Appointment createAppointment(Appointment appointment) {
		String sqlCreateAppointment = "INSERT INTO appointments "
				+ "(patient_id, doctor_id, office_id, appt_date, appt_time, appt_mod_date, appt_mod_time, virtual, visit_reason, appt_type_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT appointment_types_id FROM appointment_types WHERE appointment_type ILIKE ?))";
		jdbcTemplate.update(sqlCreateAppointment, appointment.getPatient().getPatientId(), appointment.getDoctor().getDoctorId(), appointment.getOffice().getOfficeId(), 
							appointment.getAppointmentDate(), appointment.getAppointmentTime(), appointment.getLastUpdatedDate(), appointment.getLastUpdatedTime(), appointment.isVirtual(), 
							appointment.getVisitReason(), appointment.getAppointmentType());
		return appointment;

	}
	
	@Override
	public Appointment updateAppointment(Appointment appointment) {
		String sqlUpdateAppointment = "UPDATE appointments SET patient_id = ?, doctor_id = ?, "
									+ "office_id = ?, appt_date = ?, appt_time = ?, virtual = ?, visit_reason = ?, "
									+ "appt_type_id = (SELECT appointment_types_id FROM appointment_types WHERE appointment_type ILIKE ?), "
									+ "appt_mod_date = ?, appt_mod_time = ? WHERE appointment_id = ?";
		jdbcTemplate.update(sqlUpdateAppointment, appointment.getPatient().getPatientId(), appointment.getDoctor().getDoctorId(),
							appointment.getOffice().getOfficeId(), appointment.getAppointmentDate(), appointment.getAppointmentTime(), appointment.isVirtual(),
							appointment.getVisitReason(), appointment.getAppointmentType(), appointment.getLastUpdatedDate(), appointment.getLastUpdatedTime(), appointment.getAppointmentId());
		
		return appointment;
	}
	
	@Override
	public void deleteAppointment(Long appointmentId) {
		String sqlDeleteAppointment = "DELETE FROM appointments WHERE appointment_id = ?";
		jdbcTemplate.update(sqlDeleteAppointment, appointmentId);
		
	}

	private Appointment mapRowToAppointment(SqlRowSet results) {
		Appointment theAppointment = new Appointment();
		Patient patient = new Patient();
		Doctor doctor = new Doctor();
		Office office = new Office();		
		
		theAppointment.setAppointmentId(results.getLong("appointment_id"));
		patient.setPatientId(results.getLong("patientID"));
		patient.setFirstName(results.getString("patient_first"));
		patient.setLastName(results.getString("patient_last"));
		theAppointment.setPatient(patient);
		doctor.setDoctorId(results.getLong("doctorID"));
		doctor.setFirstName(results.getString("doctor_first"));
		doctor.setLastName(results.getString("doctor_last"));
		theAppointment.setDoctor(doctor);
		office.setOfficeId(results.getLong("officeID"));
		office.setOfficeName(results.getString("name"));
		office.setAddress(results.getString("address"));
		office.setCity(results.getString("city"));
		office.setState(results.getString("state"));
		office.setPhoneNumber(results.getString("phone"));
		office.setCost(results.getBigDecimal("cost_per_hour"));
		theAppointment.setOffice(office);
		theAppointment.setAppointmentDate(results.getDate("appt_date").toLocalDate());
		theAppointment.setAppointmentTime(results.getTime("appt_time").toLocalTime());
		theAppointment.setVirtual(results.getBoolean("virtual"));
		theAppointment.setVisitReason(results.getString("visit_reason"));
		theAppointment.setAppointmentType(results.getString("appointment_type"));

		return theAppointment;
	}

	
	
	
}
