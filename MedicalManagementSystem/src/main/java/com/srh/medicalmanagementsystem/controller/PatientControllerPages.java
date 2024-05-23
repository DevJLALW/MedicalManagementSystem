package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
        List<Patient> patientList= patientService.getAllPatients();
        patientList.sort(Comparator.comparing(Patient::getPatientId).reversed());
        model.addAttribute("patients",patientList);

        return "patients/ShowPatients";
    }

    @GetMapping("/create")
    public String showCreatePatientPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/CreatePatient";
    }

    @PostMapping("/create")
    public String createPatient(@ModelAttribute("patient") Patient patient) {
        System.out.println("Date of Birth received from form: " + patient.getDob());

        patientService.savePatient(patient);
        return "redirect:/patients/all";
    }
}
