package com.smartscheduler.smart.config;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.smartscheduler.smart.model.*;
import com.smartscheduler.smart.repository.AgentRepository;
import com.smartscheduler.smart.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class AppointmentConfig {
    private final AgentRepository agentRepository;

    @Autowired
    public AppointmentConfig(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(AppointmentRepository repository){
        return args -> {
            try {
                // Check if an agent with ID 2 exists
                Optional<Agent> agentOptional = agentRepository.findById(1);
                Agent agent2 = agentOptional.orElseThrow(() -> new IllegalArgumentException("No agent with ID Exists"));

                Customer customer = new Customer("ff", "dd", null, null, "dd");

                Appointment appoint4 = new Appointment(Status.PENDING, new Address("ss", "ss", "ss", "ss", 12), null, " ", agent2, null, LocalDate.of(2023, 10, 15), LocalTime.of(14, 10), 2, 2);
                repository.saveAll(List.of(appoint4));
            } catch (IllegalArgumentException e) {
                // Log the exception and handle it gracefully
                System.out.println("Error: " + e.getMessage());
            }
        };
    }
}
