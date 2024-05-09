package com.test.models;

public class Animal {
    private int id;
    private String name;
    private String category;
    private Zoo zoo; // Reference to the Zoo entity

    // Constructors, getters, and setters
    public Animal() {
    }

    public Animal(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // Getters and setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
}
