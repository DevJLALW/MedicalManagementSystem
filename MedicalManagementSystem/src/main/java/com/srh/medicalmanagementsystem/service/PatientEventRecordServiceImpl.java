package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PatientEventRecordRepository;
import com.srh.medicalmanagementsystem.entity.PatientEventRecord;

import com.srh.medicalmanagementsystem.service.PatientEventRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientEventRecordServiceImpl implements PatientEventRecordService {

    private final PatientEventRecordRepository patientEventRecordRepository;

    @Autowired
    public PatientEventRecordServiceImpl(PatientEventRecordRepository patientEventRecordRepository) {
        this.patientEventRecordRepository = patientEventRecordRepository;
    }

    @Override
    public List<PatientEventRecord> getAllPatientEventRecords() {
        return patientEventRecordRepository.findAll();
    }

    @Override
    public PatientEventRecord getPatientEventRecordById(int eventId) {
        Optional<PatientEventRecord> result = patientEventRecordRepository.findById(eventId);
        return result.orElse(null);
    }

    @Override
    public PatientEventRecord savePatientEventRecord(PatientEventRecord patientEventRecord) {
        System.out.println("Reached implementation class"+patientEventRecord.getPatientId()+
                " "+patientEventRecord.getEventId()+" "+patientEventRecord.getEventDescriptions());
        return patientEventRecordRepository.save(patientEventRecord);
    }

    @Override
    public void updatePatientEventRecord(PatientEventRecord patientEventRecord) {
        patientEventRecordRepository.save(patientEventRecord);
    }

    @Override
    public void deletePatientEventRecord(int eventId) {
        patientEventRecordRepository.deleteById(eventId);
    }
}
