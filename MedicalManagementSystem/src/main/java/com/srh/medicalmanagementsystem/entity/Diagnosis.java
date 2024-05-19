package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Diagnoses")
@Data
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diagnosisId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;

    @Temporal(TemporalType.DATE)
    @Column(name = "DiagnosisDate", nullable = false)
    private Date diagnosisDate;

    @Column(name = "Disease", nullable = false)
    private String disease;

    @Column(name = "Notes")
    private String notes;
}

