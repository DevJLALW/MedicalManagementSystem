-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Create Table
DROP TABLE IF EXISTS Payment;
CREATE TABLE Payment (
    PaymentID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PatientID BIGINT NOT NULL,
    PaymentDate DATE NOT NULL,
    PaymentMethod VARCHAR(255) NOT NULL,
    PaymentDetails VARCHAR(255),
    DoctorFees DOUBLE,
    RoomService DOUBLE,
    PharmacyCost DOUBLE,
    PaymentStatus VARCHAR(255) NOT NULL,
    TotalAmount FLOAT NOT NULL
);