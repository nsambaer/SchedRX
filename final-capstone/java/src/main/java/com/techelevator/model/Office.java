package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

public class Office {
	
	private Long officeId;
	
	private String officeName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	private String phoneNumber;
	private BigDecimal cost;
	private Map<String, LocalTime> openHours;
	private Map<String, LocalTime> closeHours;
	private List<Doctor> doctorList;
	
	
	public Office() {}	
	
	
	

	public Office(Long officeId) {
		super();
		this.officeId = officeId;
	}




	public Office(String officeName, String address, String city, String state, String zipCode, String phoneNumber,
			BigDecimal cost, Map<String, LocalTime> openHours, Map<String, LocalTime> closeHours,
			List<Doctor> doctorList) {
		super();
		this.officeName = officeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.cost = cost;
		this.openHours = openHours;
		this.closeHours = closeHours;
		this.doctorList = doctorList;
	}




	public Office(Long officeId, String officeName, String address, String city, String state, String zipCode,
			String phoneNumber, BigDecimal cost, Map<String, LocalTime> openHours, Map<String, LocalTime> closeHours,
			List<Doctor> doctorList) {
		super();
		this.officeId = officeId;
		this.officeName = officeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.cost = cost;
		this.openHours = openHours;
		this.closeHours = closeHours;
		this.doctorList = doctorList;
	}




	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Map<String, LocalTime> getOpenHours() {
		return openHours;
	}

	public void setOpenHours(Map<String, LocalTime> openHours) {
		this.openHours = openHours;
	}

	public Map<String, LocalTime> getCloseHours() {
		return closeHours;
	}

	public void setCloseHours(Map<String, LocalTime> closeHours) {
		this.closeHours = closeHours;
	}
	
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	
	
	
	

}
