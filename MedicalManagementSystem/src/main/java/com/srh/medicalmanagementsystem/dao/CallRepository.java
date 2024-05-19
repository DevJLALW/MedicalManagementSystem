package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Integer> {
}

