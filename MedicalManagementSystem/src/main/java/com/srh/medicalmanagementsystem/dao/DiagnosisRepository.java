package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
}

