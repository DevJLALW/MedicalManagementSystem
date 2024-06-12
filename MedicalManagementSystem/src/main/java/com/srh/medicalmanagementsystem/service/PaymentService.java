package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    void deletePayment(Long id);
    List<Payment> findPaymentsByPatientId(Long patientId);
}
