package com.techelevator.controller;

import java.security.Principal;
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
import com.techelevator.dao.DoctorAvailabilityDAO;
import com.techelevator.dao.DoctorDAO;
import com.techelevator.dao.OfficeDAO;
import com.techelevator.dao.PatientDAO;
import com.techelevator.dao.ReviewDAO;
import com.techelevator.model.Appointment;
import com.techelevator.model.Doctor;
import com.techelevator.model.DoctorAvailability;
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
	private DoctorAvailabilityDAO drAvailDao;
	
	public MedicalSchedulingController(AppointmentDAO appointmentDao, DoctorDAO doctorDao, OfficeDAO officeDao,
			PatientDAO patientDao, ReviewDAO reviewDao, DoctorAvailabilityDAO doctorAvailabilityDao) {
		super();
		this.appointmentDao = appointmentDao;
		this.doctorDao = doctorDao;
		this.officeDao = officeDao;
		this.patientDao = patientDao;
		this.reviewDao = reviewDao;
		this.drAvailDao = doctorAvailabilityDao;
		
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
	@RequestMapping(path = "/doctors/{doctorId}/patients", method = RequestMethod.GET)
	public List<Patient> getPatientsByDoctor(@PathVariable Long doctorId) {
		return patientDao.getPatientsByDoctor(doctorId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/{patientId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		return appointmentDao.getAppointmentsByDoctor(doctorId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/patients/{patientId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
		return appointmentDao.getAppointmentsByPatient(patientId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/offices/create", method = RequestMethod.POST)
	public Office createOffice(@RequestBody Office office) {
		return officeDao.createOffice(office);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/offices/{id}/update", method = RequestMethod.PUT)
	public Office updateOffice(@RequestBody Office office, @PathVariable Long officeId) {
		return officeDao.updateOffice(office, officeId);
	}
	
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/createPatient", method = RequestMethod.POST)
	public Patient createPatient(@RequestBody Patient patient) {
		return patientDao.createPatient(patient);
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/doctor/{doctorId}/availability", method = RequestMethod.GET)
	public DoctorAvailability createPatient(@PathVariable Long doctorId) {
		return drAvailDao.getDoctorAvailability(doctorId);
	}
	
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/appointments/schedule", method = RequestMethod.POST)
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return appointmentDao.createAppointment(appointment);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
	@RequestMapping(path = "/appointments/{appointmentId}/update", method = RequestMethod.PUT)
	public Appointment updateAppointment(@RequestBody Appointment appointment, @PathVariable Long appointmentId) {
		return appointmentDao.updateAppointment(appointment, appointmentId);
	}
	

}
