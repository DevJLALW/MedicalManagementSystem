package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEventRecordRepository extends JpaRepository<PatientEventRecord, Integer> {
}
