package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.PharmacyInventory;
import com.srh.medicalmanagementsystem.service.PharmacyInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PharmacyInventoryController {

    @Autowired
    private PharmacyInventoryService service;

    @GetMapping("/inventories")
    public String viewInventoryList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<PharmacyInventory> listInventories = service.searchInventories(keyword);
        model.addAttribute("listInventories", listInventories);
        model.addAttribute("keyword", keyword);
        return "patients/Inventories";
    }

    @GetMapping("/inventories/new")
    public String showNewInventoryForm(Model model) {
        PharmacyInventory inventory = new PharmacyInventory();
        model.addAttribute("inventory", inventory);
        return "patients/NewInventory";
    }

    @PostMapping("/inventories")
    public String saveInventory(@ModelAttribute("inventory") PharmacyInventory inventory, Model model) {
        try {
            service.saveInventory(inventory);
            return "redirect:/inventories";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving inventory: " + e.getMessage());
            return "redirect:/inventories/new";
        }
    }

    @GetMapping("/inventories/update/{id}")
    public String showUpdateInventoryForm(@PathVariable("id") int id, Model model) {
        PharmacyInventory inventory = service.getInventoryById(id);
        model.addAttribute("inventory", inventory);
        return "patients/UpdateInventory";
    }

    @PostMapping("/inventories/{id}")
    public String updateInventory(@PathVariable("id") int id, @ModelAttribute("inventory") PharmacyInventory inventory, Model model) {
        try {
            service.updateInventory(id, inventory);
            return "redirect:/inventories";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating inventory: " + e.getMessage());
            return "patients/UpdateInventory";
        }
    }

    @GetMapping("/inventories/delete/{id}")
    public String deleteInventory(@PathVariable("id") int id) {
        service.deleteInventory(id);
        return "redirect:/inventories";
    }
}