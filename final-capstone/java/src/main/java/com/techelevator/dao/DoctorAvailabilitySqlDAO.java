package com.techelevator.dao;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.DoctorAvailability;

@Component
public class DoctorAvailabilitySqlDAO implements DoctorAvailabilityDAO {

	private JdbcTemplate jdbc;

	public DoctorAvailabilitySqlDAO(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}

	@Override
	public DoctorAvailability getDoctorAvailabilityForMonth(Long doctorId, int month, int year) {

		String SqlSelectRegular = "SELECT * FROM doctor_availability WHERE doctor_id = ? AND specific_date = false;";
		String SqlSelectSpecific = "SELECT * FROM doctor_availability WHERE doctor_id = ? AND specific_date = true";
		String SqlSelectAppointment = "SELECT appt_time, appt_date FROM appointments WHERE doctor_id = ? AND EXTRACT(MONTH FROM (appt_date)) = ? AND EXTRACT(YEAR FROM (appt_date)) = ?";

		SqlRowSet regularResults = jdbc.queryForRowSet(SqlSelectRegular, doctorId);
		SqlRowSet specificResults = jdbc.queryForRowSet(SqlSelectSpecific, doctorId);
		SqlRowSet appointmentResults = jdbc.queryForRowSet(SqlSelectAppointment, doctorId, month, year);

		return mapRowsToAvailability(doctorId, month, year, regularResults, specificResults, appointmentResults);

	}

	@Override
	public DoctorAvailability setRegularAvailability(DoctorAvailability regularAvailability) {
		String SqlInsertAvail = "INSERT INTO doctor_availability (doctor_id, day_of_week, specific_date, start_time, end_time) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Set<String> dow = regularAvailability.getRegularOpenHours().keySet();
		Map<String, LocalTime> startTimes = regularAvailability.getRegularOpenHours();
		Map<String, LocalTime> closeTimes = regularAvailability.getRegularCloseHours();

		for (String dayOfWeek : dow) {
			jdbc.update(SqlInsertAvail, regularAvailability.getDoctorId(), dayOfWeek, false, startTimes.get(dayOfWeek),
					closeTimes.get(dayOfWeek));
		}

		return regularAvailability;
	}

	@Override
	public DoctorAvailability setSpecificAvailability(DoctorAvailability specificAvailability) {
		String SqlInsertAvail = "INSERT INTO doctor_availability (doctor_id, availability_date, specific_date, start_time, end_time) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Set<LocalDate> dates = specificAvailability.getSpecificOpenHours().keySet();
		Map<LocalDate, LocalTime> startTimes = specificAvailability.getSpecificOpenHours();
		Map<LocalDate, LocalTime> closeTimes = specificAvailability.getSpecificCloseHours();

		for (LocalDate date : dates) {
			jdbc.update(SqlInsertAvail, specificAvailability.getDoctorId(), date, true, startTimes.get(date),
					closeTimes.get(date));
		}

		return specificAvailability;
	}

	@Override
	public DoctorAvailability updateRegularAvailability(DoctorAvailability regularAvailability) {
		String SqlUpdateAvail = "UPDATE doctor_availability SET start_time = ?, end_time = ? "
				+ "WHERE doctor_id = ? AND day_of_week = ?";

		Set<String> dow = regularAvailability.getRegularOpenHours().keySet();
		Map<String, LocalTime> startTimes = regularAvailability.getRegularOpenHours();
		Map<String, LocalTime> closeTimes = regularAvailability.getRegularCloseHours();

		for (String dayOfWeek : dow) {
			jdbc.update(SqlUpdateAvail, startTimes.get(dayOfWeek), closeTimes.get(dayOfWeek),
					regularAvailability.getDoctorId(), dayOfWeek);
		}

		return regularAvailability;
	}

	@Override
	public DoctorAvailability updateSpecificAvailability(DoctorAvailability specificAvailability) {
		String SqlUpdateAvail = "UPDATE doctor_availability SET start_time = ?, end_time = ? "
				+ "WHERE doctor_id = ? AND availability_date = ?";

		Set<LocalDate> dates = specificAvailability.getSpecificOpenHours().keySet();
		Map<LocalDate, LocalTime> startTimes = specificAvailability.getSpecificOpenHours();
		Map<LocalDate, LocalTime> closeTimes = specificAvailability.getSpecificCloseHours();

		for (LocalDate date : dates) {
			jdbc.update(SqlUpdateAvail, startTimes.get(date), closeTimes.get(date), specificAvailability.getDoctorId(),
					date);
		}

		return specificAvailability;
	}

	@Override
	public void deleteSpecificAvailability(DoctorAvailability specificAvailability) {
		String SqlDeleteAvail = "DELETE FROM doctor_availability WHERE doctor_id = ? AND availability_date = ?";

		Set<LocalDate> dates = specificAvailability.getSpecificOpenHours().keySet();

		for (LocalDate date : dates) {
			jdbc.update(SqlDeleteAvail, specificAvailability.getDoctorId(), date);
		}

	}

