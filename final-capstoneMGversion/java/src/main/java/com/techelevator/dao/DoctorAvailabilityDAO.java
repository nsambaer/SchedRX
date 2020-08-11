package com.techelevator.dao;

import com.techelevator.model.DoctorAvailability;

public interface DoctorAvailabilityDAO {

	
	public DoctorAvailability getDoctorAvailabilityForMonth(Long doctorId, int month, int year);
	
	public DoctorAvailability setRegularAvailability(DoctorAvailability regularAvailability);
	
	public DoctorAvailability setSpecificAvailability(DoctorAvailability specificAvailability);
	
	public DoctorAvailability updateRegularAvailability(DoctorAvailability regularAvailability);
	
	public DoctorAvailability updateSpecificAvailability(DoctorAvailability specificAvailability);
	
	public void deleteSpecificAvailability(DoctorAvailability specificAvailability);
}
