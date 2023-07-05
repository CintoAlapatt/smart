package com.smartscheduler.smart.service;

import com.smartscheduler.smart.model.Agent;
import com.smartscheduler.smart.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AgentService {
    @Autowired
    private final AgentRepository agentRepository ;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }
    public void addNewAgent(Agent agent) {
        agentRepository.save(agent);
    }
}
