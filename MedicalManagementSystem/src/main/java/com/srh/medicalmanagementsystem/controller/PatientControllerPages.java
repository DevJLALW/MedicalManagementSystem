package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;

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
        model.addAttribute("patients",patientList);

        return "patients/ShowPatients";
    }

    @GetMapping("/create")
    public String showCreatePatientPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/CreatePatient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult, Model model) {


        if(bindingResult.hasErrors()){
            return "patients/CreatePatient";
        }
        patientService.savePatient(patient);
        return "redirect:/patients/all";
    }

    @GetMapping("/update/{patientId}")
    public String showUpdatePatientPage(@PathVariable("patientId") Integer patientId,Model model) {
        Patient patient= patientService.findPatientById(patientId);
        model.addAttribute("patient", patient);
        return "patients/UpdatePatient";
    }

    @PostMapping("/update/{patientId}")
    public String updatePatient(@PathVariable("patientId") Integer patientId, @ModelAttribute("patient") Patient patient) {

        patientService.updatePatient(patientId, patient);
        return "redirect:/patients/all";
    }

    @PostMapping("/delete")
    public String deletePatient(@RequestParam("patientIds") List<Integer> patientIds) {
       patientService.deletePatients(patientIds);

        return "redirect:/patients/all";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam("keyword") String keyword, Model model) {
        List<Patient> patientList =patientService.searchPatients(keyword);
        model.addAttribute("patients", patientList);

        return "patients/ShowPatients";
    }
}
