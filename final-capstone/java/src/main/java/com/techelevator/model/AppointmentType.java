package com.techelevator.model;

public class AppointmentType {

	private Long appointmentTypeId;
	private String appointmentType;
	
	public AppointmentType(Long appointmentTypeId, String appointmentType) {
		super();
		this.appointmentTypeId = appointmentTypeId;
		this.appointmentType = appointmentType;
	}

	public AppointmentType(String appointmentType) {
		super();
		this.appointmentType = appointmentType;
	}

	public Long getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(Long appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	
	
	

}
