package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {


 //   @Query("SELECT m FROM MedicalRecord m WHERE m.diagnosis LIKE %:keyword% OR m.diagnosisNotes LIKE %:keyword%")

   // List<MedicalRecord> searchMedicalRecords(String keyword);
    // List<MedicalRecord> searchMedicalRecords(String keyword);

   // @Query("SELECT p FROM MedicalRecord p WHERE  p.medicalRecordId = :medicalRecordId OR (p.diagnosis LIKE %:keyword% OR p.patientId LIKE %:keyword% OR p.employeeId LIKE %:keyword%)")
    //List<MedicalRecord> searchMedicalRecords(@Param("medicalRecordId") Integer medicalRecordId, @Param("keyword") String keyword);



    @Query("SELECT p FROM MedicalRecord p WHERE  p.patientId = :id OR p.employeeId = :id")
    List<MedicalRecord> searchMedicalRecords(@Param("id") Integer id);

    @Query("SELECT m FROM MedicalRecord m WHERE m.patientId = :patientId")
    List<MedicalRecord> findMedicalRecordByPatientId(@Param("patientId") Integer patientId);
}
