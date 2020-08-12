package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.Patient;
import com.techelevator.model.Review;

@Component
public class ReviewSqlDAO implements ReviewDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	DoctorDAO doctorDao;

	@Autowired
	PatientDAO patientDao;

	public ReviewSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Review getAllReviews() {
//		DEPRECATED
		
//		String sqlAllReviews = "SELECT * FROM reviews";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllReviews);
//		results.next();
		return new Review();
	}
	
	@Override
	public List<Review> getReviewsByPatient(Long patientId) {
		List<Review> reviewList = new ArrayList<>();
		String sqlReviewsPatient = "SELECT r.*, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last, o.* "
				+ "FROM reviews r INNER JOIN patients p ON r.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON r.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON r.office_id = o.office_id "
				+ "WHERE r.patient_id= ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReviewsPatient, patientId);
		while (results.next()) {
			Review theReview = mapRowToReview(results);
			reviewList.add(theReview);
		}
		return reviewList;
	}

	@Override
	public List<Review> getReviewsByOffice(Long officeId) {
		List<Review> reviewList = new ArrayList<>();
		String sqlReviewsByOffice = "SELECT r.*, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last, o.* "
				+ "FROM reviews r INNER JOIN patients p ON r.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON r.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON r.office_id = o.office_id "
				+ "WHERE r.office_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReviewsByOffice, officeId);
		while (results.next()) {
			Review theReview = mapRowToReview(results);
			reviewList.add(theReview);
		}
		return reviewList;
	}

	@Override
	public List<Review> getReviewsByDoctor(Long doctorId) {
		List<Review> reviewList = new ArrayList<>();
		String sqlReviewsByOffice = "SELECT r.*, p.first_name AS patient_first, p.last_name AS patient_last, d.first_name AS doctor_first, d.last_name AS doctor_last, o.* "
				+ "FROM reviews r INNER JOIN patients p ON r.patient_id = p.patient_id "
				+ "INNER JOIN doctors d ON r.doctor_id = d.doctor_id "
				+ "INNER JOIN offices o ON r.office_id = o.office_id "
				+ "WHERE r.doctor_id= ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReviewsByOffice, doctorId);
		while (results.next()) {
			Review theReview = mapRowToReview(results);
			reviewList.add(theReview);
		}
		return reviewList;
	}

	@Override
	public Review createReview(Review review) {
		String SqlInsert = "INSERT INTO reviews (patient_id, doctor_id, office_id, title, rating, description) VALUES (?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(SqlInsert, review.getPatient().getPatientId(), review.getDoctor().getDoctorId(),
				review.getOffice().getOfficeId(), review.getReviewTitle(), review.getRating(),
				review.getReviewDescription());

		return review;
	}

	private Review mapRowToReview(SqlRowSet results) {
		Review theReview = new Review();
		Patient patient = new Patient();
		Doctor doctor = new Doctor();
		Office office = new Office();
		
		
		theReview.setReviewId(results.getLong("review_id"));
		patient.setPatientId(results.getLong("patient_id"));
		patient.setFirstName(results.getString("patient_first"));
		patient.setLastName(results.getString("patient_last"));
		theReview.setPatient(patient);
		doctor.setDoctorId(results.getLong("doctor_id"));
		doctor.setFirstName(results.getString("doctor_first"));
		doctor.setLastName(results.getString("doctor_last"));
		theReview.setDoctor(doctor);
		office.setOfficeId(results.getLong("office_id"));
		office.setOfficeName(results.getString("name"));
		theReview.setOffice(office);
		theReview.setReviewTitle(results.getString("title"));
		theReview.setRating(results.getInt("rating"));
		theReview.setReviewDescription(results.getString("description"));
		theReview.setComments(results.getString("comments"));

		return theReview;

	}

}
