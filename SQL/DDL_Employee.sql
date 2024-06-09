CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

DROP TABLE IF EXISTS Employee;

CREATE TABLE Employee (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Password VARBINARY(60) null,
    ContactNumber VARCHAR(255),
    Role VARCHAR(50),
    Status INT
);