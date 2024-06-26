package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.entity.Patient;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    List<Employee> getAllInactiveEmployees();

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(int id, Employee employeeDetails);

    public boolean deleteEmployees(List<Integer> employeeIds);

    public Employee findEmployeeById(Integer employeeId);

    public List<Employee> searchEmployeesByKeyword(String keyword);

    public List<Employee> searchEmployeesById(Integer employeeId);

    public List<Patient> seachAssignedPatientsByKeyword(Integer employeeId, String keyword);

    public List<Patient> seachAssignedPatientsByPatientId(Integer employeeId, Integer patientId);

    public  List<Employee> getRoleSpecificEmployees(String keyword);

    public List<Employee> searchDoctorsByName(String name);

    public List<Employee> searchNurseByName(String name);

    public List<Patient> getAllAssignedPatients(Integer doctorId);

    public List<Employee> findNursesByDoctorId(int doctorId);
}
