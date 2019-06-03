package com.martiply.model;

public class Owner {

	private int ownerId;
	private String email;
	private float credit;
	private long lastPaid;
	private String name;
	private String address;
	private String phone;
	private String paymentNote;
	private String currency;
	private long uid;

	public long getUid() {
		return uid;
	}
	
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}
	
	public float getCredit() {
		return credit;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastPaid(long lastPaid) {
		this.lastPaid = lastPaid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}



	public String getEmail() {
		return email;
	}

	public long getLastPaid() {
		return lastPaid;
	}

	public String getName() {
		return name;
	}

	public int getOwnerId() {
		return ownerId;
	}

}
