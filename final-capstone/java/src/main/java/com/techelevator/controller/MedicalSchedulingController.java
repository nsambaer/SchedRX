package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.AppointmentDAO;
import com.techelevator.dao.DoctorDAO;
import com.techelevator.dao.OfficeDAO;
import com.techelevator.dao.PatientDAO;
import com.techelevator.dao.ReviewDAO;
import com.techelevator.model.Appointment;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.Patient;
import com.techelevator.model.Review;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class MedicalSchedulingController {
	
	private AppointmentDAO appointmentDao;
	private DoctorDAO doctorDao;
	private OfficeDAO officeDao;
	private PatientDAO patientDao;
	private ReviewDAO reviewDao;
	
	public MedicalSchedulingController(AppointmentDAO appointmentDao, DoctorDAO doctorDao, OfficeDAO officeDao,
			PatientDAO patientDao, ReviewDAO reviewDao) {
		super();
		this.appointmentDao = appointmentDao;
		this.doctorDao = doctorDao;
		this.officeDao = officeDao;
		this.patientDao = patientDao;
		this.reviewDao = reviewDao;
		
	}
	
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/offices", method = RequestMethod.GET)
	public List<Office> getAllOffices() {
		List<Office> officeList = officeDao.getAllOffices();
		return officeList;
	}
	
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/offices/{id}", method = RequestMethod.GET)
	public Office getOfficeById(@PathVariable Long officeId) {
		return officeDao.getOfficeById(officeId);
	}
	
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/offices/{id}/reviews", method = RequestMethod.GET)
	public List<Review> getReviewsByOffice(@PathVariable Long officeId) {
		List<Review> reviewList = reviewDao.getReviewsByOffice(officeId);
		return reviewList;
	}
	
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/offices/{id}/doctors", method = RequestMethod.GET)
	public List<Doctor> getDoctorsByOffice(@PathVariable Long officeId) {
		return doctorDao.getDoctorsByOffice(officeId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/patients", method = RequestMethod.GET)
	public List<Patient> getPatientsByDoctor(@RequestBody Doctor doctor) {
		return patientDao.getPatientsByDoctor(doctor.getDoctorId());
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByDoctor(@RequestBody Doctor doctor) {
		return appointmentDao.getAppointmentsByDoctor(doctor.getDoctorId());
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/patients/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByPatient(@RequestBody Patient patient) {
		return appointmentDao.getAppointmentsByPatient(patient.getPatientId());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/offices/create", method = RequestMethod.POST)
	public void createOffice(@RequestBody Office office) {
		officeDao.createOffice(office);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/offices/{id}/update", method = RequestMethod.PUT)
	public Office updateOffice(@RequestBody Office office, @PathVariable Long officeId) {
		return officeDao.updateOffice(office, officeId);
	}
	
	
	

}
