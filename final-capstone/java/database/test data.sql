BEGIN TRANSACTION;

INSERT INTO offices (name, address, city, state, phone, cost_per_hour)
VALUES ('Office Alpha', '555 Main St', 'Lansing', 'MI', '5175555678', 90.99);

INSERT INTO offices (name, address, city, state, phone, cost_per_hour)
VALUES ('Office Bravo', '550 Doctor St', 'Lansing', 'MI', '5175556789', 105.99);

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

INSERT INTO users (username, password_hash, role)
VALUES ('doctorC', 'hash', 'doctor');
INSERT INTO users (username, password_hash, role)
VALUES ('doctorD', 'hash', 'doctor');
INSERT INTO users (username, password_hash, role)
VALUES ('doctorE', 'hash', 'doctor');
INSERT INTO users (username, password_hash, role)
VALUES ('doctorF', 'hash', 'doctor');

INSERT INTO users (username, password_hash, role)
VALUES ('patientG', 'hash', 'patient');
INSERT INTO users (username, password_hash, role)
VALUES ('patientH', 'hash', 'patient');
INSERT INTO users (username, password_hash, role)
VALUES ('patientI', 'hash', 'patient');
INSERT INTO users (username, password_hash, role)
VALUES ('patientJ', 'hash', 'patient');

INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorC'), 'Doctor', 'Charlie', (SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorD'), 'Doctor', 'Delta', (SELECT office_id FROM offices WHERE name ILIKE 'Office Alpha'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorE'), 'Doctor', 'Echo', (SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'));
INSERT INTO doctors (doctor_id, first_name, last_name, office_id)
VALUES ((SELECT user_id FROM users WHERE username ILIKE 'doctorF'), 'Doctor', 'Foxtrot', (SELECT office_id FROM offices WHERE name ILIKE 'Office Bravo'));


SELECT * FROM offices;
SELECT * FROM office_hours;
SELECT * FROM users;
SELECT * FROM doctors;
SELECT * FROM patients;

ROLLBACK;