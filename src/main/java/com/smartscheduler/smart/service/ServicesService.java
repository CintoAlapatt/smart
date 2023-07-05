package com.smartscheduler.smart.service;
import com.smartscheduler.smart.model.Services;
import com.smartscheduler.smart.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {
    private final ServicesRepository servicesRepository;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public void addNewService(Services services) {
        servicesRepository.save(services);
    }
}
