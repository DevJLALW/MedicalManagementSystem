package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(int patientId, Patient patientDetails) {
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id " + patientId));

        existingPatient.setFirstName(patientDetails.getFirstName());
            existingPatient.setLastName(patientDetails.getLastName());
            existingPatient.setDob(patientDetails.getDob());
            existingPatient.setGender(patientDetails.getGender());
            existingPatient.setContactNumber(patientDetails.getContactNumber());
            existingPatient.setEmail(patientDetails.getEmail());
            existingPatient.setAddress(patientDetails.getAddress());
            return patientRepository.save(existingPatient);

    }

    public boolean deletePatient(Integer patientId) {
        if (patientRepository.existsById(patientId)) {
            patientRepository.deleteById(patientId);
            return true;
        } else {
            return false;
        }
    }


    public Patient findPatientById(Integer patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id " + patientId));
    }
}
