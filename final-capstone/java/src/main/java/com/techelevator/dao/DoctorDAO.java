package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Doctor;

public interface DoctorDAO {
	
	public Doctor getDoctorById(Long doctorId);
	
	public List<Doctor> getAllDoctors();
	
	public List<Doctor> getDoctorsByOffice(Long officeId);
	
	public Long getDoctorIdByName(String doctorName);
	
	public Doctor registerDoctor(Doctor doctor);
	
}
