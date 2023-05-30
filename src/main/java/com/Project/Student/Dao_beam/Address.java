package com.Project.Student.Dao_beam;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String city;
	private String zipCode;
	private String state;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String city, String zipCode, String state) {
		super();
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
