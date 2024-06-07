package com.srh.medicalmanagementsystem.security;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import com.srh.medicalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private PatientService patientService;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = null;
        Employee employee = null;

        try {
            Integer id = Integer.parseInt(username);
            patient = patientService.findPatientById(id);
        } catch (Exception e) {
            System.out.println("Exception"+e);
        }

        if (patient == null) {
            employee = employeeService.findEmployeeById(Integer.parseInt(username));
        }


        if (patient != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(patient.getPatientId()))
                    .password(patient.getPassword())
                    .roles("ADMIN")
                    .build();
        } else if (employee != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(employee.getEmployeeId()))
                    .password(employee.getPassword())
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
