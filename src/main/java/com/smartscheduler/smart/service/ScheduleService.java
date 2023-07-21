package com.smartscheduler.smart.service;

import com.smartscheduler.smart.controller.ScheduleController;
import com.smartscheduler.smart.model.Agent;
import com.smartscheduler.smart.model.Schedule;
import com.smartscheduler.smart.model.Services;
import com.smartscheduler.smart.repository.AgentRepository;
import com.smartscheduler.smart.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final AgentRepository agentRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, AgentRepository agentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.agentRepository = agentRepository;
    }
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }
    @Transactional
    public void addNewSchedule(Schedule schedule) {
        int agentId = schedule.getAgent().getId();
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("No agent with the provided ID exists"));

        agent.getSchedules().add(schedule);
        schedule.setAgent(agent);
        scheduleRepository.save(schedule);

    }


}

