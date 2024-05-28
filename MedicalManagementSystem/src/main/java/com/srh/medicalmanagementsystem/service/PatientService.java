package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();

    public Patient savePatient(Patient patient);

    public Patient updatePatient(int id, Patient patientDetails);

    public boolean deletePatient(Integer patientId);

    public Patient findPatientById(Integer patientId);

    public List<Patient> searchPatients(String keyword);
}
