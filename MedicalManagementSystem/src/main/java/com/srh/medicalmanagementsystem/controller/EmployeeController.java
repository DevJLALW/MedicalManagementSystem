package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/searchDoctors")
    public ResponseEntity<List<Employee>> searchDoctors(@RequestParam("query") String query) {
        List<Employee> doctors = employeeService.searchDoctorsByName(query);
        System.out.println(doctors);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/searchNurse")
    public ResponseEntity<List<Employee>> searchNurse(@RequestParam("query") String query) {
        List<Employee> nurses = employeeService.searchNurseByName(query);
        System.out.println(nurses);
        return ResponseEntity.ok(nurses);
    }
}
