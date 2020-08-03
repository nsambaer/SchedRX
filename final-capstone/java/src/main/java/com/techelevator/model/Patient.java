package com.techelevator.model;

import java.time.LocalDate;

public class Patient {

	private Long patientId;
	private String firstName;
	private String lastName;
	private Long primaryDoctorId;
	private String address;
	private LocalDate birthdate;
	
	public Patient() {};
	
	public Patient(Long patientId, String firstName, String lastName, Long primaryDoctorId, String address,
			LocalDate birthdate) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryDoctorId = primaryDoctorId;
		this.address = address;
		this.birthdate = birthdate;
	}

	public Patient(String firstName, String lastName, Long primaryDoctorId, String address, LocalDate birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryDoctorId = primaryDoctorId;
		this.address = address;
		this.birthdate = birthdate;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public Long getPrimaryDoctorId() {
		return primaryDoctorId;
	}

	public void setPrimaryDoctorId(Long primaryDoctorId) {
		this.primaryDoctorId = primaryDoctorId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	
	

}
