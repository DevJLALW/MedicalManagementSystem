package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthController {
    private final PatientService patientService;

    @Autowired
    public AuthController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("patient") Patient patient,
                               BindingResult result,
                               Model model) {
        Patient existingPatient = patientService.findPatientById(patient.getPatientId());

        if (existingPatient != null) {
            result.rejectValue("patientID", null,
                    "There is already an account registered with the same patient ID");
        }

        if (result.hasErrors()) {
            model.addAttribute("patient", patient);
            return "register";
        }

        patientService.savePatient(patient);
        return "redirect:/register?success";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        List<Patient> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
