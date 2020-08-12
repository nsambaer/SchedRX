package com.techelevator.model;

import java.time.LocalDate;

public class Patient {

	private Long patientId;
	private String firstName;
	private String lastName;
	private Doctor primaryDoctor;
	private String address;
	private String state;
	private String city;
	private String zipCode;
	private String phone;
	private LocalDate birthdate;
	
	public Patient() {};
	
	
	
	public Patient(Long patientId) {
		super();
		this.patientId = patientId;
	}



	public Patient(Long patientId, String firstName, String lastName, Doctor primaryDoctor, String address,
			String state, String city, String zipCode, String phone, LocalDate birthdate) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryDoctor = primaryDoctor;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.phone = phone;
		this.birthdate = birthdate;
	}

	public Patient(String firstName, String lastName, Doctor primaryDoctor, String address, String state, String city,
			String zipCode, String phone, LocalDate birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryDoctor = primaryDoctor;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.phone = phone;
		this.birthdate = birthdate;
	}

	public Patient(Long patientId, String firstName, String lastName, String address, String state, String city,
			String zipCode, String phone, LocalDate birthdate) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.phone = phone;
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

	public Doctor getPrimaryDoctor() {
		return primaryDoctor;
	}

	public void setPrimaryDoctor(Doctor primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	

}
