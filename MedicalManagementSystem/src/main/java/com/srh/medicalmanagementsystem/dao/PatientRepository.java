package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
       @Query("Select p from Patient p where p.firstName like %:keyword% or p.lastName like %:keyword% or p.email like %:keyword%")
       List<Patient> searchPatients(@Param("keyword") String keyword);
}
