package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.entity.PatientDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PasswordEncoder passwordEncoder){
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        patients.sort(Comparator.comparing(Patient::getPatientId).reversed());
        return patients;
    }

    @Transactional
    public Patient savePatient(PatientDTO patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        System.out.println("patient.getPassword(): "+patient.getPassword());

        if (patient.getPassword() != null && !patient.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(patient.getPassword());
            patient.setPassword(encodedPassword);
            System.out.println("Check if password is encoded");
        }
        patient.setEmployeeID(patientDTO.getEmployeeID());
        return patientRepository.save(patient);
    }

    private Patient convertToEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();

        patient.setPatientId(patientDTO.getPatientId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDob(patientDTO.getDob());
        patient.setGender(patientDTO.getGender());
        patient.setContactNumber(patientDTO.getContactNumber());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        patient.setInsuranceID(patientDTO.getInsuranceID());
        patient.setDoctorID(patientDTO.getDoctorID());
        patient.setNurseID(patientDTO.getNurseID());
        patient.setRoomID(patientDTO.getRoomID());
        patient.setRecordID(patientDTO.getRecordID());
        patient.setPassword(patientDTO.getPassword());
        patient.setEmployeeID(patientDTO.getEmployeeID());

        return patient;
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
            existingPatient.setMedicalHistory(patientDetails.getMedicalHistory());
            existingPatient.setInsuranceID(patientDetails.getInsuranceID());
            existingPatient.setDoctorID(patientDetails.getDoctorID());
            existingPatient.setRoomID(patientDetails.getRoomID());
            existingPatient.setRecordID(patientDetails.getRecordID());
            existingPatient.setEmployeeID(patientDetails.getEmployeeID());

            return patientRepository.save(existingPatient);

    }

        public boolean deletePatients(List<Integer> patientIds) {
            boolean allDeleted = true;
            for(Integer patientId: patientIds) {
                if (patientRepository.existsById(patientId)) {
                    patientRepository.deleteById(patientId);

                } else {
                    allDeleted= false;
                }
            }
            return allDeleted;
        }


    public Patient findPatientById(Integer patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id " + patientId));
    }

   public List<Patient> searchPatients(String keyword){
       Integer patientId = null;
       try {
           patientId = Integer.valueOf(keyword);
       } catch (NumberFormatException ignored) {
       }
        return patientRepository.searchPatients(patientId,keyword);
    }
}
