package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.EmployeeRepository;
import com.srh.medicalmanagementsystem.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {

        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
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
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setContactNumber(employeeDetails.getContactNumber());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPassword(employeeDetails.getPassword());
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
    public List<Employee> getRoleSpecificEmployees(String keyword) {
        return employeeRepository.getRoleSpecificEmployees(keyword);
    }

    @Override
    public List<Employee> searchDoctorsByName(String name) {
      
        return employeeRepository.searchDoctorsByName(name);
    }

    @Override
    public List<Employee> searchNurseByName(String name) {

        return employeeRepository.searchNurseByName(name);
    }
}
