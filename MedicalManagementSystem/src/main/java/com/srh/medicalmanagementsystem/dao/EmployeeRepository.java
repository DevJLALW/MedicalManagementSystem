package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("Select e from Employee e where e.firstName like %:keyword% or e.lastName like %:keyword% or e.email like %:keyword% or e.role like %:keyword%")
    List<Employee> searchEmployees(@Param("keyword") String keyword);
}
