package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.entity.PatientDTO;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> getAllPatients();

    public List<Patient> getInactivePatients();

    public Patient savePatient(PatientDTO patientDTO);

    public Patient updatePatient(int id, PatientDTO patientDetails);

    public boolean deletePatients(List<Integer> patientIds);

    public Patient findPatientById(Integer patientId);

    public List<Patient> searchPatients(String keyword);
    public void updatePassword(Integer patientId, String newPassword);
    public Patient findPatientById(int patientId);
}
