package com.martiply.model;

import java.util.Comparator;

import com.martiply.model.Coupon;
import com.martiply.model.Store;

public class FaveItem {
	
	private Coupon catalog;
	private Store storeInfo;
	
	public Coupon getCatalog() {
		return catalog;
	}
	
	public void setCatalog(Coupon catalog) {
		this.catalog = catalog;
	}
	
	public void setStoreInfo(Store storeInfo) {
		this.storeInfo = storeInfo;
	}
	

	
	public Store getStoreInfo() {
		return storeInfo;
	}

	
	public static Comparator<FaveItem> FaveItemDateComparator = new Comparator<FaveItem>() {

		@Override
		public int compare(FaveItem left, FaveItem right) {

			if (left.getCatalog().getEndSec() > right.getCatalog().getEndSec()) {
				return 1;
			} else if (left.getCatalog().getEndSec() < right.getCatalog().getEndSec()) {
				return -1;
			} else
				return 0;
		}
	};
}
