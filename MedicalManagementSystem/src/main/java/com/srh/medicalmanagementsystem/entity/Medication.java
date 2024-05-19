package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Medications")
@Data
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicationId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;

    @Column(name = "MedicationName", nullable = false)
    private String medicationName;

    @Column(name = "Dosage", nullable = false)
    private String dosage;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate")
    private Date endDate;
}

