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
phone VARCHAR(12) NOT NULL,
address VARCHAR(128) NOT NULL,
city VARCHAR(64) NOT NULL,
state VARCHAR(32) NOT NULL,
zip_code VARCHAR(5) NOT NULL,
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
comments TEXT,

CONSTRAINT pk_reviews_review_id PRIMARY KEY (review_id),
CONSTRAINT reviews_rating_check CHECK (rating > 0 AND rating < 6)
);

CREATE TABLE appointments (
appointment_id SERIAL NOT NULL,
office_id INT NOT NULL,
doctor_id INT NOT NULL,
patient_id INT NOT NULL,
appt_date DATE NOT NULL,
appt_time TIME NOT NULL,
appt_mod_date DATE NOT NULL,
appt_mod_time TIME NOT NULL,
virtual BOOLEAN NOT NULL,
visit_reason VARCHAR(128) NOT NULL,
appt_type_id INT NOT NULL,

CONSTRAINT pk_appointment_appointment_id PRIMARY KEY (appointment_id)
);

CREATE TABLE appointment_types (
appointment_types_id SERIAL NOT NULL,
appointment_type TEXT NOT NULL,

CONSTRAINT pk_appointment_types PRIMARY KEY (appointment_types_id)
);


CREATE TABLE offices (
office_id SERIAL NOT NULL,
name VARCHAR(128) NOT NULL,
address VARCHAR(128) NOT NULL,
city VARCHAR(64) NOT NULL,
state VARCHAR(32) NOT NULL,
zip_code VARCHAR(5) NOT NULL,
phone VARCHAR(12) NOT NULL,
cost_per_hour NUMERIC(999, 2) NOT NULL,

CONSTRAINT pk_offices_office_id PRIMARY KEY (office_id),
CONSTRAINT cost_per_hour_positive CHECK (cost_per_hour > 0)
);

CREATE TABLE office_hours (
office_id INT NOT NULL,
day_of_week INT NOT NULL,
start_time TIME,
end_time TIME,

CONSTRAINT pk_office_hours PRIMARY KEY (office_id, day_of_week)
);

CREATE TABLE doctor_availability (
availability_id SERIAL NOT NULL,
doctor_id INT NOT NULL,
day_of_week VARCHAR(14),
availability_date DATE,
specific_date BOOLEAN NOT NULL,
start_time TIME,
end_time TIME, 


CONSTRAINT pk_availability PRIMARY KEY (availability_id)
);

CREATE TABLE admin_office (
admin_id INT NOT NULL,
office_id INT NOT NULL,

CONSTRAINT pk_admin_office PRIMARY KEY (admin_id, office_id)
);

CREATE TABLE notifications (
notification_id SERIAL NOT NULL,
user_id INT NOT NULL,
message VARCHAR NOT NULL,
message_read BOOLEAN NOT NULL,

CONSTRAINT pk_notifications PRIMARY KEY (notification_id)
);



ALTER TABLE doctors
ADD FOREIGN KEY (doctor_id)
REFERENCES users (user_id);

ALTER TABLE patients
ADD FOREIGN KEY (patient_id)
REFERENCES users (user_id);

ALTER TABLE doctors
ADD FOREIGN KEY (office_id)
REFERENCES offices (office_id);

ALTER TABLE patients
ADD FOREIGN KEY (primary_doctor_id)
REFERENCES doctors (doctor_id);

ALTER TABLE reviews
ADD FOREIGN KEY (office_id)
REFERENCES offices (office_id);

ALTER TABLE reviews
ADD FOREIGN KEY (patient_id)
REFERENCES patients (patient_id);

ALTER TABLE reviews
ADD FOREIGN KEY (doctor_id)
REFERENCES doctors (doctor_id);

ALTER TABLE appointments
ADD FOREIGN KEY (office_id)
REFERENCES offices (office_id);

ALTER TABLE appointments
ADD FOREIGN KEY (patient_id)
REFERENCES patients (patient_id);

ALTER TABLE appointments
ADD FOREIGN KEY (doctor_id)
REFERENCES doctors (doctor_id);

ALTER TABLE appointments
ADD FOREIGN KEY (appt_type_id)
REFERENCES appointment_types (appointment_types_id);

ALTER TABLE office_hours
ADD FOREIGN KEY (office_id)
REFERENCES offices (office_id);

ALTER TABLE doctor_availability
ADD FOREIGN KEY (doctor_id)
REFERENCES doctors (doctor_id);

ALTER TABLE admin_office
ADD FOREIGN KEY (admin_id)
REFERENCES users (user_id);

ALTER TABLE admin_office
ADD FOREIGN KEY (office_id)
REFERENCES offices (office_id);

ALTER TABLE notifications
ADD FOREIGN KEY (user_id)
REFERENCES users (user_id);



SELECT * FROM doctors;
SELECT * FROM patients;
SELECT * FROM reviews;
SELECT * FROM appointments;
SELECT * FROM appointment_types;
SELECT * FROM offices;
SELECT * FROM office_hours;
SELECT * FROM doctor_availability;
SELECT * FROM admin_office;
SELECT * FROM notifications;

COMMIT;