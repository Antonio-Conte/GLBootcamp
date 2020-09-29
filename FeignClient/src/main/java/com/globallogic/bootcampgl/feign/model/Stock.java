package com.globallogic.bootcampgl.feign.model;

public class Stock {

	private int id;
	private int quantity;
	private String locationCode;
	private String status;

	public Stock() {

	}

	public Stock(int id, int quantity, String locationCode, String status) {
		this.id = id;
		this.quantity = quantity;
		this.locationCode = locationCode;
		this.status = status;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
