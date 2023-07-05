package com.smartscheduler.smart.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private int id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String serviceLocation;

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id")
    private Services services;

    private String serviceDescription;
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDateTime dateTime;
    private Integer estimateHrs;
    private Integer estimateMin;

    public Appointment() {
        }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Appointment(String status, String serviceLocation, Services services, String serviceDescription, Agent agent, Customer customer, LocalDateTime dateTime, Integer estimateHrs, Integer estimateMin) {
        this.serviceLocation=serviceLocation;
        this.services = services;
        this.serviceDescription=serviceDescription;
        this.agent = agent;
        this.customer = customer;
        this.dateTime = dateTime;
        this.estimateHrs = estimateHrs;
        this.estimateMin = estimateMin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent  agent) {
        this.agent = agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getEstimateHrs() {
        return estimateHrs;
    }

    public void setEstimateHrs(Integer estimateHrs) {
        this.estimateHrs = estimateHrs;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", service=" + services +
                ", agent=" + agent +
                ", customer=" + customer +
                ", dateTime=" + dateTime +
                ", estimateHrs=" + estimateHrs +
                ", estimateMin=" + estimateMin +
                '}';
    }

    public Integer getEstimateMin() {
        return estimateMin;
    }

    public void setEstimateMin(Integer estimateMin) {
        this.estimateMin = estimateMin;
    }
}
