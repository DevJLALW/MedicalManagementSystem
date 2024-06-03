package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping("/records")
    public String getAllMedicalRecord(Model model) {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecord();
        model.addAttribute("records", records);
        return "records";
    }

    @RequestMapping("/medical/save")
    public String showForm(Model model) {
        MedicalRecord medicalRecord = new MedicalRecord();
        model.addAttribute("medicalRecord", medicalRecord);
        return "patients/medicalRecord";
    }

    @PostMapping("/medical/save")
    public String submitForm(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        medicalRecordService.saveOrUpdate(medicalRecord);
        return "redirect:/medical/save/success";
    }

    @GetMapping("/medical/save/success")
    public String showSuccess() {
        return "patients/medicalRecord_success";
    }



}