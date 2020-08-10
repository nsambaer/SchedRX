package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Notification;

public interface NotificationDAO {

	public List<Notification> getRecentNotifications(Long userId);
	
	public boolean markRead(Long notificationId);
	
	public Notification createNotification(Notification notification);	
}
