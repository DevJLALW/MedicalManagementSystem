package com.srh.medicalmanagementsystem.controller;


import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicalRecords")
public class MedicalRecordControllerPages {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordControllerPages(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/all")
    public String getAllMedicalRecordsPage(Model model) {
        List<MedicalRecord> medicalRecordList= medicalRecordService.getAllMedicalRecord();
        model.addAttribute("medicalRecords",medicalRecordList);

        return "patients/ShowMedicalRecords";
    }

    @GetMapping("/create")
    public String showCreateMedicalRecordPage(Model model) {
        model.addAttribute("medicalRecord", new MedicalRecord());
        return "patients/createMedicalRecord";
    }

    @PostMapping("/create")
    public String createMedicalRecord(
            @Valid @ModelAttribute("medicalRecord") MedicalRecord medicalRecord,
            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "patients/createMedicalRecord";
        }
        medicalRecordService.saveMedicalRecord(medicalRecord);
        return "redirect:/medicalRecords/all";
    }

    @GetMapping("/update/{medicalRecordId}")
    public String showUpdateMedicalRecordPage(
            @PathVariable("medicalRecordId") Integer medicalRecordId,
            Model model
    ) {
        MedicalRecord medicalRecord= medicalRecordService.findMedicalRecordById(medicalRecordId);
        model.addAttribute("medicalRecord", medicalRecord);
        return "patients/UpdateMedicalRecord";
    }

    @PostMapping("/update/{medicalRecordId}")
    public String updateMedicalRecord(
            @PathVariable("medicalRecordId") Integer medicalRecordId,
            @ModelAttribute("medicalRecord") MedicalRecord medicalRecord
    ) {
        medicalRecordService.updateMedicalRecord(medicalRecordId, medicalRecord);
        return "redirect:/medicalRecords/all";
    }

    @PostMapping("/delete")
    public String deleteMedicalRecord(@RequestParam("medicalRecordId") List<Integer> medicalRecordIds) {
        medicalRecordService.deleteMedicalRecords(medicalRecordIds);

        return "redirect:/medicalRecords/all";
    }
/*
    @GetMapping("/search")
    public String searchMedicalRecords(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<MedicalRecord> medicalRecordList =medicalRecordService.searchMedicalRecords(keyword);
        model.addAttribute("medicalRecords", medicalRecordList);

        return "medicalRecords/ShowMedicalRecords";
    }*/


    @GetMapping("/search")
    public String searchMedicalRecords(@RequestParam("keyword") String keyword, Model model) {
        Integer id = Integer.valueOf(keyword);
        List<MedicalRecord> medicalRecordList = medicalRecordService.searchMedicalRecords(id);
        model.addAttribute("medicalRecords", medicalRecordList);
        return "patients/ShowMedicalRecords";
    }

    @GetMapping("/searchByPatientId")
    public String findMedicalRecordByPatientId(@RequestParam("patientId") Integer patientId, Model model) {
        List<MedicalRecord> medicalRecordList = medicalRecordService.findMedicalRecordByPatientId(patientId);
        model.addAttribute("medicalRecords", medicalRecordList);
        return "patients/ShowMedicalRecords";
    }
}
