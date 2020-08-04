package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Review;

public interface ReviewDAO {
	public Review getAllReviews();
	
	public List<Review> getReviewsByOffice(Long officeId);
}
