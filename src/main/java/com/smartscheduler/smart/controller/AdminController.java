package com.smartscheduler.smart.controller;


import com.smartscheduler.smart.model.Admin;
import com.smartscheduler.smart.model.Agent;
import com.smartscheduler.smart.service.AdminService;
import com.smartscheduler.smart.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smartscheduler/api/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
    @PostMapping
    public void registerNewAdmin(@RequestBody Admin admin){
        adminService.addNewAdmin(admin);
    }
}