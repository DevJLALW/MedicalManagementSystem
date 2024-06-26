package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "Appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private Integer appointmentId;

    @Column(name = "PatientID", nullable = false)
    @NotNull(message = "PatientID is mandatory")
    private Integer patientId;

    @Column(name = "DoctorID", nullable = false)
    @NotNull(message = "DoctorID is mandatory")
    private Integer doctorId;

    @Column(name= "AppointmentDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Column(name= "StartTime")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Time startTime;

    @Column(name= "EndTime")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Time endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId", referencedColumnName = "PatientID", insertable = false, updatable = false)
    private Patient patient;



}
