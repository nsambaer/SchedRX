package com.techelevator.model;

public class Review {
	
	private Long reviewId;
	private Doctor doctor;
	private Patient patient;
	private Office office;
	private String reviewTitle;
	private int rating;
	private String reviewDescription;
	private String comments;
	
	public Review() {}

	public Review(Doctor doctor, Patient patient, Office office, String reviewTitle, int rating,
			String reviewDescription) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.office = office;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewDescription = reviewDescription;
	}




	public Review(Long reviewId, Doctor doctor, Patient patient, Office office, String reviewTitle, int rating,
			String reviewDescription, String comments) {
		super();
		this.reviewId = reviewId;
		this.doctor = doctor;
		this.patient = patient;
		this.office = office;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewDescription = reviewDescription;
		this.comments = comments;
	}








	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	};
	
	
	
	
	
	

}
