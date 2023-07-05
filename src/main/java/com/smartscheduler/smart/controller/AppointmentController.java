package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "smartscheduler/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public List<Appointment> getRequests() {
        return appointmentService.getRequests();
    }
    @PostMapping
    public void registerNewAppointment(@RequestBody Appointment appointment){
        appointmentService.addNewAppointment(appointment);
    }

    @DeleteMapping(path ="{appointmentId}")
    public void deleteAppointment(@PathVariable ("appointmentId")int appointmentId){
        appointmentService.deleteAppointment(appointmentId);

    }
    @DeleteMapping(path ="/all")
    public void deleteAllAppointment(){
        appointmentService.deleteAllAppointment();

    }
    @PutMapping(path ="{appointmentId}")
    public void updateAppointment(@PathVariable("appointmentId")int appointmentId,@RequestParam(required = false) Integer estimateHrs,@RequestParam (required = false) Integer estimateMin){
        appointmentService.updateAppointment(appointmentId,estimateHrs,estimateMin);
    }


}
