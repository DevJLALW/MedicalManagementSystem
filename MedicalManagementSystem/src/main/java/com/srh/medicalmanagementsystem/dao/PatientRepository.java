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

       @Query("SELECT p FROM Patient p WHERE  p.doctor.employeeId = :employeeId OR p.nurse.employeeId = :employeeId")
       List<Patient> findPatientsByEmployeeId(@Param("employeeId") Integer employeeId);

       @Query("SELECT p FROM Patient p WHERE (p.doctor.employeeId = :employeeId OR p.nurse.employeeId = :employeeId) AND p.patientId = :patientId")
       List<Patient> findPatientByEmployeeIdAndPatientId(@Param("employeeId") Integer employeeId, @Param("patientId") Integer patientId);

       @Query("SELECT p FROM Patient p WHERE (p.doctor.employeeId = :employeeId OR p.nurse.employeeId = :employeeId) AND (p.firstName LIKE %:keyword% OR p.lastName LIKE %:keyword% OR p.email LIKE %:keyword%)")
       List<Patient> findPatientByEmployeeIdAndPatientKeyword(@Param("employeeId") Integer employeeId, @Param("keyword") String keyword);

       @Modifying
       @Transactional
       @Query("UPDATE Patient p SET p.status = 0 WHERE p.patientId IN :patientIds")
       int updateStatusToInactive(@Param("patientIds") List<Integer> patientIds);

       @Modifying
       @Transactional
       @Query("UPDATE Patient p SET p.password = :password WHERE p.patientId = :patientId")
       int updatePatientPassword(@Param("patientId") Integer patientId, @Param("password") String password);

       @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.doctor d LEFT JOIN FETCH p.nurse n LEFT JOIN FETCH p.user WHERE p.patientId = :patientId")
       Patient findByIdWithRelations(@Param("patientId") int patientId);

}
