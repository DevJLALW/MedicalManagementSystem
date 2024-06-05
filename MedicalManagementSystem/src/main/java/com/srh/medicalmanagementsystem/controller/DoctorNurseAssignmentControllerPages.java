package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.service.DoctorNurseAssignmentService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/doctor-nurse-assignment")
public class DoctorNurseAssignmentControllerPages {

    private DoctorNurseAssignmentService doctorNurseAssignmentService;

    public DoctorNurseAssignmentControllerPages(DoctorNurseAssignmentService doctorNurseAssignmentService) {
        this.doctorNurseAssignmentService = doctorNurseAssignmentService;
    }

    @GetMapping("/all")
    public String getAllDoctorNurseAssignmentPage(Model model) {
        List<DoctorNurseAssignment> assignmentsList= doctorNurseAssignmentService.getAllDoctorNurseAssignments();
        model.addAttribute("doctorNurseAssignments",assignmentsList);

        return "/employees/doctor-nurse-assignment/ShowAssignments";
    }

    @GetMapping("/create")
    public String showCreateDoctorNurseAssignmentPage(Model model) {
        model.addAttribute("doctorNurseAssignment", new DoctorNurseAssignment());
        return "employees/doctor-nurse-assignment/CreateDoctorNurseAssignment";
    }

    @PostMapping("/create")
    public String createDoctorNurseAssignment(
            @Valid @ModelAttribute("doctorNurseAssignment") DoctorNurseAssignment assignment,
            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "employees/doctor-nurse-assignment/CreateEmployee";
        }
        doctorNurseAssignmentService.saveDoctorNurseAssignment(assignment);
        return "redirect:/employees/doctor-nurse-assignment/all";
    }

    @PostMapping("/delete")
    public String deleteDoctorNurseAssignment(@RequestParam("ids") List<Integer> ids) {
        doctorNurseAssignmentService.deleteDoctorNurseAssignments(ids);

        return "redirect:/employees/doctor-nurse-assignment/all";
    }

    @GetMapping("/search")
    public String searchEmployees(
            @RequestParam("id") Integer id,
            Model model
    ) {
        List<DoctorNurseAssignment> assignmentList =doctorNurseAssignmentService.findAssignmentsByIds(id);
        model.addAttribute("doctorNurseAssignmentsList", assignmentList);

        return "employees/doctor-nurse-assignment/ShowAssignments";
    }
}
