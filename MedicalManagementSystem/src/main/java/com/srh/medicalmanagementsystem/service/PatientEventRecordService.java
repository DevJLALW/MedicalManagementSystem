package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.PatientEventRecord;

import java.util.List;

public interface PatientEventRecordService {
    List<PatientEventRecord> getAllPatientEventRecords();
    PatientEventRecord getPatientEventRecordById(int eventId);
    public PatientEventRecord savePatientEventRecord(PatientEventRecord patientEventRecord);
    void updatePatientEventRecord(PatientEventRecord patientEventRecord);
    void deletePatientEventRecord(int eventId);
}
