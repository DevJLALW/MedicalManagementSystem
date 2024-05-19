package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {
}

