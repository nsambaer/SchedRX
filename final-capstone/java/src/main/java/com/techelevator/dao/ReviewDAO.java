package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Review;


public interface ReviewDAO {
	public Review getAllReviews();
	
	public List<Review> getReviewsByPatient(Long patientId);
	
	public List<Review> getReviewsByOffice(Long officeId);
	
	public List<Review> getReviewsByDoctor(Long doctorId);
	
	public Review createReview(Review review);
}
