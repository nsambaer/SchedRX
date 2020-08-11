package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;
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
		String sqlAllReviews = "SELECT * FROM reviews";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllReviews);
		results.next();
		return mapRowToReview(results);
	}

	
	@Override
	public List<Review> getReviewsByOffice(Long officeId) {
		List<Review> reviewList = new ArrayList<>();
		String sqlReviewsByOffice = "SELECT * FROM reviews r "
									+ "INNER JOIN doctor d "
									+ "ON r.doctor_id = d.doctor_id "
									+ "WHERE d.office_id = ?";
		
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
		String sqlReviewsByOffice = "SELECT * FROM reviews r "
									+ "WHERE doctor_id= ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReviewsByOffice, doctorId);
		while (results.next()) {
			Review theReview = mapRowToReview(results);
			reviewList.add(theReview);
		}
		return reviewList;
	}

	private Review mapRowToReview(SqlRowSet results) {
		Review theReview = new Review();
		
		theReview.setReviewId(results.getLong("review_id"));
		theReview.setDoctorId(results.getLong("doctor_id"));
		theReview.setPatientId(results.getLong("patient_id"));
		theReview.setReviewTitle(results.getString("title"));
		theReview.setRating(results.getInt("rating"));
		theReview.setReviewDescription(results.getString("description"));
		theReview.setComments(results.getString("comments"));
		
		Doctor reviewDoctor = doctorDao.getDoctorById(theReview.getDoctorId());
		Patient reviewPatient = patientDao.getPatientById(theReview.getPatientId());
		theReview.setDoctor(reviewDoctor);
		theReview.setPatient(reviewPatient);
		
		
		return theReview;
		
	}
	

	
}