	private DoctorAvailability mapRowsToAvailability(Long doctorId, int month, int year, SqlRowSet regularResults,
			SqlRowSet specificResults, SqlRowSet appointmentResults) {
		DoctorAvailability drAvailability = new DoctorAvailability();
		Map<LocalDate, List<LocalTime>> availability = new HashMap<>();
		List<LocalDate> dateList = generateDateList(month, year);

		for (LocalDate date : dateList) {
			LocalTime regularOpenHour = mapRegularOpenToTime(regularResults, date);
			LocalTime regularCloseHour = mapRegularCloseToTime(regularResults, date);
			LocalTime specificOpenHour = mapSpecificOpenToTime(specificResults, date);
			LocalTime specificCloseHour = mapSpecificCloseToTime(specificResults, date);
			List<LocalTime> appointments = mapRowsToAppts(appointmentResults, date);
			if (specificOpenHour == null) {
				availability.put(date, null);
			} else if (specificOpenHour.compareTo(LocalTime.MAX) != 0) {// if no specific hours were in the database for
																		// this
				// date, this will return false and we use the
				// regular hours
				availability.put(date, getLocalTimeList(specificOpenHour, specificCloseHour, appointments));
			} else {
				availability.put(date, getLocalTimeList(regularOpenHour, regularCloseHour, appointments));
			}
		}

		drAvailability.setDoctorId(doctorId);
		drAvailability.setAvailability(availability);
		return drAvailability;

	}

	// generates a list of dates for the specific month
	private List<LocalDate> generateDateList(int month, int year) {
		List<LocalDate> dateList = new ArrayList<>();
		try {
			for (int x = 1; x < 32; x++) {
				dateList.add(LocalDate.of(year, month, x));
			}
		} catch (DateTimeException e) {
			// when x gets to an invalid number(ie 30 for February or 31 for a month like
			// September an exception will be thrown
			// and this catch block will eat it, leaving us with a list of all valid dates
			// in the month
		}

		return dateList;
	}

	private List<LocalTime> getLocalTimeList(LocalTime openTime, LocalTime closeTime, List<LocalTime> appointments) {
		List<LocalTime> timeList = new ArrayList<>();

		if (openTime == null) {
			return null;
		}
		int openHour = openTime.getHour();
		int closeHour = closeTime.getHour();

		for (int x = openHour; x <= closeHour; x++) {
			boolean conflict = false;
			LocalTime time = LocalTime.of(x, 0);
			for (LocalTime appointment : appointments) {
				if (appointment.compareTo(time) == 0) {
					conflict = true;
				}
			}
			if (!conflict) {
				timeList.add(time);
			}
		}

		return timeList;
	}

	public LocalTime mapRegularOpenToTime(SqlRowSet results, LocalDate date) {
		LocalTime lt = null;

		results.beforeFirst();

		while (results.next()) {
			String dayOfWeek = results.getString("day_of_week").toUpperCase();
			if (dayOfWeek.equals(date.getDayOfWeek().toString())) {
				Time time = results.getTime("start_time");
				if (time != null) {
					lt = time.toLocalTime();
				}
			}
		}

		return lt;
	}

	public LocalTime mapRegularCloseToTime(SqlRowSet results, LocalDate date) {
		LocalTime lt = null;

		results.beforeFirst();

		while (results.next()) {
			String dayOfWeek = results.getString("day_of_week").toUpperCase();
			if (dayOfWeek.equals(date.getDayOfWeek().toString())) {
				Time time = results.getTime("end_time");
				if (time != null) {
					lt = time.toLocalTime();
				}
			}
		}

		return lt;

	}

	public LocalTime mapSpecificOpenToTime(SqlRowSet results, LocalDate date) {
		LocalTime lt = LocalTime.MAX; // this is a placeholder, also used to determine if a specific time exists in
										// the database.
		// if no specific time exists in the database for this date then the time
		// returned is LocalTime.MAX, which is specific and won't be in the database

		results.beforeFirst();

		while (results.next()) {
			LocalDate resultsDate = results.getDate("availability_date").toLocalDate();
			if (date.compareTo(resultsDate) == 0) {
				Time time = results.getTime("start_time");
				if (time != null) {
					lt = time.toLocalTime();
				} else {
					lt = null;
				}

			}
		}

		return lt;
	}

	public LocalTime mapSpecificCloseToTime(SqlRowSet results, LocalDate date) {
		LocalTime lt = LocalTime.MAX;

		results.beforeFirst();

		while (results.next()) {
			LocalDate resultsDate = results.getDate("availability_date").toLocalDate();
			if (date.compareTo(resultsDate) == 0) {
				Time time = results.getTime("end_time");
				if (time != null) {
					lt = time.toLocalTime();
				} else {
					lt = null;
				}

			}
		}

		return lt;
	}

	private List<LocalTime> mapRowsToAppts(SqlRowSet results, LocalDate date) {
		List<LocalTime> appointments = new ArrayList<>();

		results.beforeFirst();

		while (results.next()) {
			LocalDate resultsDate = results.getDate("appt_date").toLocalDate();
			if (date.compareTo(resultsDate) == 0) {
				LocalTime lt = results.getTime("appt_time").toLocalTime();
				appointments.add(lt);
			}
		}

		return appointments;
	}

//	private DoctorAvailability mapRowsToAvailability(Long doctorId, SqlRowSet regularResults, SqlRowSet specificResults) {
//		DoctorAvailability drAvailability = new DoctorAvailability();
//		
//		drAvailability.setDoctorId(doctorId);
//		drAvailability.setRegularOpenHours(mapRegularOpenToMap(regularResults));
//		drAvailability.setRegularCloseHours(mapRegularCloseToMap(regularResults));
//		drAvailability.setSpecificOpenHours(mapSpecificOpenToMap(specificResults));
//		drAvailability.setSpecificCloseHours(mapSpecificCloseToMap(specificResults));
//		return drAvailability;
//	}
}
