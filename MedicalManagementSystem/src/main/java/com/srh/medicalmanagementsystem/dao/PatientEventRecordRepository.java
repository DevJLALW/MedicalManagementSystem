package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientEventRecordRepository extends JpaRepository<PatientEventRecord, Integer> {


    List<PatientEventRecord> findByEventId(Integer eventId);
    List<PatientEventRecord> findByPatientId(Integer patientId);

}
