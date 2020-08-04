package com.techelevator.dao;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

			officeList.add(mapRowToOfficeWithHours(results, startResults, endResults));
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

			office = mapRowToOfficeWithHours(results, startResults, endResults);
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

			office = mapRowToOfficeWithHours(results, startResults, endResults);
		}

		return office;
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

	private Office mapRowToOfficeWithHours(SqlRowSet officeResults, SqlRowSet startResults, SqlRowSet endResults) {
		Office office = new Office();

		office.setOfficeId(officeResults.getLong("office_id"));
		office.setOfficeName(officeResults.getString("name"));
		office.setAddress(officeResults.getString("address"));
		office.setPhoneNumber(officeResults.getString("phone"));
		office.setCost(officeResults.getBigDecimal("cost_per_hour"));
		office.setOpenHours(mapRowToHours(startResults));
		office.setCloseHours(mapRowToHours(endResults));

		return office;
	}

	private Map<String, LocalTime> mapRowToHours(SqlRowSet results) {
		Map<String, LocalTime> hours = new HashMap<>();

		while (results.next()) {
			int dow = results.getInt("day_of_week");
			switch (dow) {
			case 1:
				hours.put("Monday", results.getTime(3).toLocalTime());
				break;
			case 2:
				hours.put("Tuesday", results.getTime(3).toLocalTime());
				break;
			case 3:
				hours.put("Wednesday", results.getTime(3).toLocalTime());
				break;
			case 4:
				hours.put("Thursday", results.getTime(3).toLocalTime());
				break;
			case 5:
				hours.put("Friday", results.getTime(3).toLocalTime());
				break;
			case 6:
				hours.put("Saturday", results.getTime(3).toLocalTime());
				break;
			case 7:
				hours.put("Sunday", results.getTime(3).toLocalTime());
				break;
			}
		}

		return hours;
	}

}
