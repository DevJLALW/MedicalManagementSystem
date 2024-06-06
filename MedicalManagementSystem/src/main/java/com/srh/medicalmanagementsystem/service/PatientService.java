package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.entity.PatientDTO;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();

    public Patient savePatient(PatientDTO patientDTO);

    public Patient updatePatient(int id, Patient patientDetails);

    public boolean deletePatients(List<Integer> patientIds);

    public Patient findPatientById(Integer patientId);

    public List<Patient> searchPatients(String keyword);
}
