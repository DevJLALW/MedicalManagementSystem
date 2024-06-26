package com.srh.medicalmanagementsystem.entity;

import com.srh.medicalmanagementsystem.validation.PastOrPresentDate;
import com.srh.medicalmanagementsystem.validation.ValidEmail;
import jakarta.persistence.*;
import lombok.Data;


import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private Integer patientId;

    @Column(name = "FirstName", nullable = false)
    @NotEmpty(message = "First name is mandatory")
    private String firstName;

    @Column(name = "LastName", nullable = false)
    @NotEmpty(message = "Last name is mandatory")
    private String lastName;

    @Column(name = "DOB", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date of birth is mandatory")
    @PastOrPresentDate(message = "Date of birth must be in the past or present")
    private Date dob;

    @Column(name = "Gender", nullable = false)
    @NotNull(message = "Gender is mandatory")
    private String gender;

    @Column(name = "ContactNumber", nullable = false)
    @NotEmpty(message = "Contact number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Contact number must contain only digits and optionally start with a '+'")
    private String contactNumber;

    @Column(name = "Email", nullable = true)
    @ValidEmail(message = "Email should be valid")
    private String email;

    @Column(name = "Address", nullable = true)
    private String address;

    @Column(name = "MedicalHistory", nullable = true)
    private String medicalHistory;

    @Column(name = "InsuranceID", nullable = true)
    private String insuranceID;


    @ManyToOne
    @JoinColumn(name = "DoctorID", referencedColumnName = "EmployeeID")
    private Employee doctor;

    @ManyToOne
    @JoinColumn(name = "NurseID", referencedColumnName = "EmployeeID")
    private Employee nurse;

    @Column(name = "RoomID", nullable = true)
    private Integer roomID;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "EmployeeID")
    private Employee user;

    @Column(name = "Password", nullable = true)
    private String password;

    @Column(name = "Status", nullable = false)
    private int status;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientEventRecord> eventRecords;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PharmacyPrescription> pharmacyPrescription;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentDto;

    @OneToMany(mappedBy = "patient")
    private List<Room> rooms;

    @PrePersist
    protected void onCreate() {
        this.timestamp = Timestamp.from(Instant.now());
    }


    @Formula("(YEAR(CURDATE()) - YEAR(DOB)) - (DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(DOB, '%m%d'))")
    private int age;
}

