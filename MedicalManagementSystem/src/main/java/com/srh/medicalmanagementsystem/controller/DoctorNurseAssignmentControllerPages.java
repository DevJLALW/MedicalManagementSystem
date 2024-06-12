package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import com.srh.medicalmanagementsystem.service.DoctorNurseAssignmentService;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees/doctor-nurse-assignment")
public class DoctorNurseAssignmentControllerPages {

    private DoctorNurseAssignmentService doctorNurseAssignmentService;
    private EmployeeService employeeService;

    public DoctorNurseAssignmentControllerPages(DoctorNurseAssignmentService doctorNurseAssignmentService, EmployeeService employeeService) {
        this.doctorNurseAssignmentService = doctorNurseAssignmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public String getAllDoctorNurseAssignmentPage(Model model) {
        List<DoctorNurseAssignmentDto> assignmentsList= doctorNurseAssignmentService.getAllDoctorNurseAssignments();
        model.addAttribute("assignmentsList",assignmentsList);

        return "patients/ShowDoctorNurseAssignments";
    }

    @GetMapping("/create")
    public String showCreateDoctorNurseAssignmentPage(Model model) {
        model.addAttribute("assignment", new DoctorNurseAssignmentDto());
        model.addAttribute("doctors", employeeService.getRoleSpecificEmployees("Doctor"));
        model.addAttribute("nurses",
                employeeService.getRoleSpecificEmployees("Nurse"));
        return "patients/CreateDoctorNurseAssignment";
    }

    @PostMapping("/create")
    public String createDoctorNurseAssignment(
            @Valid @ModelAttribute("assignment") DoctorNurseAssignmentDto assignment,
            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "patients/CreateDoctorNurseAssignment";
        }
        doctorNurseAssignmentService.saveDoctorNurseAssignment(assignment);
        return "redirect:/employees/doctor-nurse-assignment/all";
    }

    @PostMapping("/delete")
    public String deleteDoctorNurseAssignments(@RequestParam("assignmentIds") List<Integer> assignmentIds) {
        doctorNurseAssignmentService.deleteDoctorNurseAssignments(assignmentIds);

        return "redirect:/employees/doctor-nurse-assignment/all";
    }

    @GetMapping("/search")
    public String searchDoctorNurseAssignments(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<DoctorNurseAssignmentDto> assignmentsList;
        try {
            Integer employeeId = Integer.parseInt(keyword);
            assignmentsList =doctorNurseAssignmentService.findAssignmentsByIds(employeeId);
        } catch (NumberFormatException e) {
            assignmentsList =doctorNurseAssignmentService.findAssignmentsByName(keyword);
        }
        model.addAttribute("assignmentsList", assignmentsList);

        return "patients/ShowDoctorNurseAssignments";
    }
}
