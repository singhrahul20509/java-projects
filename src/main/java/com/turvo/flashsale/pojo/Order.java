package com.turvo.flashsale.pojo;

public class Order {
	
	private String registrationKey;
	private OrderDetails details;
	
	public Order(String registrationKey, OrderDetails details) {
		super();
		this.registrationKey = registrationKey;
		this.details = details;
	}
	
	public String getRegistrationKey() {
		return registrationKey;
	}
	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}
	public OrderDetails getDetails() {
		return details;
	}
	public void setDetails(OrderDetails details) {
		this.details = details;
	}
}
