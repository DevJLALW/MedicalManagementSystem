package com.srh.medicalmanagementsystem.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "PharmacyInventory")
@Data

public class PharmacyInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryID")
    private int inventoryID;

    @Column(name = "MedicineName", nullable = false)
    private String medicineName;

    @Column(name = "MedicineDescription", columnDefinition = "TEXT")
    private String medicineDescription;

    @Column(name = "StockQuantity", nullable = false)
    private int stockQuantity;

    @Column(name = "ExpiryDate")
    private LocalDate expiryDate;

    @Column(name = "MedicinePrice", nullable = false)
    private BigDecimal medicinePrice;

    @Column(name = "EmployeeID", nullable = false)
    private int employeeID;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

    // Getters and setters

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(BigDecimal medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
