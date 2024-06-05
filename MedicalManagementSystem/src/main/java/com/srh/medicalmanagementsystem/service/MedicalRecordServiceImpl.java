package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.MedicalRecordRepository;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService
{
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }


    @Override
    public List<MedicalRecord> getAllMedicalRecord() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        medicalRecords.sort(Comparator.comparing(MedicalRecord::getMedicalRecordId).reversed());
        return medicalRecords;
    }

    @Transactional
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }


    @Override
    public MedicalRecord updateMedicalRecord(int medicalRecordId, MedicalRecord medicalRecordDetails) {
        MedicalRecord existingMedicalRecord = medicalRecordRepository.findById(medicalRecordId)
                .orElseThrow(() -> new NoSuchElementException("MedicalRecord not found with id " + medicalRecordId));
        //existingMedicalRecord.getMedicalRecordId(medicalRecordDetails.getMedicalRecordId());
        existingMedicalRecord.setDiagnosis(medicalRecordDetails.getDiagnosis());
        existingMedicalRecord.setDiagnosisNotes(medicalRecordDetails.getDiagnosisNotes());
        existingMedicalRecord.setMedicalResult(medicalRecordDetails.getMedicalResult());
        existingMedicalRecord.setMedicationsPrescribed(medicalRecordDetails.getMedicationsPrescribed());
        existingMedicalRecord.setDate(medicalRecordDetails.getDate());
        existingMedicalRecord.setEmployeeId(medicalRecordDetails.getEmployeeId());
        existingMedicalRecord.setPatientId(medicalRecordDetails.getPatientId());
        return medicalRecordRepository.save(existingMedicalRecord);
    }

    @Override
    public boolean deleteMedicalRecords(List<Integer> medicalRecordIds) {
        boolean allDeleted = true;
        for(Integer medicalRecordId: medicalRecordIds) {
            if (medicalRecordRepository.existsById(medicalRecordId)) {
                medicalRecordRepository.deleteById(medicalRecordId);

            } else {
                allDeleted= false;
            }
        }
        return allDeleted;
    }


    @Override
    public MedicalRecord findMedicalRecordById(Integer medicalRecordId) {
        return medicalRecordRepository.findById(medicalRecordId)
                .orElseThrow(() -> new NoSuchElementException("MedicalRecord not found with id " + medicalRecordId));
    }

    @Override
   public List<MedicalRecord> searchMedicalRecords(Integer id) {
        return medicalRecordRepository.searchMedicalRecords(id);
    }

    @Override
    public List<MedicalRecord> findMedicalRecordByPatientId(Integer patientId) {
        return medicalRecordRepository.findMedicalRecordByPatientId(patientId);
    }


}

