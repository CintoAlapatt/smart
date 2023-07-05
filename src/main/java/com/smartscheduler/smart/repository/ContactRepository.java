package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
