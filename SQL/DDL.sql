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



-- Create Patients table
CREATE TABLE Patients (
    PatientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    DOB DATE,
    Gender VARCHAR(50),
    ContactNumber VARCHAR(255),
    Email VARCHAR(255),
    Address VARCHAR(255)
);

-- Create Doctors table
CREATE TABLE Doctors (
    DoctorID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Specialization VARCHAR(255),
    ContactNumber VARCHAR(255),
    Email VARCHAR(255)
);

-- Create Nurses table
CREATE TABLE Nurses (
    NurseID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(255),
    Email VARCHAR(255),
    AssignedDoctorID INT,
    FOREIGN KEY (AssignedDoctorID) REFERENCES Doctors(DoctorID)
);

-- Create Medical Assistants table
CREATE TABLE MedicalAssistants (
    AssistantID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(255),
    Email VARCHAR(255),
    AssignedDoctorID INT,
    FOREIGN KEY (AssignedDoctorID) REFERENCES Doctors(DoctorID)
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
    FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
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
    FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
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
    FOREIGN KEY (NurseID) REFERENCES Nurses(NurseID),
    FOREIGN KEY (AssistantID) REFERENCES MedicalAssistants(AssistantID)
);

ALTER TABLE Patient
ADD COLUMN MedicalHistory TEXT,
ADD COLUMN InsuranceID INT,
ADD COLUMN DoctorID INT,
ADD COLUMN NurseID INT,
ADD COLUMN RoomID INT,
ADD COLUMN RecordID INT,
ADD COLUMN Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN EmployeeID INT,
ADD COLUMN Password VARBINARY(60) null;

