package com.smartscheduler.smart.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Agent extends Person {

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Schedule> schedules = new ArrayList<>();

    public Agent(){
    }

    public Agent(String firstname, String lastname, Contact contact, Login login) {
        super( firstname, lastname, contact, login);
    }

//    getter and setter for schedules
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        for (Schedule schedule : schedules) {
            schedule.setAgent(this);
        }
    }
}

