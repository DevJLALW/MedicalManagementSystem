package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DoctorNurseAssignment")
@Data
public class DoctorNurseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssignmentID")
    private Integer assignmentId;

    @Column(name = "DoctorID", nullable = false)
    private Integer doctorId;

    @Column(name = "NurseID", nullable = false)
    private Integer nurseId;
}