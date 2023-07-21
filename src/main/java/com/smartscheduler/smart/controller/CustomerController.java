package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.model.Customer;
import com.smartscheduler.smart.model.Services;
import com.smartscheduler.smart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("smartscheduler/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.registerNewCustomer(customer);
    }

}
