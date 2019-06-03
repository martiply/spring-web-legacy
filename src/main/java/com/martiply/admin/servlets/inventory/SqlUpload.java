package com.martiply.admin.servlets.inventory;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.martiply.db.tables.TableStandardApparel;
import com.martiply.model.ApparelExtension;
import com.martiply.model.BasicItem;

public class SqlUpload {
	
	public static final int LENGTH_COMPLETE = 21;
	public static final int LENGTH_DEFAULT = 12;
	
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");

	BasicItem item = new BasicItem();
	ApparelExtension apparelExtension = new ApparelExtension();
		
	/**
	 * 
	 * @param ownerId
	 * @param isEdit true if editing existing item, false for new item
	 */
	public SqlUpload(int ownerId) {
		item.setOwnerId(ownerId);
	}
	
	public static String[] csvItem(BasicItem item){
		String[] p = new String[LENGTH_COMPLETE];
		
		p[0] = item.getIdType();
		p[1] = item.getGtin();
		p[2] = item.getIdCustom();
		p[3] = item.getBrand();
		p[4] = item.getName();
		p[5] = item.getCategory();
		p[6] = Float.toString(item.getPrice());
		p[7] = item.getCondition();
		p[8] = item.getDescription();
		p[9] = item.getUrl();
		p[10] = item.getSalePrice() > 0 ? Float.toString(item.getSalePrice()) : null;
		p[11] = item.getSaleStart() > 0 ? formatter.print(item.getSaleStart() * 1000) : null;
		p[12] = item.getSaleEnd() > 0 ? formatter.print(item.getSaleEnd() * 1000) : null;
		
		ApparelExtension apparelExtension = item.getApparelExtension();
		if (apparelExtension != null){
			p[13] = apparelExtension.getGender();
			p[14] = apparelExtension.getAge();
			p[15] = apparelExtension.getSizeSystem();
			p[16] = apparelExtension.getSize();
			p[17] = apparelExtension.getColor();
			p[18] = apparelExtension.getFeature();
			p[19] = apparelExtension.getMaterial();
			p[20] = apparelExtension.getGroupId();			
		}		
		return p;
	
	}
	
	public String testBasic(String idType, String gtin, String idCustom, String brand,  String name, String category, String stringPrice, String condition, String description, String url){
		ArrayList<String> errors = new ArrayList<String>();
		
		if (idType == null || idType.trim().isEmpty()){
			errors.add("idType empty");
		}else{
			if (!idType.equalsIgnoreCase("custom") && !idType.equalsIgnoreCase("gtin")){
				errors.add("invalid idType");
			}
		}
		
		
		if (brand == null || brand.trim().isEmpty()){
			errors.add("brand empty ");
		}else{
			if (brand.length() > 20){
				errors.add("brand longer than 20 characters");
			}
		}
			
		if (name == null || name.trim().isEmpty()){
			errors.add("name empty" );
		}else{
			if (name.length() > 70){
				errors.add("name longer than 70 characters");
			}
		}
		
		
		if (category == null || category.trim().isEmpty()){
			errors.add("category empty ");
		}else{
			if (category.length() > 200){
				errors.add("category longer than 200 characters");
			}
		}
				
		if (condition == null || condition.trim().isEmpty()){
			errors.add("condition empty ");
		}else{
			if (!condition.equalsIgnoreCase("used") && !condition.equalsIgnoreCase("new")){
				errors.add("invalid condition");
			}
		}
		
		switch(idType){				
			case BasicItem.ID_TYPE_GTIN: 			
				if (gtin.isEmpty()  || !isNumeric(gtin) || gtin.length() != 12 && gtin.length() != 13){
					errors.add("invalid gtin");
				}
				break;
		}
		
		
		float price = 0.0f;

		if (stringPrice == null || stringPrice.trim().isEmpty()){
			errors.add("price empty");
		}else{
			try {			
				price = Float.parseFloat(stringPrice.trim());
			}catch(NumberFormatException e){
				errors.add("price not numeric");
			}
		}
		
		
		if (url != null && !url.trim().isEmpty()){
			if (url.startsWith("http://")){
				url = url.substring(7, url.length());
			}
			
		}
		
		if (!errors.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (String error : errors){
				sb.append(error);
				sb.append(", ");
				
			}
			String error = sb.toString();
		    return error.substring(0, error.length() - 2);
		}else{		
			item.setIdType(idType);
			item.setGtin(gtin);
			item.setIdCustom(idCustom);
			item.setBrand(brand);
			item.setName(name);
			item.setCategory(category);
			item.setCondition(condition.toLowerCase());
			item.setPrice(price);
			item.setDescription(description);
			item.setUrl(url);
			return null;
		}
	}
	
