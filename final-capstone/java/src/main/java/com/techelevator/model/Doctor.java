package com.techelevator.model;

public class Doctor {
	
	private Long doctorId;
	private Long officeId;
	private String firstName;
	private String lastName;
	
	public Doctor() {};
	
	
	
	public Doctor(Long doctorId) {
		super();
		this.doctorId = doctorId;
	}



	public Doctor(Long doctorId, Long officeId, String firstName, String lastName) {
		super();
		this.doctorId = doctorId;
		this.officeId = officeId;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	

}
