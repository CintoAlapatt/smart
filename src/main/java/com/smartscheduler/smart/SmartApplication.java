package com.smartscheduler.smart;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.smartscheduler.smart.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@RestController
public class SmartApplication {
	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(SmartApplication.class, args);
	}

	@GetMapping("/hello")
	public Schedule	 hello(){
		Services services = new Services(3,"hh");
		Agent agent = entityManager.find(Agent.class, 2);
		ScheduleUnit schedule = new ScheduleUnit( LocalTime.of( 9, 0), LocalTime.of(17, 0));
		Schedule schedule1=new Schedule(LocalDate.of(2023,7,10),schedule,null,null,null);
		Appointment appointment = new Appointment(Status.PENDING,null,services,"lawn",null,null,LocalDate.of(2023, 10, 15),LocalTime.of(14, 10),2,2);

		return schedule1;
	}

	@GetMapping("/greet")
	public Greeting greet(){
		return new Greeting("helle");
	}



	record Greeting (String greet){}
}
