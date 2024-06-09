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
       // List<Patient> patients = patientRepository.findAll();
        List<Patient> patients = patientRepository.findAllActivePatients();
        patients.sort(Comparator.comparing(Patient::getPatientId).reversed());
        return patients;
    }

    @Override
    public List<Patient> getInactivePatients() {

        List<Patient> patients = patientRepository.findInactivePatients();
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
        patient.setStatus(1);
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
    public Patient updatePatient(int patientId, PatientDTO patientDetails) {
        Patient patient = convertToEntity(patientDetails);
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id " + patientId));

            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setDob(patient.getDob());
            existingPatient.setGender(patient.getGender());
            existingPatient.setContactNumber(patient.getContactNumber());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setMedicalHistory(patient.getMedicalHistory());
            existingPatient.setInsuranceID(patient.getInsuranceID());
            existingPatient.setDoctorID(patient.getDoctorID());
            existingPatient.setRoomID(patient.getRoomID());
            existingPatient.setRecordID(patient.getRecordID());
          //  existingPatient.setEmployeeID(patient.getEmployeeID());

        if (patient.getPassword() != null && !patient.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(patient.getPassword());
            existingPatient.setPassword(encodedPassword);

        }
            return patientRepository.save(existingPatient);

    }

        public boolean deletePatients(List<Integer> patientIds) {
            int affectedRows = patientRepository.updateStatusToInactive(patientIds);
            return affectedRows > 0;

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

    @Override
    public void updatePassword(Integer patientId, String newPassword) {
        System.out.println("Impl newPassword: " + newPassword);


        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id " + patientId));


        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);



            int updatedRows = patientRepository.updatePatientPassword(patientId, encodedPassword);


            
        }
    }
}
