package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPatientId(String patientId);
}