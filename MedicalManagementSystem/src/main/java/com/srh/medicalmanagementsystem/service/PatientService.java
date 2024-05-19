package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();

    public Patient savePatient(Patient patient);

    public Optional<Patient> updatePatient(int id, Patient patientDetails);

    public boolean deletePatient(Integer patientId);

    public Optional<Patient> findPatientById(Integer patientId);
}
