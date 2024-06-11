package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import com.srh.medicalmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorNurseAssignmentRepository extends JpaRepository <DoctorNurseAssignment, Integer> {
//        @Query("SELECT dna.assignmentId, dna.doctorId, doc.FirstName AS DoctorFirstName, doc.LastName AS DoctorLastName, dna.nurseId, nur.FirstName AS NurseFirstName, nur.LastName AS NurseLastName FROM DoctorNurseAssignment dna JOIN Employees doc ON dna.doctorId = doc.EmployeeID JOIN Employees nur ON dna.nurseId = nur.EmployeeID where dna.doctorId  = :employeeId or dna.nurseId  = :employeeId")
        @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto(" +
                "dna.assignmentId, d.employeeId, d.firstName, d.lastName, " +
                "n.employeeId, n.firstName, n.lastName) " +
                "FROM DoctorNurseAssignment dna " +
                "JOIN Employee d ON dna.doctorId = d.employeeId " +
                "JOIN Employee n ON dna.nurseId = n.employeeId " +
                "WHERE dna.doctorId = :employeeId OR dna.nurseId = :employeeId")
        List<DoctorNurseAssignmentDto> searchAssignmentsById(@Param("employeeId") Integer employeeId);


        @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto(" +
                "dna.assignmentId, d.employeeId, d.firstName, d.lastName, " +
                "n.employeeId, n.firstName, n.lastName) " +
                "FROM DoctorNurseAssignment dna " +
                "JOIN Employee d ON dna.doctorId = d.employeeId " +
                "JOIN Employee n ON dna.nurseId = n.employeeId " +
                "WHERE d.firstName LIKE %:name% OR d.lastName LIKE %:name% OR " +
                "n.firstName LIKE %:name% OR n.lastName LIKE %:name%")
        List<DoctorNurseAssignmentDto> searchAssignmentsByKeyword(@Param("name") String name);

//        @Query("SELECT dna.assignmentId, dna.doctorId, doc.FirstName AS DoctorFirstName, doc.LastName AS DoctorLastName, dna.nurseId, nur.FirstName AS NurseFirstName, nur.LastName AS NurseLastName FROM DoctorNurseAssignment dna JOIN Employees doc ON dna.doctorId = doc.EmployeeID JOIN Employees nur ON dna.nurseId = nur.EmployeeID where doc.firstName like %name% or doc.lastName like %name% or nur.firstName like %name% or nur.lastName like %name%")

        @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto(" +
                "dna.assignmentId, d.employeeId, d.firstName, d.lastName, " +
                "n.employeeId, n.firstName, n.lastName) " +
                "FROM DoctorNurseAssignment dna " +
                "JOIN Employee d ON dna.doctorId = d.employeeId " +
                "JOIN Employee n ON dna.nurseId = n.employeeId")
        List<DoctorNurseAssignmentDto> findAllAssignments();

        @Query("SELECT e FROM Employee e WHERE e.employeeId IN (SELECT dna.nurseId FROM DoctorNurseAssignment dna WHERE dna.doctorId = :doctorId)")
        List<Employee> findNursesByDoctorId(int doctorId);
}
