package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Agent;
import com.smartscheduler.smart.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smartscheduler/api/agent")
public class AgentController {
    private final AgentService agentService;
    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }
    @PostMapping
    public void registerNewAgent(@RequestBody Agent agent){
        agentService.addNewAgent(agent);
    }
}
