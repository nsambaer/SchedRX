package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Notification;

public interface NotificationDAO {

	public List<Notification> getNotifications(Long userId);
	
	public boolean markRead(Long notificationId);
	
	public void markAllRead(Long userId);
	
	public Notification createNotification(Notification notification);	
}
