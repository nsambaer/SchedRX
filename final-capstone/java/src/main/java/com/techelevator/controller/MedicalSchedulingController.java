package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.AppointmentDAO;
import com.techelevator.dao.DoctorDAO;
import com.techelevator.dao.OfficeDAO;
import com.techelevator.dao.PatientDAO;
import com.techelevator.dao.ReviewDAO;
import com.techelevator.dao.UserDAO;
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
	
	@RequestMapping(path = "/offices", method = RequestMethod.GET)
	public List<Office> getAllOffices() {
		List<Office> officeList = officeDao.getAllOffices();
		return officeList;
	}
	
	@RequestMapping(path = "/offices/{id}", method = RequestMethod.GET)
	public Office getOfficeById(@PathVariable Long officeId) {
		return officeDao.getOfficeById(officeId);
	}
	
	@RequestMapping(path = "/reviews/{id}", method = RequestMethod.GET)
	public List<Review> getReviewsByOffice(@PathVariable Long officeId) {
		List<Review> reviewList = reviewDao.getReviewsByOffice(officeId);
		return reviewList;
	}
	
	@RequestMapping(path = "/offices/{id}/doctors", method = RequestMethod.GET)
	public List<Doctor> getDoctorsByOffice(@PathVariable Long officeId) {
		return doctorDao.getDoctorsByOffice(officeId);
	}
	
	@RequestMapping(path = "/doctors/{id}/patients", method = RequestMethod.GET)
	public List<Patient> getPatientsByDoctor(@PathVariable Long doctorId) {
		return patientDao.getPatientsByDoctor(doctorId);
	}
	
	@RequestMapping(path = "/doctors/{id}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		return appointmentDao.getAppointmentsByDoctor(doctorId);
	}
	
	
	

}
