package com.martiply.model;


public class NotificationItem {

	private int notificationId;
	private String message;
	private String title;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

}
