package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.PatientEventRecord;

import com.srh.medicalmanagementsystem.service.PatientEventRecordService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patientEventRecord")
public class PatientEventRecordController {


    private final PatientEventRecordService patientEventRecordService;

    public PatientEventRecordController(PatientEventRecordService patientEventRecordService) {
        this.patientEventRecordService = patientEventRecordService;
    }

    @GetMapping("/all")
    public String getAllPatientEventRecordsPage(Model model) {
        List<PatientEventRecord> patientEventRecordList = patientEventRecordService.getAllPatientEventRecord();
        model.addAttribute("patientEventRecords", patientEventRecordList);
        return "patients/ShowPatientEventRecords";
    }

    @GetMapping("/create")
    public String showCreatePatientEventRecordPage(Model model) {
        model.addAttribute("patientEventRecord", new PatientEventRecord());
        return "patients/createPatientEventRecord";
    }

    @PostMapping("/create")
    public String createPatientEventRecord(
            @Valid @ModelAttribute("patientEventRecord") PatientEventRecord patientEventRecord,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/createPatientEventRecord";
        }
        patientEventRecordService.savePatientEventRecord(patientEventRecord);
        return "redirect:/patientEventRecord/all";
    }


    @GetMapping("/update/{eventId}")
    public String showUpdatePatientEventRecordPage(
            @PathVariable("eventId") Integer eventId,
            Model model
    ) {
        PatientEventRecord patientEventRecord = patientEventRecordService.findPatientEventRecordById(eventId);
        model.addAttribute("patientEventRecord", patientEventRecord);
        return "patients/UpdatePatientEventRecord";
    }

    @PostMapping("/update/{eventId}")
    public String updatePatientEventRecord(
            @PathVariable("eventId") Integer eventId,
            @Valid @ModelAttribute("patientEventRecord") PatientEventRecord patientEventRecord,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/UpdatePatientEventRecord";
        }
        patientEventRecordService.updatePatientEventRecord(eventId, patientEventRecord);
        return "redirect:/patientEventRecord/all";
    }


    @PostMapping("/delete")
    public String deletePatientEventRecord(@RequestParam("patientEventRecordId") List<Integer> patientEventRecordIds) {
        patientEventRecordService.deletePatientEventRecords(patientEventRecordIds);
        return "redirect:/patientEventRecord/all";
    }

    @GetMapping("/search")
    public String searchPatientEventRecords(@RequestParam("keyword") String keyword, Model model) {
        Integer id = Integer.valueOf(keyword);
        List<PatientEventRecord> patientEventRecordList = patientEventRecordService.searchPatientEventRecords(id);
        model.addAttribute("patientEventRecords", patientEventRecordList);
        return "patients/ShowPatientEventRecords";
    }
}

