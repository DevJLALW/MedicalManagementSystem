package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.MedicalRecord;


import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    public List<MedicalRecord> getAllMedicalRecord();

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    public MedicalRecord updateMedicalRecord(int medicalRecordId, MedicalRecord medicalRecordDetails);

    public boolean deleteMedicalRecords(List<Integer> medicalRecordId);

//    public MedicalRecord findMedicalRecordById(Integer medicalRecordId);

  //  public List<MedicalRecord> searchMedicalRecords(String keyword);

    // public List<MedicalRecord> searchMedicalRecords(String keyword);

    MedicalRecord findMedicalRecordById(Integer medicalRecordId);
    List<MedicalRecord> searchMedicalRecords(Integer id);

    List<MedicalRecord> findMedicalRecordByPatientId(Integer patientId);
}
