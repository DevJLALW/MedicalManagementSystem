package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;

import java.util.List;

public interface PatientEventRecordService {

    List<PatientEventRecord> getAllPatientEventRecord();

    PatientEventRecord savePatientEventRecord(PatientEventRecord patientEventRecord);

    PatientEventRecord updatePatientEventRecord(Integer patientEventRecordId, PatientEventRecord patientEventRecordDetails);

    boolean deletePatientEventRecords(List<Integer> patientEventRecordIds);

    PatientEventRecord findPatientEventRecordById(Integer patientEventRecordId);

    List<PatientEventRecord> searchPatientEventRecords(Integer id);

    List<PatientEventRecord> findPatientEventRecordByPatientId(Integer patientId);
}
