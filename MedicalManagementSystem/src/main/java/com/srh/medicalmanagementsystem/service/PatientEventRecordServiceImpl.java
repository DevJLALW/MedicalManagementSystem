package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PatientEventRecordRepository;
import com.srh.medicalmanagementsystem.dao.PatientEventRecordRepository;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;

import com.srh.medicalmanagementsystem.service.PatientEventRecordService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientEventRecordServiceImpl implements PatientEventRecordService {


    private final PatientEventRecordRepository patientEventRecordRepository;

    @Autowired
    public PatientEventRecordServiceImpl(PatientEventRecordRepository patientEventRecordRepository) {
        this.patientEventRecordRepository = patientEventRecordRepository;
    }

    @Override
    public List<PatientEventRecord> getAllPatientEventRecord() {
        List<PatientEventRecord> patientEventRecords = patientEventRecordRepository.findAll();
        patientEventRecords.sort(Comparator.comparing(PatientEventRecord::getEventId).reversed());
        return patientEventRecords;
    }

    @Transactional
    public PatientEventRecord savePatientEventRecord(PatientEventRecord patientEventRecord) {
        patientEventRecord.setStatus(1);
        return patientEventRecordRepository.save(patientEventRecord);
    }

    @Override
    public PatientEventRecord updatePatientEventRecord(Integer patientEventRecordId, PatientEventRecord patientEventRecordDetails) {
        PatientEventRecord existingPatientEventRecord = patientEventRecordRepository.findById(patientEventRecordId)
                .orElseThrow(() -> new NoSuchElementException("PatientEventRecord not found with id " + patientEventRecordId));
        existingPatientEventRecord.setPatientId(patientEventRecordDetails.getPatientId());
        existingPatientEventRecord.setEventDate(patientEventRecordDetails.getEventDate());
        existingPatientEventRecord.setEventType(patientEventRecordDetails.getEventType());
        existingPatientEventRecord.setDetails(patientEventRecordDetails.getDetails());
        existingPatientEventRecord.setAssignedDoctorId(patientEventRecordDetails.getAssignedDoctorId());
        existingPatientEventRecord.setAssignedNurseId(patientEventRecordDetails.getAssignedNurseId());
        return patientEventRecordRepository.save(existingPatientEventRecord);
    }

    @Override
    public boolean deletePatientEventRecords(List<Integer> patientEventRecordIds) {
        boolean allDeleted = true;
        for (Integer patientEventRecordId : patientEventRecordIds) {
            if (patientEventRecordRepository.existsById(patientEventRecordId)) {
                patientEventRecordRepository.deleteById(patientEventRecordId);
            } else {
                allDeleted = false;
            }
        }
        return allDeleted;
    }

    @Override
    public PatientEventRecord findPatientEventRecordById(Integer patientEventRecordId) {
        return patientEventRecordRepository.findById(patientEventRecordId)
                .orElseThrow(() -> new NoSuchElementException("PatientEventRecord not found with id " + patientEventRecordId));
    }

    @Override
    public List<PatientEventRecord> searchPatientEventRecords(Integer id) {
        return patientEventRecordRepository.findByEventId(id);
    }

    @Override
    public List<PatientEventRecord> findPatientEventRecordByPatientId(Integer patientId) {
        return patientEventRecordRepository.findByPatientId(patientId);
    }


}
