package com.test.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.Views.JSONView;


public class Zoo {

	
	
	private int id;

	private String name;
	private String location;
	private Date insertTime;
	 
	private Address address;
	 private Set<Animal> animals = new HashSet<>();

	public Zoo(int id, String name, String location, Date insertTime ) {
		super();
		this.id = id;
		
		this.name = name;
		this.location = location;
		this.insertTime = insertTime;

	}
	public Zoo() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	 public Set<Animal> getAnimals() {
	        return animals;
	    }

	    public void setAnimals(Set<Animal> animals) {
	        this.animals = animals;
	    }

	    // Helper methods to manage bidirectional relationship
	    public void addAnimal(Animal animal) {
	        animals.add(animal);
	        animal.setZoo(this);
	    }

	    public void removeAnimal(Animal animal) {
	        animals.remove(animal);
	        animal.setZoo(null);
	    }
	

	
	
	
	
	
}