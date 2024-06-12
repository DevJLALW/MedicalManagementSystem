-- Create database
CREATE DATABASE IF NOT EXISTS HospitalDB;
USE HospitalDB;

-- Create Table
DROP TABLE IF EXISTS Payment;
CREATE TABLE Payment (
  PaymentID bigint NOT NULL AUTO_INCREMENT,
  PatientID bigint NOT NULL,
  PaymentDate date NOT NULL,
  PaymentMethod varchar(255) NOT NULL,
  PaymentDetails varchar(255) DEFAULT NULL,
  DoctorFees double DEFAULT NULL,
  PaymentStatus varchar(255) NOT NULL,
  TotalAmount float NOT NULL,
  Status tinyint(1) DEFAULT NULL,
  PRIMARY KEY(PaymentID)
);