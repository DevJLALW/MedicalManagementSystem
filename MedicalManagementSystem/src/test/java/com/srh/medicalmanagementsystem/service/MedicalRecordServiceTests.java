package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.MedicalRecordRepository;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(MedicalRecordServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicalRecordServiceTests {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;


    @Test
    void testFindMedicalRecordByPatientId() {
        List<MedicalRecord> records = medicalRecordService.findMedicalRecordByPatientId(202);
        assertEquals(1, records.size());
        assertEquals("Hypertension", records.get(0).getDiagnosis());
    }

    @Test
    void testFindAllMedicalRecords() {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecord();
        assertFalse(records.isEmpty());
    }

    @Test
    void testSaveMedicalRecord() {
        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setDiagnosis("Asthma");
        newRecord.setDiagnosisNotes("Patient has trouble breathing.");
        newRecord.setMedicalResult("Managed with inhaler");
        newRecord.setMedicationsPrescribed("Inhaler");
        newRecord.setDate(java.sql.Date.valueOf(LocalDate.now()));
        newRecord.setEmployeeId(104);
        newRecord.setPatientId(204);

        MedicalRecord savedRecord = medicalRecordService.saveMedicalRecord(newRecord);
        assertNotNull(savedRecord);
        assertEquals("Asthma", savedRecord.getDiagnosis());
    }

    @Test
    void testUpdateMedicalRecord() {
        List<MedicalRecord> records = medicalRecordService.findMedicalRecordByPatientId(202);
        assertFalse(records.isEmpty());
        MedicalRecord record = records.get(0);
        record.setDiagnosis("Severe Hypertension");
        MedicalRecord updatedRecord = medicalRecordService.updateMedicalRecord(record.getMedicalRecordId(), record);
        assertEquals("Severe Hypertension", updatedRecord.getDiagnosis());
    }

    @Test
    void testDeleteMedicalRecords() {
       
        List<Integer> ids = List.of(3, 8);
        boolean allDeleted = medicalRecordService.deleteMedicalRecords(ids);
        assertTrue(allDeleted);
    }

}
