package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
       @Query("SELECT p FROM Patient p WHERE  p.patientId = :patientId OR (p.firstName LIKE %:keyword% OR p.lastName LIKE %:keyword% OR p.email LIKE %:keyword%)")
       List<Patient> searchPatients(@Param("patientId") Integer patientId, @Param("keyword") String keyword);
}
