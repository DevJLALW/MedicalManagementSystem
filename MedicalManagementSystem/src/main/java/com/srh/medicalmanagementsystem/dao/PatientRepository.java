package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
       @Query("SELECT p FROM Patient p WHERE  p.patientId = :patientId OR (p.firstName LIKE %:keyword% OR p.lastName LIKE %:keyword% OR p.email LIKE %:keyword%)")
       List<Patient> searchPatients(@Param("patientId") Integer patientId, @Param("keyword") String keyword);

       @Query("SELECT p FROM Patient p WHERE p.status = 1")
       List<Patient> findAllActivePatients();

       @Query("SELECT p FROM Patient p WHERE p.status = 0")
       List<Patient> findInactivePatients();

       @Modifying
       @Transactional
       @Query("UPDATE Patient p SET p.status = 0 WHERE p.patientId IN :patientIds")
       int updateStatusToInactive(@Param("patientIds") List<Integer> patientIds);
}