	public String testSaleCsv(String salePriceString, String saleStartString, String saleEndString){
		int nullCount = 0;

		ArrayList<String> errors = new ArrayList<String>();

		float salePrice = 0.0f;

		if (salePriceString == null || salePriceString.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("sale price empty");
		}else{
			try {			
				salePrice = Float.parseFloat(salePriceString.trim());
			}catch(NumberFormatException e){
				errors.add("sale price not numeric");
			}
		}
		
		if (saleStartString == null || saleStartString.trim().isEmpty()){
			nullCount = nullCount + 1;			
			errors.add("sale start date empty");
		}
		
		if (saleEndString == null || saleEndString.trim().isEmpty()){
			nullCount = nullCount + 1;			
			errors.add("sale end date empty");
		}
		
		if(nullCount == 3){
			return null;
		}
		
		Integer saleStart = null, saleEnd = null;
		try{
			DateTime dt = formatter.parseDateTime(saleStartString);
			saleStart = (int)(dt.getMillis() / 1000);
		}catch(IllegalArgumentException e){
			errors.add("(sale start date) invalid format");
		}
		
		try{
			DateTime dt = formatter.parseDateTime(saleEndString);
			saleEnd = (int)(dt.getMillis() / 1000);
		}catch(IllegalArgumentException e){
			errors.add("(sale end date) invalid format");
		}
			
		if (errors.isEmpty()){
			item.setSalePrice(salePrice);
			item.setSaleStart(saleStart);
			item.setSaleEnd(saleEnd);
			return null;
		}else{
			StringBuilder sb = new StringBuilder();
			for (String error : errors){
				sb.append(error);
				sb.append(", ");
				
			}
			String error = sb.toString();
		    return error.substring(0, error.length() - 2);
		}
	}
	
	/**
	 * 
	 * @param salePriceString sale price (in float) String
	 * @param saleStartString sale start date (in int sec) String
	 * @param saleEndString sale end date (in int sec) String
	 * @return
	 */
	
	public String testSale(String salePriceString, String saleStartString, String saleEndString){
		int nullCount = 0;

		ArrayList<String> errors = new ArrayList<String>();

		float salePrice = 0.0f;

		if (salePriceString == null || salePriceString.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("sale price empty");
		}else{
			try {			
				salePrice = Float.parseFloat(salePriceString.trim());
			}catch(NumberFormatException e){
				errors.add("sale price not numeric");
			}
		}
				
		if (saleStartString == null || saleStartString.trim().isEmpty()){
			nullCount = nullCount + 1;			
			errors.add("sale start date empty");
		}
		
		if (saleEndString == null || saleEndString.trim().isEmpty()){
			nullCount = nullCount + 1;			
			errors.add("sale end date empty");
		}
		
		if (nullCount == 3){
			return null;
		}
		
		Integer saleStartSec = null, saleEndSec = null;
		try{
			saleStartSec  = Integer.parseInt(saleStartString);
		}catch (NumberFormatException e){
			errors.add("(sale start date) invalid format");
		}
		
		try{
			saleEndSec  = Integer.parseInt(saleEndString);
		}catch (NumberFormatException e){
			errors.add("(sale end date) invalid format");
		}
		
		if (saleEndSec != null && saleStartSec != null){
//			int currentSec = (int)(System.currentTimeMillis() / 1000);
//			if (!isEdit && (saleStartSec < currentSec || saleEndSec < currentSec)){
//				errors.add("both sale start and end dates have to be later than today");
//			}

			if (saleEndSec < saleStartSec){
				errors.add("sale end date has to be later than start date");
			}
		}

		
		if (errors.isEmpty()){
			item.setSalePrice(salePrice);
			item.setSaleStart(saleStartSec);
			item.setSaleEnd(saleEndSec);
			return null;
		}else{
			StringBuilder sb = new StringBuilder();
			for (String error : errors){
				sb.append(error);
				sb.append(", ");
				
			}
			String error = sb.toString();
		    return error.substring(0, error.length() - 2);
		}
	}
	
