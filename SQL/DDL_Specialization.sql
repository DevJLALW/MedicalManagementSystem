CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

DROP TABLE IF EXISTS Specialization;

CREATE TABLE Specialization (
    DoctorID INT PRIMARY KEY,
    SpecializationName VARCHAR(255),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID)
);