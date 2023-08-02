package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Schedule;
import com.smartscheduler.smart.model.Status;
import com.smartscheduler.smart.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @GetMapping("/agent")
    public Schedule getScheduleAgentOnDate(@RequestParam Integer month,@RequestParam Integer year,@RequestParam Integer day,@RequestParam Integer agentId){
        return scheduleService.getScheduleAgentOnDate( agentId,day,month,year);
    }
    @PostMapping
    public void registerNewSchedule(@RequestBody Schedule schedule){
        scheduleService.addNewSchedule(schedule);
    }

    @PutMapping
    public void updateWorkSchedule(@RequestParam Integer month,@RequestParam Integer year,@RequestParam Integer day,@RequestParam Integer agentId,@RequestParam LocalTime startTime,@RequestParam LocalTime endTime){
            scheduleService.updateWorkSchedule(agentId,day,month,year,startTime,endTime);
    }

}
