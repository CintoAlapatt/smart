package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phoneNumber;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact() {

    }

    public Contact(int i) {
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

