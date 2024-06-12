package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.PharmacyInventory;
import com.srh.medicalmanagementsystem.dao.PharmacyInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyInventoryService {

    @Autowired
    private PharmacyInventoryRepository repository;

    public List<PharmacyInventory> getAllInventories() {
        return repository.findAll();
    }

    public PharmacyInventory getInventoryById(int id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Inventory not found"));
    }

    public List<PharmacyInventory> getInventoriesByName(String name) {
        return repository.findByMedicineNameContainingIgnoreCase(name);
    }

    public PharmacyInventory saveInventory(PharmacyInventory inventory) {
        return repository.save(inventory);
    }

    public void deleteInventory(int id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Inventory not found");
        }
        repository.deleteById(id);
    }

    public List<PharmacyInventory> searchInventories(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return getInventoriesByName(keyword);
        } else {
            return getAllInventories();
        }
    }

    public PharmacyInventory updateInventory(int id, PharmacyInventory updatedInventory) {
        PharmacyInventory existingInventory = getInventoryById(id);
        existingInventory.setMedicineName(updatedInventory.getMedicineName());
        existingInventory.setMedicineDescription(updatedInventory.getMedicineDescription());
        existingInventory.setStockQuantity(updatedInventory.getStockQuantity());
        existingInventory.setExpiryDate(updatedInventory.getExpiryDate());
        existingInventory.setMedicinePrice(updatedInventory.getMedicinePrice());
        existingInventory.setEmployeeID(updatedInventory.getEmployeeID());
        existingInventory.setLastUpdated(LocalDateTime.now());
        return repository.save(existingInventory);
    }
}