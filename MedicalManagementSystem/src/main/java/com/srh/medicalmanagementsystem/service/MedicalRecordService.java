package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.MedicalRecordRepository;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {


    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecord(){

        List<MedicalRecord> medicalRecord=new ArrayList<MedicalRecord>();
        medicalRecordRepository.findAll().forEach(medicalRecord1 -> medicalRecord.add(medicalRecord1));
        return medicalRecord;
    }

    public MedicalRecord getMedicalRecordById(int id){
        return medicalRecordRepository.findById(id).get();
    }

    public void saveOrUpdate(MedicalRecord medicalRecord){
        medicalRecordRepository.save(medicalRecord);
    }

    public void delete(int id){

        medicalRecordRepository.deleteById(id);
    }

    public void update(MedicalRecord medicalRecord, int medicalRecordId){
        medicalRecordRepository.save(medicalRecord);
    }
}
