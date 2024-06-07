package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

   /* @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }*/

   /* @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patientDetails) {
       try{
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
            return ResponseEntity.ok(updatedPatient);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        boolean isDeleted = patientService.deletePatient(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // returns a 204 No Content response.
        } else {
            return ResponseEntity.notFound().build(); // returns a 404 Not Found response.
        }
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Integer id) {
        try {
        Patient patient = patientService.findPatientById(id);


            return ResponseEntity.ok(patient);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
