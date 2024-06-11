-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Create Table
DROP TABLE IF EXISTS Room;
CREATE TABLE Room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    PatientID BIGINT,
    RoomNumber VARCHAR(255) NOT NULL,
    RoomType VARCHAR(255) NOT NULL,
    RoomAdmissionStartDate DATE,
    RoomAdmissionEndDate DATE,
    NumberOfDays BIGINT,
    RoomBill  DECIMAL(10, 2) NOT NULL
);
