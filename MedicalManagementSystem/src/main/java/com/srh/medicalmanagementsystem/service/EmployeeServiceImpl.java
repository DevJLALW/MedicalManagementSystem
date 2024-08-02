package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.DoctorNurseAssignmentRepository;
import com.srh.medicalmanagementsystem.dao.EmployeeRepository;
import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private  DoctorNurseAssignmentRepository doctorNurseAssignmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PatientRepository patientRepository, PasswordEncoder passwordEncoder, DoctorNurseAssignmentRepository doctorNurseAssignmentRepository) {

        this.employeeRepository = employeeRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorNurseAssignmentRepository=doctorNurseAssignmentRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAllActiveEmployees();
        employees.sort(Comparator.comparing(Employee::getStatus).reversed());
        return employees;
    }

    @Override
    public List<Employee> getAllInactiveEmployees() {
        List<Employee> employees = employeeRepository.findAllInactiveEmployees();
        employees.sort(Comparator.comparing(Employee::getStatus).reversed());
        return employees;
    }


    @Transactional
    public Employee saveEmployee(Employee employee) {

        if(employee.getPassword() != null && !employee.getPassword().isEmpty()){

            String encoderPassword = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(encoderPassword);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id " + employeeId));
        String encoderPassword = passwordEncoder.encode(employeeDetails.getPassword());
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setContactNumber(employeeDetails.getContactNumber());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPassword(encoderPassword);
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setStatus(employeeDetails.getStatus());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public boolean deleteEmployees(List<Integer> employeeIds) {
        boolean allDeleted = true;
        for(Integer employeeId: employeeIds) {
            if (employeeRepository.existsById(employeeId)) {
                Employee deletingEmployee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new NoSuchElementException("Employee not found with id " + employeeId));
                deletingEmployee.setStatus(0);
                employeeRepository.save(deletingEmployee);
            } else {
                allDeleted= false;
            }
        }
        return allDeleted;
    }

    @Override
    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id " + employeeId));
    }

    @Override
    public List<Employee> searchEmployeesByKeyword(String keyword) {
        return employeeRepository.searchEmployeesByKeyword(keyword);
    }

    @Override
    public List<Employee> searchEmployeesById(Integer employeeId) {
        return employeeRepository.searchEmployeesById(employeeId);
    }

    @Override
    public List<Patient> seachAssignedPatientsByKeyword(Integer employeeId, String keyword) {
        return patientRepository.findPatientByEmployeeIdAndPatientKeyword(employeeId, keyword);
    }

    @Override
    public List<Patient> seachAssignedPatientsByPatientId(Integer employeeId, Integer patientId) {
        return patientRepository.findPatientByEmployeeIdAndPatientId(employeeId, patientId);
    }

    @Override
    public List<Employee> getRoleSpecificEmployees(String keyword) {
        return employeeRepository.getRoleSpecificEmployees(keyword);
    }

    @Override
    @Cacheable("doctors")
    public List<Employee> searchDoctorsByName(String name) {

       // System.out.println("Fetching doctors by name from database: " + name);
        return employeeRepository.searchDoctorsByName(name);
    }

    @Override
    public List<Employee> searchNurseByName(String name) {

       // System.out.println("searchNurseByName from database: " + name);
        return employeeRepository.searchNurseByName(name);
    }

    @Override
    public List<Patient> getAllAssignedPatients(Integer employeeId) {
        return patientRepository.findPatientsByEmployeeId(employeeId);
    }

    @Cacheable(value = "nurses", key = "#doctorId")
    public List<Employee> findNursesByDoctorId(int doctorId) {
       // System.out.println("Fetching Nurse by name from database: " + doctorId);
        return doctorNurseAssignmentRepository.findNursesByDoctorId(doctorId);
    }
}
