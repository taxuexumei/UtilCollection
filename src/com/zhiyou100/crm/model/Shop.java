package com.zhiyou100.crm.model;

public class Shop {

	private Integer shopId;
	
	private String categoryName;
	
	private String shopName;
	
	private Integer price;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Shop(Integer shopId, String categoryName, String shopName, Integer price) {
		super();
		this.shopId = shopId;
		this.categoryName = categoryName;
		this.shopName = shopName;
		this.price = price;
	}
	
	
	
	
	
	
}
