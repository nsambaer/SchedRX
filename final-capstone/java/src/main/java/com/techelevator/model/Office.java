package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Map;

import javax.validation.constraints.NotBlank;

public class Office {
	
	private Long officeId;
	@NotBlank(message = "The office name cannot be blank")
	private String officeName;
	@NotBlank(message = "The address cannot be blank")
	private String address;
	@NotBlank(message = "The city cannot be blank")
	private String city;
	@NotBlank(message = "The state cannot be blank")
	private String state;
	@NotBlank(message = "The phone number cannot be blank")
	private String phoneNumber;
	private BigDecimal cost;
	private Map<String, LocalTime> openHours;
	private Map<String, LocalTime> closeHours;
	
	public Office() {}	
	
	public Office(Long officeId, String officeName, String address, String city, String state, String phoneNumber, BigDecimal cost,
			Map<String, LocalTime> openHours, Map<String, LocalTime> closeHours) {
		super();
		this.officeId = officeId;
		this.officeName = officeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.cost = cost;
		this.openHours = openHours;
		this.closeHours = closeHours;
	}

	public Office(String officeName, String address, String city, String state, String phoneNumber, BigDecimal cost,
			Map<String, LocalTime> openHours, Map<String, LocalTime> closeHours) {
		super();
		this.officeName = officeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.cost = cost;
		this.openHours = openHours;
		this.closeHours = closeHours;
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
	
	
	
	

}
