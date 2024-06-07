package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@Table(name = "Payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long paymentId;

    @Column(name = "PatientID")
    private Long patientId;

    @Column(name = "PaymentDate")
    private LocalDate paymentDate;

    @Column(name = "PaymentMethod")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "PaymentDetails")
    private String paymentDetails;

    @Column(name = "DoctorFees")
    private Double doctorFees;

    @Column(name = "RoomService")
    private Double roomService;

    @Column(name = "PharmacyCost")
    private Double pharmacyCost;

    @Column(name = "PaymentStatus")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    @PrePersist
    @PreUpdate
    private void updateTotalAmount() {
        this.totalAmount = getTotalAmount();
    }

    public Double getTotalAmount() {
        double total = doctorFees != null ? doctorFees : 0;
        total += roomService != null ? roomService : 0;
        total += pharmacyCost != null ? pharmacyCost : 0;
        return total;
    }

    public enum PaymentMethod {
        CASH, DEBIT_CREDIT, BANK_TRANSFER
    }

    public enum PaymentStatus {
        PAID, UNPAID
    }

    public Long getPaymentId() {
        return paymentId;
    }
}