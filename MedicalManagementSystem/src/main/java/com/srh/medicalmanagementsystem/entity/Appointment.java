package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "Appointment")
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

    @Column(name= "StartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDateTime;

    @Column(name= "EndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDateTime;

   /* @Column(name = "AppointmentStatus", nullable = false)
    @NotEmpty(message = "AppointmentStatus is mandatory")
    private boolean appointmentStatus;*/

    @Column(name = "AppointmentStatus", nullable = false)
    @NotEmpty(message = "AppointmentStatus is mandatory")
    private String appointmentStatus;




}
