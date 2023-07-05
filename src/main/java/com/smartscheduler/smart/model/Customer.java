package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Person {

    private String address;

    public Customer(int id, String firstname, String lastname, Contact contact, Login login, String address) {
        super(id, firstname, lastname, contact, login);
        this.address = address;
    }

    public Customer() {

    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
