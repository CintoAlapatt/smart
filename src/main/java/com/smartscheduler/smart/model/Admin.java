package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin extends Person{


    public Admin(String firstname, String lastname, Contact contact, Login login) {
        super( firstname, lastname, contact, login);
    }

    public Admin() {
    }
}
