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
    MedicinePrice DECIMAL(10, 2) NOT NULL,
    MedicineDosage INT NOT NULL,
    NumberOfDays INT NOT NULL,
    TotalBill DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (InventoryID) REFERENCES PharmacyInventory(StockID)
);


-- Insert records into Pharmacy Prescription table
INSERT INTO PharmacyPrescriptions (PatientID, DoctorID, InventoryID, MedicinePrice, MedicineDosage, NumberOfDays, TotalBill) VALUES
(1, 1, 1, 5.99, 2, 10, 119.80),
(2, 2, 2, 7.49, 1, 7, 52.43),
(3, 1, 3, 3.99, 2, 14, 111.72),
(4, 3, 4, 8.99, 1, 10, 89.90),
(5, 2, 5, 12.99, 2, 5, 129.90),
(6, 4, 6, 6.49, 3, 7, 136.29),
(7, 5, 7, 4.99, 1, 30, 149.70),
(8, 3, 8, 10.99, 1, 15, 164.85),
(9, 1, 9, 8.49, 2, 10, 169.80),
(10, 2, 10, 9.99, 1, 10, 99.90),
(11, 4, 11, 7.49, 2, 7, 104.86),
(12, 5, 12, 9.49, 3, 10, 284.70),
(13, 3, 13, 5.49, 1, 20, 109.80),
(14, 1, 14, 6.99, 2, 15, 209.70),
(15, 2, 15, 4.99, 1, 30, 149.70),
(16, 3, 16, 10.49, 2, 10, 209.80),
(17, 1, 17, 11.99, 1, 15, 179.85),
(18, 4, 18, 4.49, 3, 20, 269.40),
(19, 2, 19, 12.49, 1, 10, 124.90),
(20, 5, 20, 13.99, 2, 5, 139.90),
(21, 3, 21, 9.49, 2, 14, 265.72),
(22, 1, 22, 14.99, 1, 10, 149.90),
(23, 2, 23, 8.49, 2, 7, 118.86),
(24, 4, 24, 12.99, 1, 15, 194.85),
(25, 5, 25, 11.49, 2, 20, 459.60),
(26, 3, 26, 13.49, 1, 10, 134.90),
(27, 1, 27, 15.99, 2, 7, 223.86),
(28, 2, 28, 10.99, 1, 14, 153.86),
(29, 4, 29, 7.99, 3, 10, 239.70),
(30, 5, 30, 9.99, 2, 15, 299.70);

