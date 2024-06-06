package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.dao.EventDescriptionsRepository;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import com.srh.medicalmanagementsystem.service.PatientEventRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patientEventRecord")
public class PatientEventRecordController {


    private final PatientEventRecordService patientEventRecordService;

    private final EventDescriptionsRepository eventDescriptionsRepository;

    public PatientEventRecordController(PatientEventRecordService patientEventRecordService, EventDescriptionsRepository eventDescriptionsRepository) {
        this.patientEventRecordService = patientEventRecordService;
        this.eventDescriptionsRepository = eventDescriptionsRepository;
    }

    @GetMapping("/all")
    public String getAllPatientEventRecordsPage(Model model) {
        List<PatientEventRecord> patientEventRecordList = patientEventRecordService.getAllPatientEventRecords();
        model.addAttribute("patientEventRecords", patientEventRecordList);
        return "patients/ShowPatientEventRecords";
    }

    @GetMapping("/create")
    public String showCreatePatientEventRecordPage(Model model) {
        model.addAttribute("patientEventRecord", new PatientEventRecord());
        return "patients/createPatientEventRecord";
    }
/*
    @PostMapping("/create")
    public String createPatientEventRecord(
            @Valid @ModelAttribute("patientEventRecord") PatientEventRecord patientEventRecord,
            BindingResult bindingResult
    ) {
        System.out.println("Reached Post Creat");
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult"+bindingResult);
            return "patients/createPatientEventRecord";
        }
        patientEventRecordService.savePatientEventRecord(patientEventRecord);
        return "redirect:/patientEventRecords/all";
    }
*/

    @PostMapping("/create")
    public String createPatientEventRecord(
            @Valid @ModelAttribute("patientEventRecord") PatientEventRecord patientEventRecord,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/createPatientEventRecord";
        }

        // Ensure the eventDescriptions is saved first
        if (patientEventRecord.getEventDescriptions() != null) {
            eventDescriptionsRepository.save(patientEventRecord.getEventDescriptions());
        }

        patientEventRecordService.savePatientEventRecord(patientEventRecord);
        return "redirect:/patientEventRecord/all";
    }

    @GetMapping("/update/{patientEventRecordId}")
    public String showUpdatePatientEventRecordPage(
            @PathVariable("patientEventRecordId") Integer patientEventRecordId,
            Model model
    ) {
        PatientEventRecord patientEventRecord = patientEventRecordService.getPatientEventRecordById(patientEventRecordId);
        model.addAttribute("patientEventRecord", patientEventRecord);
        return "patients/UpdatePatientEventRecord";
    }
    /*

    @PostMapping("/update/{patientEventRecordId}")
    public String updatePatientEventRecord(
            @PathVariable("patientEventRecordId") Integer patientEventRecordId,
            @Valid @ModelAttribute("patientEventRecord") PatientEventRecord patientEventRecord,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/UpdatePatientEventRecord";
        }
        patientEventRecordService.updatePatientEventRecord(patientEventRecordId, patientEventRecord);
        return "redirect:/patientEventRecord/all";
    }

    @PostMapping("/delete")
    public String deletePatientEventRecord(@RequestParam("eventId") List<Integer> eventId) {
        patientEventRecordService.deletePatientEventRecord(eventId);
        return "redirect:/patientEventRecord/all";
    }

    @GetMapping("/search")
    public String searchPatientEventRecords(@RequestParam("keyword") String keyword, Model model) {
        Integer id = Integer.valueOf(keyword);
        List<PatientEventRecord> patientEventRecordList = patientEventRecordService.getPatientEventRecordById(eventId);
        model.addAttribute("patientEventRecords", patientEventRecordList);
        return "patients/ShowPatientEventRecords";
    }

    @GetMapping("/searchByPatientId")
    public String findPatientEventRecordByPatientId(@RequestParam("patientId") Integer patientId, Model model) {
        List<PatientEventRecord> patientEventRecordList = patientEventRecordService.findPatientEventRecordByPatientId(patientId);
        model.addAttribute("patientEventRecords", patientEventRecordList);
        return "patients/ShowPatientEventRecords";
    }*/
}

