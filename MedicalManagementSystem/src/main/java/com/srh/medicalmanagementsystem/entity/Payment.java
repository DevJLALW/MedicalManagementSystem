package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @Column(name = "Amount", nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "PaymentDate", nullable = false)
    private Date paymentDate;

    @Column(name = "PaymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "InsuranceDetails")
    private String insuranceDetails;
}

