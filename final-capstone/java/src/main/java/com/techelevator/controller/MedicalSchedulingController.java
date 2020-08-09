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
import org.springframework.web.bind.annotation.RequestParam;
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
	
<<<<<<< HEAD
	@PreAuthorize("permitAll()")
=======
	//OFFICE METHODS
	
	@PreAuthorize("permitAll")
>>>>>>> 6e7d993844a4e7fab0d8e9ca03196547417ee44d
	@RequestMapping(path = "/offices", method = RequestMethod.GET)
	public List<Office> getAllOffices() {
		List<Office> officeList = officeDao.getAllOffices();
		return officeList;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/offices/{officeId}", method = RequestMethod.GET)
	public Office getOfficeById(@PathVariable Long officeId) {
		return officeDao.getOfficeById(officeId);
	}
	
<<<<<<< HEAD
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/offices/{adminId}/current-office", method = RequestMethod.GET)
	public Office getOfficeByAdmin(@PathVariable Long adminId) {
		return officeDao.getOfficeByAdmin(adminId);
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/offices/{officeId}/reviews", method = RequestMethod.GET)
	public List<Review> getReviewsByOffice(@PathVariable Long officeId) {
		List<Review> reviewList = reviewDao.getReviewsByOffice(officeId);
		return reviewList;
	}
	
	@PreAuthorize("permitAll()")
=======
	@PreAuthorize("permitAll")
>>>>>>> 6e7d993844a4e7fab0d8e9ca03196547417ee44d
	@RequestMapping(path = "/offices/{officeId}/doctors", method = RequestMethod.GET)
	public List<Doctor> getDoctorsByOffice(@PathVariable Long officeId) {
		return doctorDao.getDoctorsByOffice(officeId);
	}
	
<<<<<<< HEAD
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/{doctorId}/patients", method = RequestMethod.GET)
	public List<Patient> getPatientsByDoctor(@PathVariable Long doctorId) {
		return patientDao.getPatientsByDoctor(doctorId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/{doctorId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		return appointmentDao.getAppointmentsByDoctor(doctorId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/patients/{patientId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
		return appointmentDao.getAppointmentsByPatient(patientId);
	}
	
	@PreAuthorize("permitAll()")
=======
	@PreAuthorize("hasRole('ADMIN')")
>>>>>>> 6e7d993844a4e7fab0d8e9ca03196547417ee44d
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/offices", method = RequestMethod.POST)
	public Office createOffice(@RequestBody Office office) {
		return officeDao.createOffice(office);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(path = "/offices/{officeId}", method = RequestMethod.PUT)
	public Office updateOffice(@RequestBody Office office, @PathVariable Long officeId) {
		return officeDao.updateOffice(office, officeId);
	}
	
	
	//PATIENT METHODS
	
	@PreAuthorize("hasRole('PATIENT')")
	@RequestMapping(path = "/patients/{patientId}", method = RequestMethod.GET)
	public Patient getPatientById(@PathVariable Long patientId) {
		return patientDao.getPatientById(patientId);
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@RequestMapping(path = "/patients/{patientId}", method = RequestMethod.PUT)
	public Patient updatePatient(@RequestBody Patient patient) {
		return patientDao.updatePatient(patient);
	}
	
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/patients", method = RequestMethod.POST)
	public Patient createPatient(@RequestBody Patient patient) {
		return patientDao.createPatient(patient);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT')")
	@RequestMapping(path = "/patients/{patientId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
		return appointmentDao.getAppointmentsByPatient(patientId);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/patients/{patientId}/appointments", method = RequestMethod.POST)
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return appointmentDao.createAppointment(appointment);
	}
	
	
	//DOCTOR METHODS
	
	@PreAuthorize("hasAnyRole('PATIENT')")
	@RequestMapping(path = "/doctors", method = RequestMethod.GET)
	public List<Doctor> listDoctors() {
		return doctorDao.getAllDoctors();
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@RequestMapping(path = "/doctors/{doctorId}/appointments", method = RequestMethod.GET)
	public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		return appointmentDao.getAppointmentsByDoctor(doctorId);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
	@RequestMapping(path = "/doctors/{doctorId}/availability", method = RequestMethod.GET)
	public DoctorAvailability listDoctorAvailabilityForMonth(@PathVariable Long doctorId, @RequestParam int month, @RequestParam int year) {
		return drAvailDao.getDoctorAvailabilityForMonth(doctorId, month, year);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/doctors/{doctorId}/availability/regular", method = RequestMethod.POST)
	public DoctorAvailability setRegularDoctorAvailability(@RequestBody DoctorAvailability regularAvailability) {
		return drAvailDao.setRegularAvailability(regularAvailability);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/doctors/{doctorId}/availability/specific", method = RequestMethod.POST)
	public DoctorAvailability setSpecificDoctorAvailability(@RequestBody DoctorAvailability specificAvailability) {
		return drAvailDao.setSpecificAvailability(specificAvailability);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(path = "/doctors/{doctorId}/availability/regular", method = RequestMethod.PUT)
	public DoctorAvailability updateRegularDoctorAvailability(@RequestBody DoctorAvailability regularAvailability) {
		return drAvailDao.updateRegularAvailability(regularAvailability);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(path = "/doctors/{doctorId}/availability/specific", method = RequestMethod.PUT)
	public DoctorAvailability updateSpecificDoctorAvailability(@RequestBody DoctorAvailability specificAvailability) {
		return drAvailDao.updateSpecificAvailability(specificAvailability);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/doctors/{doctorId}/availability/specific", method = RequestMethod.DELETE)
	public void deleteSpecificDoctorAvailability(@RequestBody DoctorAvailability specificAvailability) {
		drAvailDao.deleteSpecificAvailability(specificAvailability);
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(path = "/appointments/{appointmentId}", method = RequestMethod.PUT)
	public Appointment updateAppointment(@RequestBody Appointment appointment, @PathVariable Long appointmentId) {
		return appointmentDao.updateAppointment(appointment, appointmentId);
	}
	
	
	
	
	
	
	
	
	
	

	//NOT IN USE
	
	/*
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/offices/{officeId}/reviews", method = RequestMethod.GET)
	public List<Review> getReviewsByOffice(@PathVariable Long officeId) {
		List<Review> reviewList = reviewDao.getReviewsByOffice(officeId);
		return reviewList;
	}
	
	@PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
	@RequestMapping(path = "/doctors/{doctorId}/patients", method = RequestMethod.GET)
	public List<Patient> getPatientsByDoctor(@PathVariable Long doctorId) {
		return patientDao.getPatientsByDoctor(doctorId);
	}
	
	
	
	
	*/
}
