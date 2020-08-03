BEGIN TRANSACTION;

CREATE TABLE doctors (
doctor_id INT NOT NULL,
first_name VARCHAR(64) NOT NULL,
last_name VARCHAR(64) NOT NULL,
office_id INT NOT NULL,

CONSTRAINT pk_doctor_doctor_id PRIMARY KEY (doctor_id)
);

CREATE TABLE patients (
patient_id INT NOT NULL,
first_name VARCHAR(64) NOT NULL,
last_name VARCHAR(64) NOT NULL,
phone VARCHAR(10) NOT NULL,
address VARCHAR(128) NOT NULL,
primary_doctor_id INT,
date_of_birth DATE NOT NULL,

CONSTRAINT pk_patient_patient_id PRIMARY KEY (patient_id)
);

CREATE TABLE reviews (
review_id SERIAL NOT NULL,
office_id INT NOT NULL,
doctor_id INT NOT NULL,
patient_id INT NOT NULL,
title VARCHAR(64) NOT NULL,
rating INT NOT NULL,
description TEXT NOT NULL,

CONSTRAINT pk_reviews_review_id PRIMARY KEY (review_id),
CONSTRAINT reviews_rating_check CHECK (rating > 0 AND rating < 6)
);

CREATE TABLE appointments (
appointment_id SERIAL NOT NULL,
office_id INT NOT NULL,
doctor_id INT NOT NULL,
patient_id INT NOT NULL,
appt_date DATE NOT NULL,
appt_start_time TIME NOT NULL,
appt_end_time TIME NOT NULL,
appt_mod_date DATE NOT NULL,
appt_mod_time DATE NOT NULL,
visit_reason VARCHAR(128) NOT NULL,
appt_type_id INT NOT NULL,

CONSTRAINT pk_appointment_appointment_id PRIMARY KEY (appointment_id)
);

CREATE TABLE offices (
office_id SERIAL NOT NULL,
address VARCHAR(128) NOT NULL,
phone VARCHAR(10) NOT NULL,
cost_per_hour NUMERIC(999, 2) NOT NULL,

CONSTRAINT pk_offices_office_id PRIMARY KEY (office_id),
CONSTRAINT cost_per_hour_positive CHECK (cost_per_hour > 0)
);



SELECT * FROM doctors;
SELECT * FROM patients;
SELECT * FROM reviews;
SELECT * FROM appointments;
SELECT * FROM offices;

ROLLBACK;