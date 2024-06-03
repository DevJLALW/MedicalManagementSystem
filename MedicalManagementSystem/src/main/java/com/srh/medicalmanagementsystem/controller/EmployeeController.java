package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees () {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    };

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        try{
            Employee updatedEmployeeDetails = employeeService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(updatedEmployeeDetails);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.findEmployeeById(id);

            return ResponseEntity.ok(employee);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
