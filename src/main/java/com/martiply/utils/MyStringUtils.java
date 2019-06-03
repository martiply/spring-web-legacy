package com.martiply.utils;

public class MyStringUtils {
	
	public static String makePrettyCurrency(String currencyCode){
		switch (currencyCode) {
		case "IDR":
			return "Rp";
		default:
			return "￥";
		}		
	}

}
