package com.demo.models;

import java.time.LocalDate;

public class Product {
	private Integer productId;
	private String productName;
	private Integer avalQuantity;
	private Double price;
	private LocalDate expiryDate;
	public Product() {
	}
	public Product(Integer productId, String productName, Integer avalQuantity, Double price, LocalDate expiryDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.avalQuantity = avalQuantity;
		this.price = price;
		this.expiryDate = expiryDate;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getAvalQuantity() {
		return avalQuantity;
	}
	public void setAvalQuantity(Integer avalQuantity) {
		this.avalQuantity = avalQuantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
