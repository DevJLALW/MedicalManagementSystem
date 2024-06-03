package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorNurseAssignmentRepository extends JpaRepository <DoctorNurseAssignment, Integer> {
//    try {
        @Query("Select dna from DoctorNurseAssignment dna where dna.doctorId  = :employeeId or dna.nurseId  = :employeeId")
        List<DoctorNurseAssignment> searchAssignments(@Param("employeeId") Integer employeeId);
//    } catch {
//
//    }
}
