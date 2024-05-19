package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

