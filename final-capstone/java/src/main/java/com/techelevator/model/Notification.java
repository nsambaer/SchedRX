package com.techelevator.model;

public class Notification {

	private Long notificationId;
	private Long userId;
	private String message;
	private boolean isRead;
	

	
	public Notification () {}
	
	public Notification(Long userId, String message, boolean isRead) {
		super();
		this.userId = userId;
		this.message = message;
		this.isRead = isRead;
	}
	public Notification(Long notificationId, Long userId, String message, boolean isRead) {
		super();
		this.notificationId = notificationId;
		this.userId = userId;
		this.message = message;
		this.isRead = isRead;
	}
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
	
	
}
