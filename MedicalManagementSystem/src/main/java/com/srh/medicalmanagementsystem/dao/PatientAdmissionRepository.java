package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PatientAdmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAdmissionRepository extends JpaRepository<PatientAdmission, Integer> {
}

