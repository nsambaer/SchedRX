package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Notification;

@Component
public class NotificationSqlDAO implements NotificationDAO {

	private JdbcTemplate jdbc;
	
	public NotificationSqlDAO(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
	}
	
	@Override
	public List<Notification> getNotifications(Long userId) {
		List<Notification> notifList = new ArrayList<>();
		
		String SqlSelect = "SELECT * FROM notifications WHERE user_id = ? ORDER BY notification_id";
		
		SqlRowSet results = jdbc.queryForRowSet(SqlSelect, userId);
		
		while (results.next()) {
			notifList.add(mapRowToNotification(results));
		}
		
		return notifList;
	}

	@Override
	public boolean markRead(Long notificationId) {
		String SqlUpdate = "UPDATE notifications SET message_read = TRUE WHERE notification_id = ?";
		
		int updated = jdbc.update(SqlUpdate, notificationId);
		
		return updated == 1;
	}
	
	@Override
	public void markAllRead(Long userId) {
		String SqlUpdate = "UPDATE notifications SET message_read = TRUE WHERE user_id = ?";
		
		int updated = jdbc.update(SqlUpdate, userId);

	}

	@Override
	public Notification createNotification(Notification notification) {
		String SqlInsert = "INSERT INTO notifications (user_id, message, message_read) VALUES (?, ?, FALSE)";
		
		jdbc.update(SqlInsert, notification.getUserId(), notification.getMessage());
		
		return notification;
	}

	
	private Notification mapRowToNotification(SqlRowSet results) {
		Notification notif = new Notification();
		
		notif.setNotificationId(results.getLong("notification_id"));
		notif.setUserId(results.getLong("user_id"));
		notif.setMessage(results.getString("message"));
		notif.setRead(results.getBoolean("message_read"));
		
		return notif;
	}
	
	
}
