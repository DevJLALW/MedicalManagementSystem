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

        System.out.println("Before");

        if (patient != null && patient.getStatus() == 1) {
            System.out.println("Before patient");
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(patient.getPatientId()))
                    .password(patient.getPassword())
                    .roles("PATIENT")
                    .build();
        } else if (employee != null && employee.getStatus() == 1) {
            System.out.println("Before employee");
            return org.springframework.security.core.userdetails.User.builder()
                    .username(String.valueOf(employee.getEmployeeId()))
                    .password(employee.getPassword())
                    .roles(employee.getRole().toUpperCase())
                    .build();
        } else {
            System.out.println("Invalid");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
