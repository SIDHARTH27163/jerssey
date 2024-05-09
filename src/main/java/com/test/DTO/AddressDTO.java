package com.test.DTO;

public class AddressDTO {
	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String city;
	private String state;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public AddressDTO(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}



}
