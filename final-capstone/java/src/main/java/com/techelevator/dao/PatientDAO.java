package com.techelevator.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.Patient;

public interface PatientDAO {
	public Patient getPatientById(Long patientId);
	
	public List<Patient> getPatientsByDoctor(Long doctorId);
	
	public List<Patient> getPatientsByOffice(Long officeId);
	
	public List<Patient> getPatientsByDate(LocalDate date);
	
	public Patient createPatient(Patient patient);
}
