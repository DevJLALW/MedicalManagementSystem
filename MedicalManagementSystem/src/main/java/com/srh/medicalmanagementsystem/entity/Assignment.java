package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Assignments")
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "NurseID")
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "AssistantID")
    private MedicalAssistant assistant;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate")
    private Date endDate;
}

