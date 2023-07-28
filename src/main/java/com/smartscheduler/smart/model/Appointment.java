package com.smartscheduler.smart.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="service_location_id")

    private Address serviceLocation;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;

    private String serviceDescription;
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
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

    public Appointment(Status status, Address serviceLocation, Services services, String serviceDescription, Agent agent ,Customer customer,LocalDate appointmentDate,LocalTime appointmentTime, Integer estimateHrs, Integer estimateMin) {
        this.status=status;
        this.serviceLocation=serviceLocation;
        this.services = services;
        this.serviceDescription=serviceDescription;
        this.agent = agent;
        this.customer = customer;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getEstimateHrs() {
        return estimateHrs;
    }

    public void setEstimateHrs(Integer estimateHrs) {
        this.estimateHrs = estimateHrs;
    }



    public Integer getEstimateMin() {
        return estimateMin;
    }

    public void setEstimateMin(Integer estimateMin) {
        this.estimateMin = estimateMin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Address getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(Address serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
