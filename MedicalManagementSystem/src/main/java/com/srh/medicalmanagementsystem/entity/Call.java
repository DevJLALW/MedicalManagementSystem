package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Calls")
@Data
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer callId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CallDate", nullable = false)
    private Date callDate;

    @Column(name = "Purpose", nullable = false)
    private String purpose;

    @Column(name = "Resolved", nullable = false)
    private Boolean resolved;
}

