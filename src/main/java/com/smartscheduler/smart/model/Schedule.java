package com.smartscheduler.smart.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_schedule_id", referencedColumnName = "id")
    private ScheduleUnit workSchedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_schedule_id", referencedColumnName = "id")
    private ScheduleUnit availableSchedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booked_schedule_id", referencedColumnName = "id")
    private ScheduleUnit bookedSchedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="agent_id")
    @JsonIgnoreProperties({"schedules"})
    private Agent agent;

    public Schedule() {
    }

    public Schedule(LocalDate date, ScheduleUnit workSchedule, ScheduleUnit availableSchedule, ScheduleUnit bookedSchedule, Agent agent) {
        this.date = date;
        this.workSchedule = workSchedule;
        this.availableSchedule = availableSchedule;
        this.bookedSchedule = bookedSchedule;
        this.agent = agent;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ScheduleUnit getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(ScheduleUnit workSchedule) {
        this.workSchedule = workSchedule;
    }

    public ScheduleUnit getAvailableSchedule() {
        return availableSchedule;
    }

    public void setAvailableSchedule(ScheduleUnit availableSchedule) {
        this.availableSchedule = availableSchedule;
    }

    public ScheduleUnit getBookedSchedule() {
        return bookedSchedule;
    }

    public void setBookedSchedule(ScheduleUnit bookedSchedule) {
        this.bookedSchedule = bookedSchedule;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
