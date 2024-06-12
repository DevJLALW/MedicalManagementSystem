package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import com.srh.medicalmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorNurseAssignmentRepository extends JpaRepository <DoctorNurseAssignment, Integer> {

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
