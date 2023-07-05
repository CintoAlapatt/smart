package com.smartscheduler.smart;

import com.smartscheduler.smart.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class SmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartApplication.class, args);
	}

	@GetMapping("/hello")
	public Services hello(){
		Services services = new Services(3,"hh");
		Schedule schedule = new Schedule(1, LocalDateTime.of(2023, 7, 1, 9, 0), LocalDateTime.of(2023, 7, 1, 17, 0));
		Agent agent= new Agent(2,"antony","d",null,null,null,null);
		Appointment appointment = new Appointment("pending","new york",services,"lawn",new Agent(
				1,
				"John",
				"Doe",
				new Contact(  "1234567890"), // Contact object
				new Login( "johndoe", "password"), // Login object
				new Schedule(1, LocalDateTime.of(2023, 7, 1, 9, 0), LocalDateTime.of(2023, 7, 1, 17, 0)), // workSchedule
				new Schedule(2, LocalDateTime.of(2023, 7, 1, 11, 0), LocalDateTime.of(2023, 7, 1, 13, 0)) // bookedSchedule
		),null,LocalDateTime.of(14, 10, 15,0,0,0),2,2);

		return services;
	}

	@GetMapping("/greet")
	public Greeting greet(){
		return new Greeting("helle");
	}



	record Greeting (String greet){}
}
