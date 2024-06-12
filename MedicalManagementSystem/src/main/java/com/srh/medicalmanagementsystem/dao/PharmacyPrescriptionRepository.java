package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PharmacyPrescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyPrescriptionRepository extends JpaRepository<PharmacyPrescription, Integer> {
    List<PharmacyPrescription> findByPatientID(int patientID);
}