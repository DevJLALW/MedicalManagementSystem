package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "MedicalRecord")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedicalRecordID")
    private Integer medicalRecordId;

    @Column(name = "Diagnosis", nullable = false)
    @NotEmpty(message = "Diagnosis is mandatory")
    private String diagnosis;

    @Column(name = "DiagnosisNotes", nullable = false)
    @NotEmpty(message = "Diagnosis Notes is mandatory")
    private String diagnosisNotes;

    @Column(name = "MedicalResult", nullable = false)
    @NotEmpty(message = "Medical Result is mandatory")
    private String medicalResult;

    @Column(name = "MedicationsPrescribed", nullable = false)
    @NotEmpty(message = "Medications Prescribed is mandatory")
    private String medicationsPrescribed;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date date;

    @Column(name = "EmployeeId", nullable = false)
    @NotEmpty(message = "Employee Id is mandatory")
    private Integer employeeId;

    @Column(name = "PatientId", nullable = false)
    @NotEmpty(message = "Patient Id is mandatory")
    private Integer patientId;

    public int getRecordId() {
        return medicalRecordId;
    }

    public void setRecordId(int recordId) {
        this.medicalRecordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosisNotes() {
        return diagnosisNotes;
    }

    public void setDiagnosisNotes(String diagnosisNotes) {
        this.diagnosisNotes = diagnosisNotes;
    }

    public String getMedicalResult() {
        return medicalResult;
    }

    public void setMedicalResult(String medicalResult) {
        this.medicalResult = medicalResult;
    }

    public String getMedicationsPrescribed() {
        return medicationsPrescribed;
    }

    public void setMedicationsPrescribed(String medicationsPrescribed) {
        this.medicationsPrescribed = medicationsPrescribed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*@ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;*/

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    /*public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }


}
