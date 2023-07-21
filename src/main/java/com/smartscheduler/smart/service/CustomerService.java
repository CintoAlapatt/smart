package com.smartscheduler.smart.service;

import com.smartscheduler.smart.model.Customer;
import com.smartscheduler.smart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {

        private final CustomerRepository customerRepository;

        @Autowired
        public CustomerService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public List<Customer> getAllCustomer() {
            return customerRepository.findAll();
        }

        public void registerNewCustomer(Customer customer) {
            customerRepository.save(customer);
        }
}

