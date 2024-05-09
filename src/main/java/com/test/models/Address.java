package com.test.models;

import java.util.Set;

public class Address {
	

   private Set<Zoo> zoos;
  
	 
private int a_id;
	

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


public Set<Zoo> getZoos() {
    return zoos;
}

public void setZoos(Set<Zoo> zoos) {
    this.zoos = zoos;
}
public Address(int a_id, String city, String state ) {
	super();
	this.setA_id(a_id);
	this.city = city;
	this.state = state;
	
}
public Address() {
	super();
	// TODO Auto-generated constructor stub
}
public int getA_id() {
	return a_id;
}
public void setA_id(int a_id) {
	this.a_id = a_id;
}


}



