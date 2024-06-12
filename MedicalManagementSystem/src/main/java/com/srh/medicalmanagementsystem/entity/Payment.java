package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "PaymentStatus")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    @Column(name = "Status", nullable = false)
    private int status;

    public enum PaymentMethod {
        CASH, DEBIT_CREDIT, BANK_TRANSFER, INSURANCE
    }

    public enum PaymentStatus {
        PAID, UNPAID
    }

    @ManyToOne
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID", insertable = false, updatable = false)
    private Patient patient;

}










