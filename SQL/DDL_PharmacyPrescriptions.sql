-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Create Table
DROP TABLE IF EXISTS PharmacyPrescriptions;
CREATE TABLE PharmacyPrescriptions (
    PrescriptionID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT NOT NULL,
    DoctorID INT NOT NULL,
    InventoryID INT NOT NULL,
    MedicinePrice DECIMAL(10, 2),
    MedicineDosage INT NOT NULL,
    NumberOfDays INT NOT NULL,
    PharmacyBill DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (InventoryID) REFERENCES PharmacyInventory(InventoryID)
);
