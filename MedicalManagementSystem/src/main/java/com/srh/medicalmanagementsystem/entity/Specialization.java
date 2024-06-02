package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Specialization")
@Data
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(name = "SpecializationName", nullable = false)
    private String Specialization;
}