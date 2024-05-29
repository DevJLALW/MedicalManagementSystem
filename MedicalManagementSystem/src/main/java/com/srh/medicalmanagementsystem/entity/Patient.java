package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "Patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private Integer patientId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "DOB", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dob;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "ContactNumber", nullable = false)
    private String contactNumber;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "MedicalHistory", nullable = false)
    private String medicalHistory;

    @Column(name = "InsuranceID", nullable = false)
    private String insuranceID;

    @Column(name = "DoctorID", nullable = false)
    private Integer doctorID;

    @Column(name = "NurseID", nullable = false)
    private Integer nurseID;

    @Column(name = "RoomID", nullable = false)
    private Integer roomID;

    @Column(name = "RecordID", nullable = false)
    private Integer recordID;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeID;

    @Column(name = "Password", nullable = true)
    private String password;

    @PrePersist
    protected void onCreate() {
        this.timestamp = Timestamp.from(Instant.now());
    }
}

