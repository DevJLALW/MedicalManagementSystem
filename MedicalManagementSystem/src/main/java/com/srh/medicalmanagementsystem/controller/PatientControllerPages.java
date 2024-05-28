package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;

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

    @GetMapping("/delete/{patientId}")
    public String deletePatient(@PathVariable("patientId") Integer patientId) {
       patientService.deletePatient(patientId);

        return "redirect:/patients/all";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam("keyword") String keyword, Model model) {
        List<Patient> patientList =patientService.searchPatients(keyword);
        /*System.out.println("After Patient list");
        for(Patient patient: patientList){
            System.out.println("ID"+patient.getPatientId());
            System.out.println("Name"+patient.getFirstName());
        }*/

        model.addAttribute("patients", patientList);

        return "patients/ShowPatients";
    }
}
