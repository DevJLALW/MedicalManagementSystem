package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PharmacyPrescriptions")
public class PharmacyPrescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrescriptionID")
    private int prescriptionID;

    @Column(name = "PatientID", nullable = false)
    private int patientID;

    @Column(name = "DoctorID", nullable = false)
    private int doctorID;

    @ManyToOne
    @JoinColumn(name = "InventoryID", nullable = false)
    private PharmacyInventory inventory;

    @Column(name = "MedicinePrice", nullable = false)
    private BigDecimal medicinePrice;

    @Column(name = "MedicineDosage", nullable = false)
    private int medicineDosage;

    @Column(name = "NumberOfDays", nullable = false)
    private int numberOfDays;

    @Column(name = "PharmacyBill", nullable = false)
    private BigDecimal pharmacyBill;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId", referencedColumnName = "PatientID", insertable = false, updatable = false)
    private Patient patient;

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public PharmacyInventory getInventory() {
        return inventory;
    }

    public void setInventory(PharmacyInventory inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(BigDecimal medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public int getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(int medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public BigDecimal getPharmacyBill() {
        return pharmacyBill;
    }

    public void setPharmacyBill(BigDecimal pharmacyBill) {
        this.pharmacyBill = pharmacyBill;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalBill() {
        return pharmacyBill;
    }
}
