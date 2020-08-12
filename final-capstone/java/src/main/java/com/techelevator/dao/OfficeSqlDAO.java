package com.techelevator.dao;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;
import com.techelevator.model.Office;

@Component
public class OfficeSqlDAO implements OfficeDAO {


	private JdbcTemplate jdbc;

	public OfficeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbc = jdbcTemplate;
	}

	@Override
	public List<Office> getAllOffices() {
		List<Office> officeList = new ArrayList<>();

		String officeSelect = "SELECT * FROM offices";

		SqlRowSet results = jdbc.queryForRowSet(officeSelect);

		while (results.next()) {
			int officeId = results.getInt("office_id");

			String startSelect = "SELECT office_id, day_of_week, start_time FROM office_hours WHERE office_id = ?";
			SqlRowSet startResults = jdbc.queryForRowSet(startSelect, officeId);

			String endSelect = "SELECT office_id, day_of_week, end_time FROM office_hours WHERE office_id = ?";
			SqlRowSet endResults = jdbc.queryForRowSet(endSelect, officeId);
			
			String doctorSelect = "SELECT * FROM doctors WHERE office_id = ?";
			SqlRowSet doctorResults = jdbc.queryForRowSet(doctorSelect, officeId);

			officeList.add(mapRowToOfficeWithHours(results, startResults, endResults, doctorResults));
		}

		return officeList;
	}
	
	@Override
	public Office getOfficeById(Long officeId) {
		Office office = new Office();
		
		String officeSelect = "SELECT * FROM offices WHERE office_id = ?";

		SqlRowSet results = jdbc.queryForRowSet(officeSelect, officeId);

		if (results.next()) {

			String startSelect = "SELECT office_id, day_of_week, start_time FROM office_hours WHERE office_id = ?";
			SqlRowSet startResults = jdbc.queryForRowSet(startSelect, officeId);

			String endSelect = "SELECT office_id, day_of_week, end_time FROM office_hours WHERE office_id = ?";
			SqlRowSet endResults = jdbc.queryForRowSet(endSelect, officeId);
			
			String doctorSelect = "SELECT * FROM doctors WHERE office_id = ?";
			SqlRowSet doctorResults = jdbc.queryForRowSet(doctorSelect, officeId);

			office = mapRowToOfficeWithHours(results, startResults, endResults, doctorResults);
		}

		return office;
	}

	@Override
	public Office getOfficeByDoctor(Long doctorId) {
		Office office = new Office();
		
		String officeSelect = "SELECT o.* FROM offices o INNER JOIN doctors d ON o.office_id = d.office_id WHERE doctor_id = ?";

		SqlRowSet results = jdbc.queryForRowSet(officeSelect, doctorId);

		if (results.next()) {
			int officeId = results.getInt("office_id");

			String startSelect = "SELECT office_id, day_of_week, start_time FROM office_hours WHERE office_id = ?";
			SqlRowSet startResults = jdbc.queryForRowSet(startSelect, officeId);

			String endSelect = "SELECT office_id, day_of_week, end_time FROM office_hours WHERE office_id = ?";
			SqlRowSet endResults = jdbc.queryForRowSet(endSelect, officeId);
			
			String doctorSelect = "SELECT * FROM doctors WHERE office_id = ?";
			SqlRowSet doctorResults = jdbc.queryForRowSet(doctorSelect, officeId);

			office = mapRowToOfficeWithHours(results, startResults, endResults, doctorResults);
		}

		return office;
	}
	
	@Override
	public Office createOffice(Office office) {
		String sqlCreateOffice = "INSERT INTO offices "
								+ "(office_id, name, address, city, state, phone, cost, start_time, end_time) "
								+ "VALUES (?,?,?,?,?,?,?,?,?)";
		jdbc.update(sqlCreateOffice, office.getOfficeId(), office.getOfficeName(), 
						office.getAddress(), office.getCity(), office.getState(),
						office.getPhoneNumber(), office.getCost(), office.getOpenHours(), 
						office.getCloseHours());
		return office;
		
	}

	@Override
	public Office updateOffice(Office office, Long officeId) {
		Office result = office;
		String sqlUpdateOffice = "UPDATE offices "
								+ "SET name = ?, address = ?, "
								+ "city = ?, state = ?, zip_code = ?, phone = ?, "
								+ "cost_per_hour = ? "
								+ "WHERE office_id = ?";
		jdbc.update(sqlUpdateOffice, office.getOfficeName(), office.getAddress(),
						office.getCity(), office.getState(), office.getZipCode(), office.getPhoneNumber(),
						office.getCost(), officeId);
		return result;
	}
	
	@Override
	public Office getOfficeByAdmin(Long adminId) {
		Office office = new Office();
		
		String officeSelect = "SELECT o.* FROM offices o INNER JOIN admin_office a ON o.office_id = a.office_id WHERE a.admin_id = ?";

		SqlRowSet results = jdbc.queryForRowSet(officeSelect, adminId);

		if (results.next()) {
			int officeId = results.getInt("office_id");

			String startSelect = "SELECT office_id, day_of_week, start_time FROM office_hours WHERE office_id = ?";
			SqlRowSet startResults = jdbc.queryForRowSet(startSelect, officeId);

			String endSelect = "SELECT office_id, day_of_week, end_time FROM office_hours WHERE office_id = ?";
			SqlRowSet endResults = jdbc.queryForRowSet(endSelect, officeId);
			
			String doctorSelect = "SELECT * FROM doctors WHERE office_id = ?";
			SqlRowSet doctorResults = jdbc.queryForRowSet(doctorSelect, officeId);

			office = mapRowToOfficeWithHours(results, startResults, endResults, doctorResults);
		}

		return office;
	}
	
	@Override
	public Long getAdminIdbyOfficeId(Long officeId) {
		String sqlGetAdminId = "SELECT u.user_id FROM users u "
							+ "INNER JOIN admin_office a ON u.user_id = a.admin_id "
							+ "INNER JOIN offices o ON a.office_id = o.office_id "
							+ "WHERE o.office_id = ?";
		SqlRowSet results = jdbc.queryForRowSet(sqlGetAdminId, officeId);
		results.next();
		return results.getLong("user_id");
	}

	private Office mapRowToOffice(SqlRowSet results) {
		Office office = new Office();

		office.setOfficeId(results.getLong("office_id"));
		office.setOfficeName(results.getString("name"));
		office.setAddress(results.getString("address"));
		office.setCity(results.getString("city"));
		office.setState(results.getString("state"));
		office.setPhoneNumber(results.getString("phone"));
		office.setCost(results.getBigDecimal("cost_per_hour"));

		return office;
	}

	
	
	private Office mapRowToOfficeWithHours(SqlRowSet officeResults, SqlRowSet startResults, SqlRowSet endResults, SqlRowSet doctorResults) {
		Office office = new Office();

		office.setOfficeId(officeResults.getLong("office_id"));
		office.setOfficeName(officeResults.getString("name"));
		office.setAddress(officeResults.getString("address"));
		office.setCity(officeResults.getString("city"));
		office.setState(officeResults.getString("state"));
		office.setZipCode(officeResults.getString("zip_code"));
		office.setPhoneNumber(officeResults.getString("phone"));
		office.setCost(officeResults.getBigDecimal("cost_per_hour"));
		office.setOpenHours(mapRowToHours(startResults));
		office.setCloseHours(mapRowToHours(endResults));
		office.setDoctorList(mapDoctorsToList(doctorResults));
		
		
		return office;
	}

	private Map<String, LocalTime> mapRowToHours(SqlRowSet results) {
		Map<String, LocalTime> hours = new HashMap<>();

		while (results.next()) {
			int dow = results.getInt("day_of_week");
			Time time = results.getTime(3);
			LocalTime lt = null;
			if (time != null) {
				lt = time.toLocalTime();
			}
			switch (dow) {
			case 1:
				hours.put("Monday", lt);
				break;
			case 2:
				hours.put("Tuesday", lt);
				break;
			case 3:
				hours.put("Wednesday", lt);
				break;
			case 4:
				hours.put("Thursday", lt);
				break;
			case 5:
				hours.put("Friday", lt);
				break;
			case 6:
				hours.put("Saturday", lt);
				break;
			case 7:
				hours.put("Sunday", lt);
				break;
			}
		}

		return hours;
	}

	private List<Doctor> mapDoctorsToList(SqlRowSet results) {
		List<Doctor> drList = new ArrayList<>();
		
		while (results.next()) {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(results.getLong("doctor_id"));
		doctor.setOfficeId(results.getLong("office_id"));
		doctor.setFirstName(results.getString("first_name"));
		doctor.setLastName(results.getString("last_name"));
		drList.add(doctor);
		}
		
		return drList;
	}

}
