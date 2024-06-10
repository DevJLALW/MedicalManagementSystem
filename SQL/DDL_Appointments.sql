CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

DROP TABLE IF EXISTS Appointments;

CREATE TABLE Appointments (
    AppointmentID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    DoctorID INT,
    AppointmentDate INT,
    StartTime TIME,
    EndTime TIME,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (DoctorID) REFERENCES Employee(EmployeeID)
);