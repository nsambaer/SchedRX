package com.techelevator.model;

import java.math.BigDecimal;

public class Office {
	
	private Long officeId;
	private String address;
	private String phoneNumber;
	private String hours;
	private BigDecimal cost;
	
	public Office(Long officeId, String address, String phoneNumber, String hours, BigDecimal cost) {
		super();
		this.officeId = officeId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.hours = hours;
		this.cost = cost;
	}

	public Office(String address, String phoneNumber, String hours, BigDecimal cost) {
		super();
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.hours = hours;
		this.cost = cost;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	
	
	

}
