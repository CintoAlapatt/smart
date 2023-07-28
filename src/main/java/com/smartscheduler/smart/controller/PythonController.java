package com.smartscheduler.smart.controller;import com.smartscheduler.smart.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("smartscheduler/api")
public class PythonController {
    private final PythonService pythonService;

    @Autowired
    public PythonController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping("/runpythonscript")
    public String runPythonScript(@RequestBody String jsonData) {
        return pythonService.runPythonScript(jsonData);
    }

    @PostMapping("/optimizer")
    public void optimizeAppointments(@RequestParam Integer agentId, @RequestParam Integer day, @RequestParam Integer month, @RequestParam Integer year){
        pythonService.optimizeAppointments(agentId,day,month,year);
    }
}