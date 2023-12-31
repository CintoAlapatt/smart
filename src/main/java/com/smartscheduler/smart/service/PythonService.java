package com.smartscheduler.smart.service;

import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.model.Schedule;
import com.smartscheduler.smart.model.ScheduleUnit;
import com.smartscheduler.smart.model.Status;
import com.smartscheduler.smart.repository.AppointmentRepository;
import com.smartscheduler.smart.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalTime;

import org.json.*;
@Service
public class PythonService {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final  ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public PythonService(AppointmentService appointmentService, ScheduleService scheduleService, AppointmentRepository appointmentRepository,ScheduleRepository scheduleRepository) {
        this.appointmentService = appointmentService;
        this.scheduleService = scheduleService;
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository=scheduleRepository;
    }

    public void optimizeAppointments(Integer agentId, Integer day, Integer month, Integer year) {
        String jsonAppointments =appointmentService.getAllAddressByAgentAndDate(agentId, day, month, year);
        System.out.println(jsonAppointments);
        String optimizedSchedule = runPythonScript(jsonAppointments);
        System.out.println("ccc");
        System.out.println(optimizedSchedule);
        LocalTime newTime = null;
        int serviceTime = 0;
        Appointment appointment=new Appointment();
        JSONArray array = new JSONArray(optimizedSchedule);
        int totalTime=0;
        for(int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            long address_id = object.getLong("id");
            long travelTime = object.getLong("travelTime");
            long appointment_id = appointmentService.getAppointmentIdByAddressId(address_id);
            appointment = appointmentService.findAppointmentById(appointment_id);

            if(appointment != null) {
                serviceTime = appointment.getEstimateHrs() * 60 + appointment.getEstimateMin();
                long minutesToAdd = totalTime + travelTime;
                totalTime += serviceTime;
                LocalTime time = scheduleService.getScheduleAgentOnDate(agentId,day,month,year).getWorkSchedule().startTime;
                newTime = time.plusMinutes(minutesToAdd);
                appointment.setAppointmentTime(newTime);
                appointment.setStatus(Status.SCHEDULED);
                appointmentRepository.save(appointment);
            } else {
                System.out.println("No appointment found with id: " + appointment_id);
            }
        }
        Schedule schedule=scheduleService.getScheduleAgentOnDate(agentId,day,month,year);
        ScheduleUnit bookedSchedule=schedule.getBookedSchedule();
        ScheduleUnit workSchedule=schedule.getWorkSchedule();
        ScheduleUnit availableSchedule=schedule.getAvailableSchedule();

        assert newTime != null;
        bookedSchedule.setStartTime(workSchedule.startTime);
        bookedSchedule.setEndTime(newTime.plusMinutes(serviceTime));
        availableSchedule.setStartTime(bookedSchedule.endTime);

        scheduleRepository.save(schedule);
    }



    public String runPythonScript(String jsonData) {
        try {
            // define the python script path
            String pythonScriptPath = "src\\main\\java\\com\\smartscheduler\\smart\\pythonscript\\optimizer.py";

            // create the process
            String jsonDataEscaped = jsonData.replace("\"", "\\\"");
            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath, jsonDataEscaped);

            // execute the process
            Process p = pb.start();

            // write to the process's stdin
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writer.write(jsonData);
            writer.flush();
            writer.close();

            // read the output
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = "";
            String line;
            while ((line = in.readLine()) != null) {
                ret += line + "\n";
                System.out.println("Python Script Output: " + line);
            }
            in.close();

            return ret;



        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
