package com.martiply.db.tables;

public class TableOwner {
	
	public static final String SCHEMA = "main";
	
	public static final String TABLE_NAME = "owner";
	public static final String OWNER_ID = "ownerId";
	public static final String EMAIL = "email";
	public static final String CREDIT = "credit";
	public static final String LAST_PAID = "lastPaid";
	public static final String NAME = "name";	
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String PAYMENT_NOTE = "paymentNote";
	public static final String CURRENCY = "currency";
	public static final String UID = TableUser.UID;

	
	public static final String OWNER_ID_F = TABLE_NAME + "." + OWNER_ID;
	public static final String EMAIL_F = TABLE_NAME + "." + EMAIL;
	public static final String CREDIT_F = TABLE_NAME + "." + CREDIT;
	public static final String LAST_PAID_F = TABLE_NAME + "." + LAST_PAID;
	public static final String NAME_F = TABLE_NAME + "." + NAME;	
	public static final String ADDRESS_F = TABLE_NAME + "." + ADDRESS;
	public static final String PHONE_F = TABLE_NAME + "." + PHONE;
	public static final String PAYMENT_NOTE_F = TABLE_NAME + "." + PAYMENT_NOTE;
	public static final String CURRENCY_F = TABLE_NAME + "." + CURRENCY;
	public static final String UID_F = TABLE_NAME + "." + UID;
	

}
