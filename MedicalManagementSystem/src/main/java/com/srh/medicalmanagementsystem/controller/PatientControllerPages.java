package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.entity.PatientDTO;
import com.srh.medicalmanagementsystem.service.PatientService;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.service.MedicalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/patients")
@SessionAttributes("employeeID")
public class PatientControllerPages {

    private PatientService patientService;
    private MedicalRecordService medicalRecordService;

    @Autowired
    public PatientControllerPages(PatientService patientService, MedicalRecordService medicalRecordService) {
        this.patientService = patientService;
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/all")
    public String getAllPatientsPage(Model model) {
        List<Patient> patientList = patientService.getAllPatients();
        model.addAttribute("patients", patientList);
        return "patients/ShowPatients";
    }

    @GetMapping("/create")
    public String showCreatePatientPage(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "patients/CreatePatient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") PatientDTO patientdto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "patients/CreatePatient";
        }
        String employeeID = (String) request.getSession().getAttribute("employeeID");
        if (employeeID == null) {
            System.out.println("Null employeeID");
        }
        Integer employeeIDInt = Integer.parseInt(employeeID);
        patientdto.setEmployeeID(employeeIDInt);
        patientService.savePatient(patientdto);
        return "redirect:/patients/all";
    }

    @GetMapping("/update/{patientId}")
    public String showUpdatePatientPage(@PathVariable("patientId") Integer patientId, Model model) {
        Patient patient = patientService.findPatientById(patientId);
        model.addAttribute("patient", patient);
        return "patients/UpdatePatient";
    }

    @PostMapping("/update/{patientId}")
    public String updatePatient(@PathVariable("patientId") Integer patientId, @Valid @ModelAttribute("patient") PatientDTO patientdto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/UpdatePatient";
        }
        patientService.updatePatient(patientId, patientdto);
        return "redirect:/patients/all";
    }

    @PostMapping("/delete")
    public String deletePatient(@RequestParam("patientIds") List<Integer> patientIds) {
        patientService.deletePatients(patientIds);
        return "redirect:/patients/all";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam("keyword") String keyword, Model model) {
        List<Patient> patientList = patientService.searchPatients(keyword);
        model.addAttribute("patients", patientList);
        return "patients/ShowPatients";
    }

    @GetMapping("/details/{patientId}")
    public String getPatientDetails(@PathVariable("patientId") int patientId, Model model) {
        Patient patient = patientService.findPatientById(patientId);
        List<MedicalRecord> medicalRecords = medicalRecordService.findMedicalRecordByPatientId(patientId);
        model.addAttribute("patient", patient);
        model.addAttribute("medicalRecords", medicalRecords);
        return "patients/ShowPatientDetails";
    }

    @GetMapping("/forgetPassword/{patientId}")
    public String showUpdatePasswordForm(@PathVariable("patientId") Integer patientId, Model model) {
        Patient patient = patientService.findPatientById(patientId);
        if (patient == null) {
            return "error";
        }
        model.addAttribute("patient", patient);
        return "patients/ForgetPassword";
    }

    @PostMapping("/forgetPassword/{patientId}")
    public String forgetPassword(@PathVariable("patientId") Integer patientId, @RequestParam("password") String newPassword) {
        Patient patient = patientService.findPatientById(patientId);
        if (patient == null) {
            return "error";
        }
        patientService.updatePassword(patientId, newPassword);
        return "redirect:/login";
    }
}
