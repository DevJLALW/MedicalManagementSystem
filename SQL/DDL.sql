-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS Assignments;
DROP TABLE IF EXISTS Calls;
DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Medications;
DROP TABLE IF EXISTS Diagnoses;
DROP TABLE IF EXISTS PatientAdmissions;
DROP TABLE IF EXISTS MedicalAssistants;
DROP TABLE IF EXISTS Nurses;
DROP TABLE IF EXISTS Doctors;
DROP TABLE IF EXISTS Patients;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS DoctorNurseAssignment;
DROP TABLE IF EXISTS Specialization;


-- Create Employee table
CREATE TABLE Employee (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Password VARBINARY(60) null,
    ContactNumber VARCHAR(255),
    Role VARCHAR(50),
    Status TINYINT(1)
);

-- Create Patients table
CREATE TABLE Patients (
    PatientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    DOB DATE,
    Gender VARCHAR(50),
    ContactNumber VARCHAR(255),
    Email VARCHAR(255),
    Address VARCHAR(255),
    MedicalHistory TEXT,
    InsuranceID INT,
    DoctorID INT,
    NurseID INT,
    RoomID INT,
    RecordID INT,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    EmployeeID INT,
    Password VARBINARY(60) null,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (NurseID) REFERENCES Employee(EmployeeID)
);

-- Create Doctor Nurse Assignment
CREATE TABLE DoctorNurseAssignment (
    AssignmentID INT AUTO_INCREMENT PRIMARY KEY,
    DoctorID INT,
    NurseID INT,
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (NurseID) REFERENCES Employee(EmployeeID)
);

-- Create Specialization table
CREATE TABLE Specialization (
    DoctorID INT PRIMARY KEY,
    SpecializationName VARCHAR(255),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID)
);

-- Create Patient Admissions table
CREATE TABLE PatientAdmissions (
    AdmissionID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    RoomNumber VARCHAR(255),
    AdmitDate DATE,
    DischargeDate DATE,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

-- Create Diagnoses table
CREATE TABLE Diagnoses (
    DiagnosisID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    DoctorID INT,
    DiagnosisDate DATE,
    Disease VARCHAR(255),
    Notes TEXT,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID)
);

-- Create Medications table
CREATE TABLE Medications (
    MedicationID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    DoctorID INT,
    MedicationName VARCHAR(255),
    Dosage VARCHAR(255),
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID)
);

-- Create Payments table
CREATE TABLE Payments (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    Amount DECIMAL(10, 2),
    PaymentDate DATE,
    PaymentMethod VARCHAR(255),
    InsuranceDetails VARCHAR(255) NULL,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

-- Create Calls table
CREATE TABLE Calls (
    CallID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    CallDate DATETIME,
    Purpose TEXT,
    Resolved BOOLEAN,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

-- Create Assignments table
CREATE TABLE Assignments (
    AssignmentID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    NurseID INT NULL,
    AssistantID INT NULL,
    StartDate DATE,
    EndDate DATE NULL,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (NurseID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (AssistantID) REFERENCES Employee(EmployeeID)
);

