package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class PharmacyTransactions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;
    @Column
    private int itemId;
    @Column
    private int patientId;
    @Column
    private int employeeId;
    @Column
    private int count;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
