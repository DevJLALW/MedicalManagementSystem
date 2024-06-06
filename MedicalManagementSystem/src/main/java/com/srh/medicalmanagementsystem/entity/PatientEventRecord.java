package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "PatientEventRecord")
@Data
public class PatientEventRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventId")
    private int eventId;

    @Column(name = "PatientId", nullable = false)
    private int patientId;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date eventDate;


    @Column(name = "Details", nullable = false)
    private String details;

    @Column(name = "AssignedDoctorId", nullable = false)
    private int assignedDoctorId;

    @Column(name = "AssignedNurseId", nullable = false)
    private int assignedNurseId;



/*
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAssignedDoctorId() {
        return assignedDoctorId;
    }

    public void setAssignedDoctorId(int assignedDoctorId) {
        this.assignedDoctorId = assignedDoctorId;
    }

    public int getAssignedNurseId() {
        return assignedNurseId;
    }

    public void setAssignedNurseId(int assignedNurseId) {
        this.assignedNurseId = assignedNurseId;
    }*/

    /*@Override
    public String toString() {
        return "PatientEventRecord{" +
                "eventId=" + eventId +
                ", patientId=" + patientId +
                ", eventDateTime=" + eventDateTime +
                ", details='" + details + '\'' +
                ", assignedDoctorId=" + assignedDoctorId +
                ", assignedNurseId=" + assignedNurseId +
                '}';
    }*/

    //@ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "EventType", referencedColumnName = "eventType")

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EventType")
    private EventDescriptions eventDescriptions;


}
