package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PharmacyPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyPrescriptionRepository extends JpaRepository<PharmacyPrescription, Integer> {
}
