package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
