package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PharmacyInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PharmacyInventoryRepository extends JpaRepository<PharmacyInventory, Integer> {
    List<PharmacyInventory> findByMedicineNameContainingIgnoreCase(String name);
}