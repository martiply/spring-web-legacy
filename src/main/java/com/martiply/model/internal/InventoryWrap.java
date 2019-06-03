package com.martiply.model.internal;

import java.util.ArrayList;

public class InventoryWrap {

	
	private int draw;
	private String recordsTotal;
	private String recordsFiltered;
	
	private ArrayList<InventoryItem> data;
	
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
	public void setData(ArrayList<InventoryItem> data) {
		this.data = data;
	}
	
	public ArrayList<InventoryItem> getData() {
		return data;
	}
	
	public int getDraw() {
		return draw;
	}
	
	public String getRecordsFiltered() {
		return recordsFiltered;
	}
	
	public String getRecordsTotal() {
		return recordsTotal;
	}
}
