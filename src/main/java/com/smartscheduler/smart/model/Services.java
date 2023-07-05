package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Services {
    @Id

    private int id;

    private String serviceType;

    public Services(int id,String serviceType) {
        this.id=id;
        this.serviceType = serviceType;
    }


    public Services() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


}
