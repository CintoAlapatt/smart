package com.smartscheduler.smart.config;

import com.smartscheduler.smart.model.*;
import com.smartscheduler.smart.repository.AppointmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class AppointmentConfig {
    @Bean
    CommandLineRunner commandLineRunner(AppointmentRepository repository){
     return args -> {
         Customer customer=new Customer("ff","dd",null,null,"dd");

         Appointment appoint4= new Appointment(Status.PENDING,null,null," ",null,null,LocalDateTime.of(2020, Month.AUGUST,2,2,2,2),2,2);
//         Appointment appoint3= new Appointment("pending","chicago",null," ",null,new Customer(111,"ddd","fff",new Contact("122"),null,"123 downing street"),LocalDateTime.of(2020, Month.AUGUST,2,2,2,2),2,2);
         repository.saveAll(List.of(appoint4));
     };
    }
}
