package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Customer extends Person {


    private String address;

    public Customer( String firstname, String lastname, Contact contact, Login login, String address) {
        super(firstname, lastname, contact, login);

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
