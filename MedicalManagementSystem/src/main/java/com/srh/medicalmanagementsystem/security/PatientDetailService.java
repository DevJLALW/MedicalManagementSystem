package com.srh.medicalmanagementsystem.security;

import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientDetailService implements UserDetailsService {

    //private final PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

   /* public PatientDetailService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientService.findPatientById(Integer.parseInt(username));

        if (patient != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(patient.getPatientId()))
                    .password(patient.getPassword())
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
