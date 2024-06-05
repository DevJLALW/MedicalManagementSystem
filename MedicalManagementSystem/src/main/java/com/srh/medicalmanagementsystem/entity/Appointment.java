package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "Appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;

    @Column(name = "PatientID", nullable = false)
    @NotEmpty(message = "PatientID is mandatory")
    private int patientId;

    @Column(name = "DoctorID", nullable = false)
    @NotEmpty(message = "DoctorID is mandatory")
    private int doctorId;

    @Column(name= "StartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(name= "EndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @Column(name = "AppointmentStatus", nullable = false)
    @NotEmpty(message = "AppointmentStatus is mandatory")
    private boolean appointmentStatus;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @NotEmpty(message = "PatientID is mandatory")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotEmpty(message = "PatientID is mandatory") int patientId) {
        this.patientId = patientId;
    }

    @NotEmpty(message = "DoctorID is mandatory")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(@NotEmpty(message = "DoctorID is mandatory") int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @NotEmpty(message = "AppointmentStatus is mandatory")
    public boolean isAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(@NotEmpty(message = "AppointmentStatus is mandatory") boolean appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", appointmentStatus=" + appointmentStatus +
                '}';
    }

    public Appointment(int appointmentId, int patientId, int doctorId, Date startDateTime, Date endDateTime, boolean appointmentStatus) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.appointmentStatus = appointmentStatus;
    }
}
