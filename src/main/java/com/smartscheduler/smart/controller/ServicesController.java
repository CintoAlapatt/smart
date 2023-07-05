package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Services;
import com.smartscheduler.smart.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smartscheduler/api/services")
public class ServicesController {
    private final ServicesService servicesService;
    @Autowired
    public ServicesController(ServicesService servicesService){
        this.servicesService = servicesService;
    }
    @GetMapping
    public List<Services> getAllServices(){
        return servicesService.getAllServices();
    }

    @PostMapping("/post")
    public void registerNewService(@RequestBody Services services){
        servicesService.addNewService(services);
    }
}
