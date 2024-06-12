package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.PharmacyPrescription;
import com.srh.medicalmanagementsystem.service.PharmacyPrescriptionService;
import com.srh.medicalmanagementsystem.service.PharmacyInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PharmacyPrescriptionController {

    @Autowired
    private PharmacyPrescriptionService prescriptionService;

    @Autowired
    private PharmacyInventoryService inventoryService;

    @GetMapping("/prescriptions")
    public String viewPrescriptionList(Model model) {
        List<PharmacyPrescription> listPrescriptions = prescriptionService.getAllPrescriptions();
        model.addAttribute("listPrescriptions", listPrescriptions);
        return "/patients/ShowPrescriptions";
    }

    @GetMapping("/prescriptions/new")
    public String showNewPrescriptionForm(Model model) {
        PharmacyPrescription prescription = new PharmacyPrescription();
        model.addAttribute("prescription", prescription);
        model.addAttribute("inventories", inventoryService.getAllInventories());
        return "/patients/NewPrescription";
    }

    @PostMapping("/prescriptions/save")
    public String savePrescription(@ModelAttribute("prescription") PharmacyPrescription prescription, Model model) {
        try {
            prescriptionService.savePrescription(prescription);
            return "redirect:/prescriptions";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving prescription: " + e.getMessage());
            model.addAttribute("inventories", inventoryService.getAllInventories());
            return "/patients/NewPrescription";
        }
    }

    @GetMapping("/prescriptions/delete/{id}")
    public String deletePrescription(@PathVariable("id") int id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescriptions";
    }

    @GetMapping("/prescriptions/search")
    public String searchPrescriptions(@RequestParam("patientID") int patientID, Model model) {
        List<PharmacyPrescription> listPrescriptions = prescriptionService.searchPrescriptionsByPatientID(patientID);
        model.addAttribute("listPrescriptions", listPrescriptions);
        return "/patients/ShowPrescriptions";
    }
}
