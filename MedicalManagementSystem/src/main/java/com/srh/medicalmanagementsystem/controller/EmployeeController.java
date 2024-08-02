package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.entity.Specialization;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import com.srh.medicalmanagementsystem.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SpecializationService specializationService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, SpecializationService specializationService) {
        this.employeeService = employeeService;
        this.specializationService=specializationService;
    }


    @GetMapping("/searchDoctors")
    public ResponseEntity<List<Employee>> searchDoctors(@RequestParam("query") String query) {
        List<Employee> doctors = employeeService.searchDoctorsByName(query);
        List<Integer> docIds= new ArrayList<>();
        for(Employee doctor: doctors)
        {
            docIds.add(doctor.getEmployeeId());
        }
        List<Specialization> specializations =specializationService.findByDoctorIds(docIds);
        //System.out.println("specializations "+specializations);

        for(Employee doctor: doctors)
        {
            for(Specialization specialization : specializations){
                //System.out.println(doctor.getEmployeeId()+" : "+specialization.getDoctorId());
                if(doctor.getEmployeeId().equals(specialization.getDoctorId())) {
                   // System.out.println("Check if working");
                    doctor.setSpecialization(specialization);
                }
            }
        }
       // System.out.println(doctors);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/searchNurse")
    public ResponseEntity<List<Employee>> searchNurse(@RequestParam("query") String query, @RequestParam(value = "doctorId", required = false) Integer doctorId) {
        List<Employee> nurses = null;

        if (doctorId != null) {
            nurses = employeeService.findNursesByDoctorId(doctorId);
        }

        if (nurses == null || nurses.isEmpty()) {
            nurses = employeeService.searchNurseByName(query);
        }

        System.out.println(nurses);
        return ResponseEntity.ok(nurses);
    }

}