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

import java.time.LocalDateTime;

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

		Agent agent = entityManager.find(Agent.class, 1);
		ScheduleUnit schedule = new ScheduleUnit( LocalDateTime.of(2023, 7, 1, 9, 0), LocalDateTime.of(2023, 7, 1, 17, 0));
		Schedule schedule1=new Schedule(LocalDateTime.of(2023,7,10,9,0),schedule,null,null,null);
		Appointment appointment = new Appointment(Status.PENDING,null,services,"lawn",null,null,LocalDateTime.of(14, 10, 15,0,0,0),2,2);

		return schedule1;
	}

	@GetMapping("/greet")
	public Greeting greet(){
		return new Greeting("helle");
	}



	record Greeting (String greet){}
}
