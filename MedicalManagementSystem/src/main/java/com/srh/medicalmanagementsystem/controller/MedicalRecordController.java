package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.service.MedicalRecordService;
import com.srh.medicalmanagementsystem.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

    /*private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords () {
        return medicalRecordService.getAllMedicalRecord();
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        MedicalRecord newMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
        return ResponseEntity.ok(newMedicalRecord);
    };

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Integer id, @RequestBody MedicalRecord medicalRecordDetails) {
        try{
            MedicalRecord updatedMedicalRecordDetails = medicalRecordService.updateMedicalRecord(id, medicalRecordDetails);
            return ResponseEntity.ok(updatedMedicalRecordDetails);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> findMedicalRecordById(@PathVariable Integer id) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordById(id);

            return ResponseEntity.ok(medicalRecord);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}
