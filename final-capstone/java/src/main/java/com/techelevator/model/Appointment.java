package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
	
	private Long appointmentId;
	private Patient patient;
	private Doctor doctor;
	private Office office;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private LocalDate lastUpdatedDate;
	private LocalTime lastUpdatedTime;
	private boolean isVirtual;
	private String visitReason;
	private String appointmentType;
	
	
	public Appointment() {}


	public Appointment(Long appointmentId, Patient patient, Doctor doctor, Office office, LocalDate appointmentDate,
			LocalTime appointmentTime, LocalDate lastUpdatedDate, LocalTime lastUpdatedTime, boolean isVirtual,
			String visitReason, String appointmentType) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.doctor = doctor;
		this.office = office;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedTime = lastUpdatedTime;
		this.isVirtual = isVirtual;
		this.visitReason = visitReason;
		this.appointmentType = appointmentType;
	}


	public Appointment(Patient patient, Doctor doctor, Office office, LocalDate appointmentDate,
			LocalTime appointmentTime, LocalDate lastUpdatedDate, LocalTime lastUpdatedTime, boolean isVirtual,
			String visitReason, String appointmentType) {
		super();
		this.patient = patient;
		this.doctor = doctor;
		this.office = office;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedTime = lastUpdatedTime;
		this.isVirtual = isVirtual;
		this.visitReason = visitReason;
		this.appointmentType = appointmentType;
	}


	public Long getAppointmentId() {
		return appointmentId;
	}


	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Office getOffice() {
		return office;
	}


	public void setOffice(Office office) {
		this.office = office;
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


	public boolean isVirtual() {
		return isVirtual;
	}


	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
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
	}

	
	
	
	
	
	
	
}