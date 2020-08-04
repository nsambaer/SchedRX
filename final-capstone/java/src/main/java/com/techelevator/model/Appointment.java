package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
	
	private Long appointmentId;
	private Long patientId;
	private Long doctorId;
	private Long officeId;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private LocalDate lastUpdatedDate;
	private LocalTime lastUpdatedTime;
	private String visitReason;
	private String appointmentType;
	
	
	public Appointment() {}

	public Appointment(Long patientId, Long doctorId, Long officeId, LocalDate appointmentDate,
			LocalTime appointmentTime, LocalDate lastUpdatedDate, LocalTime lastUpdatedTime, String visitReason,
			String appointmentType) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.officeId = officeId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedTime = lastUpdatedTime;
		this.visitReason = visitReason;
		this.appointmentType = appointmentType;
	}

	public Appointment(Long appointmentId, Long patientId, Long doctorId, Long officeId, LocalDate appointmentDate,
			LocalTime appointmentTime, LocalDate lastUpdatedDate,
			LocalTime lastUpdatedTime, String visitReason, String appointmentType) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.officeId = officeId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedTime = lastUpdatedTime;
		this.visitReason = visitReason;
		this.appointmentType = appointmentType;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(LocalTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	};
	
	
	
	
	
	
}	