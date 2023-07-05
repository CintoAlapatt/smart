package com.smartscheduler.smart.service;

import com.smartscheduler.smart.model.Agent;
import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.model.Services;
import com.smartscheduler.smart.repository.AgentRepository;
import com.smartscheduler.smart.repository.AppointmentRepository;
import com.smartscheduler.smart.repository.ServicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final ServicesRepository servicesRepository;
    @Autowired
    private final AgentRepository agentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ServicesRepository servicesRepository, AgentRepository agentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.servicesRepository = servicesRepository;
        this.agentRepository=agentRepository;
    }

    public List<Appointment> getRequests(){
        return appointmentRepository.findAll();
    }

    public void addNewAppointment(Appointment appointment) {
        Services services = servicesRepository.findById(appointment.getServices().getId())
                .orElseThrow(() -> new IllegalArgumentException("No service with the provided ID exists"));
        Agent agent = agentRepository.findById(appointment.getAgent().getId())
                .orElseThrow(() -> new IllegalArgumentException("No agent with the provided ID exists"));

        appointment.setServices(services);
        appointment.setAgent(agent);

        appointmentRepository.save(appointment);
    }


    public void deleteAppointment(int appointmentId) {
        boolean exists =appointmentRepository.existsById(appointmentId);
        if(!exists){
            throw new IllegalStateException("ID DOES NOT EXISTS");
        }
        appointmentRepository.deleteById(appointmentId);
    }
    @Transactional
    public void updateAppointment(int appointmentId, Integer estimateHrs, Integer estimateMin) {
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(()->new IllegalStateException("appointment ID"+appointmentId+"does not exists"));

        if(estimateHrs!= null && !estimateHrs.equals(appointment.getEstimateHrs())){
            appointment.setEstimateHrs(estimateHrs);
        }
        if(estimateMin!= null && !estimateMin.equals(appointment.getEstimateMin())){
            appointment.setEstimateMin(estimateMin);
        }

    }

    public void deleteAllAppointment() {

        appointmentRepository.deleteAll();
    }
}
