package com.smartscheduler.smart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartscheduler.smart.model.*;
import com.smartscheduler.smart.repository.AgentRepository;
import com.smartscheduler.smart.repository.AppointmentRepository;
import com.smartscheduler.smart.repository.ServicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ServicesRepository servicesRepository;

    private final AgentRepository agentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, ServicesRepository servicesRepository, AgentRepository agentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.servicesRepository = servicesRepository;
        this.agentRepository=agentRepository;
    }


    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
    public List<Appointment> getAllAppointmentsByMonth(Integer month,Integer year){
        return appointmentRepository.getAllAppointmentsByMonth(month,year);
    }
    public Appointment findAppointmentById(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No appointment found for the given id: " + id));
    }
    public String getAllAddressByAgentAndDate(Integer agentId, Integer day, Integer month, Integer year) {
        if(day == null){
            day = LocalDate.now().getDayOfMonth();
        }
        if(month == null){
            month = LocalDate.now().getMonthValue();
        }
        if(year == null){
            year = LocalDate.now().getYear();
        }

        Optional<Agent> agentOpt = agentRepository.findById(agentId);

        if (!agentOpt.isPresent()) {
            throw new NoSuchElementException("No agent found with the provided id");
        }

        Agent agent = agentOpt.get();
        List<Appointment> appointments = appointmentRepository.getAllAppointmentsByAgentAndDate(agentId,day,month,year);

        // Create a list of address keys and their addresses
        List<Address> addresses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            // Check if service location is null
            if(appointment.getServiceLocation() == null){
                continue; // Skip this iteration
            }
            addresses.add(appointment.getServiceLocation());
        }

        // Prepare response object
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("startAddress", agent.getStartAddress());
        response.put("addresses", addresses);



        // Convert map to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
    public Long getAppointmentIdByAddressId(Long addressId) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findAppointmentByAddressId(Math.toIntExact(addressId));
        if (appointmentOpt.isPresent()) {
            return (long) appointmentOpt.get().getId();
        } else {
            throw new NoSuchElementException("No appointment found for the given address id: " + addressId);
        }
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
    public void updateAppointment(int appointmentId, Integer estimateHrs, Integer estimateMin,LocalDate appointmentDate,  LocalTime appointmentTime, Status status) {
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(()->new IllegalStateException("appointment ID"+appointmentId+"does not exists"));

        if(estimateHrs!= null && !estimateHrs.equals(appointment.getEstimateHrs())){
            appointment.setEstimateHrs(estimateHrs);
        }
        if(estimateMin!= null && !estimateMin.equals(appointment.getEstimateMin())){
            appointment.setEstimateMin(estimateMin);
        }
        if(appointmentDate!= null && !appointmentDate.equals(appointment.getAppointmentDate())){
            appointment.setAppointmentDate(appointmentDate);;
        }
        if(appointmentTime!= null && !appointmentTime.equals(appointment.getAppointmentTime())){
            appointment.setAppointmentTime(appointmentTime);;
        }
        if(status!=null ){
            appointment.setStatus(status);
        }
        appointmentRepository.save(appointment);
    }


    public void deleteAllAppointment() {
        appointmentRepository.deleteAll();
    }


    public List<Appointment> getAllAppointmentsByAgentAndDate(Integer agentId, Integer day, Integer month, Integer year) {
        if(day == null){
            day = LocalDate.now().getDayOfMonth();
        }
        if(month == null){
            month = LocalDate.now().getMonthValue();
        }
        if(year == null){
            year = LocalDate.now().getYear();
        }

        Optional<Agent> agentOpt = agentRepository.findById(agentId);

        if (!agentOpt.isPresent()) {
            throw new NoSuchElementException("No agent found with the provided id");
        }

        Agent agent = agentOpt.get();
        List<Appointment> appointments = appointmentRepository.getAllAppointmentsByAgentAndDate(agentId,day,month,year);
        return appointments;
    }
}
