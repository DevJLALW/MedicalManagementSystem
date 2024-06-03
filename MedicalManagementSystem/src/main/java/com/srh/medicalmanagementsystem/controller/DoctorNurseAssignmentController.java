package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.service.DoctorNurseAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/doctor-nurse-assignment")
public class DoctorNurseAssignmentController {

    private DoctorNurseAssignmentService doctorNurseAssignmentService;

    public DoctorNurseAssignmentController(DoctorNurseAssignmentService doctorNurseAssignmentService) {
        this.doctorNurseAssignmentService = doctorNurseAssignmentService;
    }


    @GetMapping
    public List<DoctorNurseAssignment> getAllDoctorNurseAssignments () {
        return doctorNurseAssignmentService.getAllDoctorNurseAssignments();
    }

    @PostMapping
    public ResponseEntity<DoctorNurseAssignment> createDoctorNurseAssignment(@RequestBody DoctorNurseAssignment assignment){
        DoctorNurseAssignment newAssignment = doctorNurseAssignmentService.saveDoctorNurseAssignment(assignment);
        return ResponseEntity.ok(newAssignment);
    };
}
