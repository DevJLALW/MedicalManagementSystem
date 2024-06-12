-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Create Table
CREATE TABLE PharmacyInventory(
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,
    MedicineName VARCHAR(255) NOT NULL,
    MedicineDescription TEXT,
    StockQuantity INT NOT NULL,
    ExpiryDate DATE,
    MedicinePrice DECIMAL(10, 2) NOT NULL,
    EmployeeID INT NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    LastUpdated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

