package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto;
import com.srh.medicalmanagementsystem.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto(" +
            "s.doctorId, d.firstName, d.lastName, s.specializationName)" +
            "FROM Specialization s " +
            "JOIN Employee d ON s.doctorId = d.employeeId " +
            "where s.specializationName like %:keyword% or d.firstName like %:keyword% or d.lastName like %:keyword%")
    List<DoctorSpecializationDto> searchDoctorSpecializationByKeyword(@Param("keyword") String keyword);

    @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto(" +
            "s.doctorId, d.firstName, d.lastName, s.specializationName)" +
            "FROM Specialization s " +
            "JOIN Employee d ON s.doctorId = d.employeeId " +
            "WHERE s.doctorId = :employeeId")
    List<DoctorSpecializationDto> searchByDoctorId(@Param("employeeId") Integer employeeId);

    @Query("SELECT new com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto(" +
            "s.doctorId, d.firstName, d.lastName, s.specializationName)" +
            "FROM Specialization s " +
            "JOIN Employee d ON s.doctorId = d.employeeId ")
    List<DoctorSpecializationDto> findAllDoctorsSpecializations();
}