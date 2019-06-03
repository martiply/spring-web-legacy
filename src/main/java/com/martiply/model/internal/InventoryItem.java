package com.martiply.model.internal;

public class InventoryItem {
	
	private String name;
	private String Brand;
	private String condition;
	
	
	public String getBrand() {
		return Brand;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBrand(String brand) {
		Brand = brand;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
