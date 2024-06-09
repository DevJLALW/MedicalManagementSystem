CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

DROP TABLE IF EXISTS DoctorNurseAssignment;

CREATE TABLE DoctorNurseAssignment (
    AssignmentID INT AUTO_INCREMENT PRIMARY KEY,
    DoctorID INT,
    NurseID INT,
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (NurseID) REFERENCES Employee(EmployeeID)
);
