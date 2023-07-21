package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Schedule;
import com.smartscheduler.smart.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smartscheduler/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService=scheduleService;
    }
    @GetMapping
    public List<Schedule> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }
    @PostMapping
    public void registerNewSchedule(@RequestBody Schedule schedule){
        scheduleService.addNewSchedule(schedule);
    }

}
