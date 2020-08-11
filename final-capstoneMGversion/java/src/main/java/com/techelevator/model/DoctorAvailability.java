package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class DoctorAvailability {

	private Long doctorId;
	private Map<String, LocalTime> regularOpenHours;
	private Map<String, LocalTime> regularCloseHours;
	private Map<LocalDate, LocalTime> specificOpenHours;
	private Map<LocalDate, LocalTime> specificCloseHours;
	private Map<LocalDate, List<LocalTime>> availability;
	
	public DoctorAvailability() {}

	public DoctorAvailability(Long doctorId, Map<String, LocalTime> regularOpenHours,
			Map<String, LocalTime> regularCloseHours, Map<LocalDate, LocalTime> specificOpenHours,
			Map<LocalDate, LocalTime> specificCloseHours) {
		super();
		this.doctorId = doctorId;
		this.regularOpenHours = regularOpenHours;
		this.regularCloseHours = regularCloseHours;
		this.specificOpenHours = specificOpenHours;
		this.specificCloseHours = specificCloseHours;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Map<String, LocalTime> getRegularOpenHours() {
		return regularOpenHours;
	}

	public void setRegularOpenHours(Map<String, LocalTime> regularOpenHours) {
		this.regularOpenHours = regularOpenHours;
	}

	public Map<String, LocalTime> getRegularCloseHours() {
		return regularCloseHours;
	}

	public void setRegularCloseHours(Map<String, LocalTime> regularCloseHours) {
		this.regularCloseHours = regularCloseHours;
	}

	public Map<LocalDate, LocalTime> getSpecificOpenHours() {
		return specificOpenHours;
	}

	public void setSpecificOpenHours(Map<LocalDate, LocalTime> specificOpenHours) {
		this.specificOpenHours = specificOpenHours;
	}

	public Map<LocalDate, LocalTime> getSpecificCloseHours() {
		return specificCloseHours;
	}

	public void setSpecificCloseHours(Map<LocalDate, LocalTime> specificCloseHours) {
		this.specificCloseHours = specificCloseHours;
	}

	public Map<LocalDate, List<LocalTime>> getAvailability() {
		return availability;
	}

	public void setAvailability(Map<LocalDate, List<LocalTime>> availability) {
		this.availability = availability;
	}
	
	
	
	
	
	
	
}
