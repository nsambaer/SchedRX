package com.techelevator.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.techelevator.model.Appointment;

public interface AppointmentDAO {
	
	public Appointment getAppointmentById(Long appointmentId);
	
	public List<Appointment> getAppointmentsByPatient(Long patientId);
	
	public List<Appointment> getAppointmentsByDoctor(Long doctorId);
	
	public List<Appointment> getAppointmentsByOffice(Long officeId);
	
	public List<Appointment> getAppointmentsByDate(LocalDate date);
	
	public void createAppointment(Long patientId, Long doctorId, 
									Long officeId, LocalDate appointmentDate,
									LocalTime appointmentStartTime, LocalTime appointmentEndTime,
									String visitReason, String appointmentType);
}
