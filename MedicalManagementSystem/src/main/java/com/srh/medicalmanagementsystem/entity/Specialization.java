package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Specialization")
@Data
public class Specialization {

    @Id
    private Integer doctorId;

    @Column(name = "SpecializationName", nullable = false)
    private String specializationName;
}