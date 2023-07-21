package com.smartscheduler.smart.service;

import com.smartscheduler.smart.model.Admin;
import com.smartscheduler.smart.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminService {
    @Autowired
    private final AdminRepository adminRepository ;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }
    public void addNewAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}