package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PatientAdmissions")
@Data
public class PatientAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admissionId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @Column(name = "RoomNumber", nullable = false)
    private String roomNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "AdmitDate", nullable = false)
    private Date admitDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DischargeDate")
    private Date dischargeDate;
}

