package com.example.sqlitedemo;

public class ContactModel {

    // variables for our coursename,
    // description, tracks and duration, id.
    private String firstName;
    private String lastName;
    private String Address;
    private String city;
    private String Age;
    private int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactModel(String firstName, String lastName, String address, String city, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        Address = address;
        this.city = city;
        Age = age;
    }
}

