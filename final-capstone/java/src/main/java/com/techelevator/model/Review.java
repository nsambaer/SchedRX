package com.techelevator.model;

public class Review {
	
	private Long reviewId;
	private Long doctorId;
	private Long patientId;
	private String reviewTitle;
	private int rating;
	private String reviewDescription;
	
	public Review(Long reviewId, Long doctorId, Long patientId, String reviewTitle, int rating,
			String reviewDescription) {
		super();
		this.reviewId = reviewId;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewDescription = reviewDescription;
	}

	public Review(Long doctorId, Long patientId, String reviewTitle, int rating, String reviewDescription) {
		super();
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewDescription = reviewDescription;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}
	
	
	
	
	

}