	/**
	 * Test params if item is apparel. Returns error string or null if item not apparel or no error registered (which means apparelItem has to be checked later)
	 * @param gender
	 * @param age
	 * @param sizeSystem
	 * @param size
	 * @param color
	 * @param feature
	 * @param material
	 * @param groupID
	 * @return
	 */
	public String testApparel(String gender, String age, String sizeSystem, String size, String color, String feature, String material, String groupID){
		int nullCount = 0;
		ArrayList<String> errors = new ArrayList<String>();

		if (gender == null || gender.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("gender empty");
		}else{
			gender = gender.toLowerCase();
			if (!gender.equalsIgnoreCase(TableStandardApparel.GENDER_MALE)  && !gender.equalsIgnoreCase(TableStandardApparel.GENDER_FEMALE) && !gender.equalsIgnoreCase(TableStandardApparel.GENDER_UNISEX)){
				errors.add(String.format("gender has to be {%s/%s/%s}", TableStandardApparel.GENDER_MALE, TableStandardApparel.GENDER_FEMALE, TableStandardApparel.GENDER_UNISEX));
			}			
		}
		
		if (age == null || age.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("age empty");
		}else{
			age = age.toLowerCase();
			if (!age.equalsIgnoreCase(TableStandardApparel.AGE_ADULT) && !age.equalsIgnoreCase(TableStandardApparel.AGE_KIDS) && !age.equalsIgnoreCase(TableStandardApparel.AGE_TODDLER)){
				errors.add(String.format("age has to be{%s/%s/%s}", TableStandardApparel.AGE_ADULT, TableStandardApparel.AGE_KIDS, TableStandardApparel.AGE_TODDLER));
			}			
		}
		
		if (sizeSystem == null || sizeSystem.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("sizeSystem empty");
		}else{
			sizeSystem = sizeSystem.toUpperCase();
			if (!sizeSystem.equalsIgnoreCase(TableStandardApparel.SIZE_SYSTEM_SML) && !sizeSystem.equalsIgnoreCase(TableStandardApparel.SIZE_SYSTEM_US) && !sizeSystem.equalsIgnoreCase(TableStandardApparel.SIZE_SYSTEM_UK) && 
					!sizeSystem.equalsIgnoreCase(TableStandardApparel.SIZE_SYSTEM_EU) && !sizeSystem.equalsIgnoreCase(TableStandardApparel.SIZE_SYSTEM_JP)){
				errors.add(String.format("sizeSystem has to be {%s/%s/%s/%s/%s}", TableStandardApparel.SIZE_SYSTEM_SML, TableStandardApparel.SIZE_SYSTEM_US, TableStandardApparel.SIZE_SYSTEM_UK, TableStandardApparel.SIZE_SYSTEM_EU, TableStandardApparel.SIZE_SYSTEM_JP));
			}			
		}
		
		if (size == null || size.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("size empty");
		}else{
			if (size.length() > 20 ){
				errors.add("size longer than 20 characters");
			}
		}
		
		if (color == null || color.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("color empty");
		}else{
			if (color.length() > 15){
				errors.add("size longer than 15 characters");
			}
		}
		
		if (feature != null && feature.trim().isEmpty() && feature.length() > 60){
			errors.add("feature longer than 60 characters");
		}
		
		if (material == null || material.trim().isEmpty()){
			nullCount = nullCount + 1;
			errors.add("material empty");
		}else{
			if (material.length() > 40){
				errors.add("material length longer than 40 characters");
			}
		}
		
		if (groupID != null && !groupID.trim().isEmpty() && groupID.length() > 15){
			errors.add("groupId length longer than 15 characters");
		}
		
		if (nullCount == 6){
			apparelExtension = null;
			return null;
		}else{			
			if (errors.isEmpty()){
				apparelExtension.setGender(gender);
				apparelExtension.setAge(age);
				apparelExtension.setSizeSystem(sizeSystem);
				apparelExtension.setSize(size);
				apparelExtension.setColor(color);
				apparelExtension.setFeature(feature);
				apparelExtension.setMaterial(material);
				apparelExtension.setGroupId(groupID);
				item.setApparelExtension(apparelExtension);
				return null;
			}else{
				StringBuilder sb = new StringBuilder();
				for (String error : errors){
					sb.append(error);
					sb.append(", ");
					
				}
				String error = sb.toString();
			    return error.substring(0, error.length() - 2);
			}		
		}	
	}
	
	public static boolean isNumeric(String str){
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}

}
