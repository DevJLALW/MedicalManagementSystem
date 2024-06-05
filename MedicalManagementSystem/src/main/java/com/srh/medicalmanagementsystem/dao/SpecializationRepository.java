package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    @Query("Select s from Specialization s where s.specializationName like %:keyword%")
    List<Specialization> searchBySpecializationName(@Param("keyword") String keyword);
}