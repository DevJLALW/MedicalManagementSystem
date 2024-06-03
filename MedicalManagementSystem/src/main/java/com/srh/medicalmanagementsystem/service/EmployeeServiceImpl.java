package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.EmployeeRepository;
import com.srh.medicalmanagementsystem.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        employees.sort(Comparator.comparing(Employee::getStatus).reversed());
        return employees;
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
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
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.searchEmployees(keyword);
    }
}
