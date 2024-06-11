package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.patientId = :patientId")
    List<Payment> findPaymentsByPatientId(@Param("patientId") Long patientId);

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.status = 0 WHERE p.patientId = :patientId")
    int updateStatusToInactiveInPaymentByPatientId(@Param("patientId") Long patientId);
}
