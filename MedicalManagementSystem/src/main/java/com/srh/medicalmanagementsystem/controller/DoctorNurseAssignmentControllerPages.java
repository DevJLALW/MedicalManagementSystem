package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import com.srh.medicalmanagementsystem.service.DoctorNurseAssignmentService;
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

    public DoctorNurseAssignmentControllerPages(DoctorNurseAssignmentService doctorNurseAssignmentService) {
        this.doctorNurseAssignmentService = doctorNurseAssignmentService;
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
//
//    @GetMapping("/search")
//    public String searchAssignmentsByEmployeeId(
//            @RequestParam("id") Integer id,
//            Model model
//    ) {
//        List<DoctorNurseAssignmentDto> assignmentList =doctorNurseAssignmentService.findAssignmentsByIds(id);
//        model.addAttribute("doctorNurseAssignmentsList", assignmentList);
//
//        return "patients/ShowDoctorNurseAssignments";
//    }
//
//    @GetMapping("/search")
//    public String searchAssignmentsByEmployeeName(
//            @RequestParam("name") String name,
//            Model model
//    ) {
//        List<DoctorNurseAssignmentDto> assignmentList =doctorNurseAssignmentService.findAssignmentsByName(name);
//        model.addAttribute("doctorNurseAssignmentsList", assignmentList);
//
//        return "patients/ShowDoctorNurseAssignments";
//    }

    @GetMapping("/search")
    public String searchDoctorNurseAssignments(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<DoctorNurseAssignmentDto> assignmentsList;
        try {
            // Attempt to parse the keyword as an integer
            Integer employeeId = Integer.parseInt(keyword);
            assignmentsList =doctorNurseAssignmentService.findAssignmentsByIds(employeeId);
            System.out.println(assignmentsList);
        } catch (NumberFormatException e) {
            // If parsing fails, treat it as a string keyword
            assignmentsList =doctorNurseAssignmentService.findAssignmentsByName(keyword);
            System.out.println(assignmentsList);
        }
        model.addAttribute("assignmentsList", assignmentsList);

        return "patients/ShowDoctorNurseAssignments";
    }
}
