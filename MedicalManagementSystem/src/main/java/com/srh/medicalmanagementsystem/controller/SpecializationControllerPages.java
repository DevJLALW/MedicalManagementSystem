package com.srh.medicalmanagementsystem.controller;


import com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto;
import com.srh.medicalmanagementsystem.entity.Specialization;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import com.srh.medicalmanagementsystem.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees/doctors-specialization")
public class SpecializationControllerPages {

    private SpecializationService specializationService;
    private EmployeeService employeeService;

    public SpecializationControllerPages(EmployeeService employeeService, SpecializationService specializationService) {
        this.employeeService = employeeService;
        this.specializationService = specializationService;
    }

    @GetMapping("/all")
    public String getAllDoctorSpecializationsPage(Model model) {
        List<DoctorSpecializationDto> specializationsList= specializationService.getAllDoctorsSpecializations();
        model.addAttribute("specializationsList",specializationsList);

        return "patients/ShowDoctorSpecializations";
    }

    @GetMapping("/create")
    public String showCreateDoctorSpecializationPage(Model model) {
        model.addAttribute("specialization", new DoctorSpecializationDto());
        model.addAttribute("doctors", employeeService.getRoleSpecificEmployees("Doctor"));
        return "patients/CreateDoctorSpecialization";
    }

    @PostMapping("/create")
    public String createDoctorSpecialization(
            @Valid @ModelAttribute("specialization") DoctorSpecializationDto specialization,
            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "patients/CreateDoctorSpecialization";
        }
        specializationService.saveSpecialization(specialization);
        return "redirect:/employees/doctors-specialization/all";
    }

    @GetMapping("/update/{doctorId}")
    public String showUpdateDoctorSpecializationPage(
            @PathVariable("doctorId") Integer doctorId,
            Model model
    ) {
        DoctorSpecializationDto specialization= (specializationService.findSpecializationByDoctorId(doctorId)).get(0);
        model.addAttribute("specialization", specialization);
        return "patients/UpdateDoctorSpecialization";
    }

    @PostMapping("/update/{doctorId}")
    public String updateDoctorSpecialization(
            @PathVariable("doctorId") Integer doctorId,
            @ModelAttribute("specialization") DoctorSpecializationDto specialization
    ) {
        specializationService.updateSpecialization(doctorId);
        return "redirect:/employees/doctors-specialization/all";
    }

    @PostMapping("/delete")
    public String deleteDoctorSpecializations(@RequestParam("doctorIds") List<Integer> doctorIds) {
        specializationService.deleteDoctorSpecialization(doctorIds);

        return "redirect:/employees/doctors-specialization/all";
    }

    @GetMapping("/search")
    public String searchDoctorSpecializations(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<DoctorSpecializationDto> specializationsList;
        try {
            // Attempt to parse the keyword as an integer
            Integer doctorId = Integer.parseInt(keyword);
            specializationsList = specializationService.findSpecializationByDoctorId(doctorId);
        } catch (NumberFormatException e) {
            // If parsing fails, treat it as a string keyword
            specializationsList =specializationService.findSpecializationByKeyword(keyword);
        }
        model.addAttribute("specializationsList", specializationsList);

        return "patients/ShowDoctorSpecializations";
    }
}
