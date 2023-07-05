package com.smartscheduler.smart.model;

import jakarta.persistence.*;

@Entity

@DiscriminatorValue("agent")
public class Agent extends Person {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_schedule_id", referencedColumnName = "id")
    private Schedule workSchedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_schedule_id", referencedColumnName = "id")
    private Schedule availableSchedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booked_schedule_id", referencedColumnName = "id")
    private Schedule bookedSchedule;

    public Agent(){

    }

    public Agent(int id, String firstname, String lastname, Contact contact, Login login, Schedule workSchedule, Schedule bookedSchedule) {
        super(id, firstname, lastname, contact, login);
        this.setWorkSchedule(workSchedule);
        this.setBookedSchedule(bookedSchedule);
    }
    public Schedule getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(Schedule workSchedule) {
        this.workSchedule = workSchedule;
        calculateAvailableSchedule();
    }

    public Schedule getAvailableSchedule() {
        return availableSchedule;
    }

    public void setAvailableSchedule(Schedule availableSchedule) {
        this.availableSchedule = availableSchedule;
    }

    public Schedule getBookedSchedule() {
        return bookedSchedule;
    }

    public void setBookedSchedule(Schedule bookedSchedule) {
        this.bookedSchedule = bookedSchedule;
        calculateAvailableSchedule();
    }
    public void calculateAvailableSchedule() {
        if (workSchedule == null || bookedSchedule == null) {
            return;
        }

        if (workSchedule.getStartDateTime().isAfter(bookedSchedule.getStartDateTime())
                || workSchedule.getEndDateTime().isBefore(bookedSchedule.getEndDateTime())) {
            throw new IllegalStateException("Booked schedule not within work schedule");
        }

        Schedule availableSchedule = new Schedule();
        availableSchedule.setStartDateTime(bookedSchedule.getEndDateTime());
        availableSchedule.setEndDateTime(workSchedule.getEndDateTime());

        this.availableSchedule = availableSchedule;
    }
}
