package com.martiply.model;


public class IPP {

	private BasicItem basicItem;

	private Store store;

	public void setStore(Store store) {
		this.store = store;
	}

	public Store getStore() {
		return store;
	}

	public void setBasicItem(BasicItem basicItem) {
		this.basicItem = basicItem;
	}

	public BasicItem getBasicItem() {
		return basicItem;
	}

}
