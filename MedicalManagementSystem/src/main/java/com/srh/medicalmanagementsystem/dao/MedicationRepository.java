package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}

