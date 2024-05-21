package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/patients")
public class PatientControllerPages {

    private PatientService patientService;

    public PatientControllerPages(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping("/all")
    public String getAllPatientsPage(Model model){
        List<Patient> patientList=patientService.getAllPatients();
        model.addAttribute("patients",patientList);

        return "patients/ShowPatients";
    }
}
