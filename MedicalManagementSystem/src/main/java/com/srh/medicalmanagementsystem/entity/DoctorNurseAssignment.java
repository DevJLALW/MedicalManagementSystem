package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DoctorNurseAssignment")
@Data
public class DoctorNurseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;

    @Column(name = "DoctorID", nullable = false)
    private int doctorId;

    @Column(name = "NurseID", nullable = false)
    private int nurseId;
}