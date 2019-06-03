package com.martiply.model;

public class BasicItem {
	/*
	 * This class is used for table Standard It will be extended to include extras (food info, electronic info)
	 */
	public static final String ID_TYPE_GTIN = "gtin";
	public static final String ID_TYPE_NO_GTIN = "nogtin";
	public static final String ID_TYPE_CUSTOM = "custom";

	private String id;
	private int ownerId;
	private String idCustom;
	private String gtin;
	private String description;
	private String name;
	private String category;
	private String brand;
	private String idType;
	private int hits;
	private int imgNum;
	private float price;
	private String condition;
	private float salePrice;
	private int saleStart;
	private int saleEnd;
	private String currency;
	private ApparelExtension apparelExtension;
	private String url;

	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}

	public int getImgNum() {
		return imgNum;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setApparelExtension(ApparelExtension apparelExtension) {
		this.apparelExtension = apparelExtension;
	}

	public ApparelExtension getApparelExtension() {
		return apparelExtension;
	}

	public String getIdCustom() {
		return idCustom;
	}

	public void setIdCustom(String idCustom) {
		this.idCustom = idCustom;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHits() {
		return hits;
	}

	public String getBrand() {
		return brand;
	}

	public String getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public String getIdType() {
		return idType;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	public int getSaleEnd() {
		return saleEnd;
	}

	public int getSaleStart() {
		return saleStart;
	}

	public void setSaleEnd(int saleEnd) {
		this.saleEnd = saleEnd;
	}

	public void setSaleStart(int saleStart) {
		this.saleStart = saleStart;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
