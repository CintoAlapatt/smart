package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
