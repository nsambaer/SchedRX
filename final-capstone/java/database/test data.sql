BEGIN TRANSACTION;

INSERT INTO offices (name, address, city, state, zip_code, phone, cost_per_hour)
VALUES ('Office Alpha', '555 Main St', 'Lansing', 'MI', '48912', '517-555-5678', 90.99);

INSERT INTO offices (name, address, city, state, zip_code, phone, cost_per_hour)
VALUES ('Office Bravo', '550 Doctor St', 'Lansing', 'MI', '48912', '517-555-6789', 105.99);

INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 1, '09:00', '17:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 2, '09:00', '17:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 3, '09:00', '17:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 4, '09:00', '17:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 5, '09:00', '17:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 6, null, null);
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), 7, null, null);

INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 1, '08:00', '16:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 2, '08:00', '16:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 3, '08:00', '16:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 4, '08:00', '16:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 5, '08:00', '16:00');
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 6, null, null);
INSERT INTO office_hours (office_id, day_of_week, start_time, end_time)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), 7, null, null);


INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorC'), 'Doctor', 'Charlie', (SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorD'), 'Doctor', 'Delta', (SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorE'), 'Doctor', 'Echo', (SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorF'), 'Doctor', 'Foxtrot', (SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'));

INSERT INTO patients (patient_id, first_name, last_name, phone, address, city, state, zip_code, primary_doctor_id, date_of_birth)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'testPatient'), 'Test', 'Patient', '517-555-7453', '145 Test Avenue', 'Mason', 'MI', '48912',
        NULL, '1991-08-12');
INSERT INTO patients (patient_id, first_name, last_name, phone, address, city, state, zip_code, primary_doctor_id, date_of_birth)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'patientG'), 'Patient', 'Golf', '517-555-7913', '123 Boulevard Avenue', 'Dewitt', 'MI', '48912',
        (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), '1993-06-12');
INSERT INTO patients (patient_id, first_name, last_name, phone, address, city, state, zip_code, primary_doctor_id, date_of_birth)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'patientH'), 'Patient', 'Hotel', '517-555-4561', '456 Grand Street', 'Grand Ledge', 'MI', '48912',
        (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), '1997-01-04');
INSERT INTO patients (patient_id, first_name, last_name, phone, address, city, state, zip_code, primary_doctor_id, date_of_birth)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'patientI'), 'Patient', 'India', '517-555-3510', '6546 Spring Lake Drive', 'Okemos', 'MI', '48864',
        (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Delta'), '1998-04-22');
INSERT INTO patients (patient_id, first_name, last_name, phone, address, city, state, zip_code, primary_doctor_id, date_of_birth)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'patientJ'), 'Patient', 'Juliet', '517-555-3197', '123 Haslett Road', 'Haslett', 'MI', '48912', 
        (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Echo'), '2000-04-12');

INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Monday', null, false, '09:00', '17:00');
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Tuesday', null, false, '09:00', '17:00');
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Wednesday', null, false, '09:00', '12:00');
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Thursday', null, false, '09:00', '17:00');
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Friday', null, false, '09:00', '17:00');
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Saturday', null, false, null, null);
INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 'Sunday', null, false, null, null);

INSERT INTO doctor_availability (doctor_id, day_of_week, availability_date, specific_date, start_time, end_time) 
VALUES ((SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), null, '2020-08-04', true, null, null);

INSERT INTO appointment_types (appointment_type)
VALUES ('Office visit');
INSERT INTO appointment_types (appointment_type)
VALUES ('Wellness Physical');
INSERT INTO appointment_types (appointment_type)
VALUES ('Pre-travel consultation');
INSERT INTO appointment_types (appointment_type)
VALUES ('Blood Draw/Vaccinations');

SELECT * FROM patients;

INSERT INTO appointments (office_id, doctor_id, patient_id, appt_date, appt_time, appt_mod_date, appt_mod_time, virtual, visit_reason, appt_type_id) 
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'), (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 
        (SELECT patient_id FROM patients WHERE first_name ILIKE 'Patient' AND last_name ILIKE 'Golf'), 
        '2020-08-17', '10:00', '2020-08-04', '14:15', false, 'I have an ouchie', (SELECT appointment_types_id FROM appointment_types WHERE appointment_type ILIKE 'Office visit'));

INSERT INTO reviews (office_id, doctor_id, patient_id, title, rating, description, comments)
VALUES ((SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'), (SELECT doctor_id FROM doctors WHERE first_name ILIKE 'Doctor' AND last_name ILIKE 'Charlie'), 
        (SELECT patient_id FROM patients WHERE first_name ILIKE 'Patient' AND last_name ILIKE 'Golf'), 'Best Doctor Ever', 5, 'What a great visit. The doctor gave me loopy pills!', null);
        
        

INSERT INTO admin_office (admin_id, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'admin'), (SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'));


SELECT * FROM offices;
SELECT * FROM office_hours;
SELECT * FROM users;
SELECT * FROM doctors;
SELECT * FROM patients;
SELECT * FROM doctor_availability;
SELECT * FROM appointment_types;
SELECT * FROM appointments;
SELECT * FROM reviews;
SELECT * FROM admin_office;


COMMIT;