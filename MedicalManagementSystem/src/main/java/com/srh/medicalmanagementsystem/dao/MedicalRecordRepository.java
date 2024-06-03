package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {
   // List<MedicalRecord> searchMedicalRecords(String keyword);
}
