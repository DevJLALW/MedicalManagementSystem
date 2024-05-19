package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Nurses")
@Data
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nurseId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "ContactNumber", nullable = false)
    private String contactNumber;

    @Column(name = "Email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "AssignedDoctorID")
    private Doctor assignedDoctor;
}

