-- Use the database
USE HospitalDB;

-- Insert records into Patients table
INSERT INTO Patients (FirstName, LastName, DOB, Gender, ContactNumber, Email, Address) VALUES
('John', 'Doe', '1980-01-01', 'Male', '1234567890', 'john.doe@example.com', '123 Main St'),
('Jane', 'Smith', '1990-02-02', 'Female', '2345678901', 'jane.smith@example.com', '456 Oak St'),
('Jim', 'Brown', '1975-03-03', 'Male', '3456789012', 'jim.brown@example.com', '789 Pine St'),
('Jake', 'White', '1985-04-04', 'Male', '4567890123', 'jake.white@example.com', '101 Maple St'),
('Jill', 'Black', '1995-05-05', 'Female', '5678901234', 'jill.black@example.com', '202 Elm St'),
('Joan', 'Green', '1982-06-06', 'Female', '6789012345', 'joan.green@example.com', '303 Cedar St'),
('Jerry', 'Red', '1992-07-07', 'Male', '7890123456', 'jerry.red@example.com', '404 Birch St'),
('Jenny', 'Blue', '1987-08-08', 'Female', '8901234567', 'jenny.blue@example.com', '505 Walnut St'),
('Jeff', 'Yellow', '1978-09-09', 'Male', '9012345678', 'jeff.yellow@example.com', '606 Ash St'),
('Janet', 'Gray', '1988-10-10', 'Female', '0123456789', 'janet.gray@example.com', '707 Spruce St');

-- Insert records into Doctors table
INSERT INTO Doctors (FirstName, LastName, Specialization, ContactNumber, Email) VALUES
('Alice', 'Brown', 'Cardiology', '1234509876', 'alice.brown@example.com'),
('Bob', 'White', 'Neurology', '2345609876', 'bob.white@example.com'),
('Charlie', 'Black', 'Orthopedics', '3456709876', 'charlie.black@example.com'),
('David', 'Green', 'Pediatrics', '4567809876', 'david.green@example.com'),
('Eva', 'Red', 'Dermatology', '5678909876', 'eva.red@example.com'),
('Frank', 'Blue', 'Oncology', '6789009876', 'frank.blue@example.com'),
('Grace', 'Yellow', 'Gastroenterology', '7890109876', 'grace.yellow@example.com'),
('Henry', 'Gray', 'Pulmonology', '8901209876', 'henry.gray@example.com'),
('Ivy', 'Purple', 'Endocrinology', '9012309876', 'ivy.purple@example.com'),
('Jack', 'Orange', 'Urology', '0123409876', 'jack.orange@example.com');

-- Insert records into Nurses table
INSERT INTO Nurses (FirstName, LastName, ContactNumber, Email, AssignedDoctorID) VALUES
('Nancy', 'Smith', '2345678902', 'nancy.smith@example.com', 1),
('Olivia', 'Johnson', '3456789013', 'olivia.johnson@example.com', 2),
('Patricia', 'Brown', '4567890124', 'patricia.brown@example.com', 3),
('Quincy', 'Williams', '5678901235', 'quincy.williams@example.com', 4),
('Rachel', 'Jones', '6789012346', 'rachel.jones@example.com', 5),
('Samantha', 'Garcia', '7890123457', 'samantha.garcia@example.com', 6),
('Tina', 'Martinez', '8901234568', 'tina.martinez@example.com', 7),
('Uma', 'Davis', '9012345679', 'uma.davis@example.com', 8),
('Victoria', 'Rodriguez', '0123456780', 'victoria.rodriguez@example.com', 9),
('Wendy', 'Martinez', '1234567891', 'wendy.martinez@example.com', 10);

-- Insert records into Medical Assistants table
INSERT INTO MedicalAssistants (FirstName, LastName, ContactNumber, Email, AssignedDoctorID) VALUES
('Xander', 'Thompson', '2345678903', 'xander.thompson@example.com', 1),
('Yara', 'Anderson', '3456789014', 'yara.anderson@example.com', 2),
('Zack', 'Thomas', '4567890125', 'zack.thomas@example.com', 3),
('Aaron', 'Harris', '5678901236', 'aaron.harris@example.com', 4),
('Bella', 'Clark', '6789012347', 'bella.clark@example.com', 5),
('Cathy', 'Lewis', '7890123458', 'cathy.lewis@example.com', 6),
('Diana', 'Walker', '8901234569', 'diana.walker@example.com', 7),
('Evan', 'Hall', '9012345670', 'evan.hall@example.com', 8),
('Fiona', 'Allen', '0123456781', 'fiona.allen@example.com', 9),
('George', 'Young', '1234567892', 'george.young@example.com', 10);

-- Insert records into PatientAdmissions table
INSERT INTO PatientAdmissions (PatientID, RoomNumber, AdmitDate, DischargeDate) VALUES
(1, '101', '2023-01-01', '2023-01-05'),
(2, '102', '2023-02-01', '2023-02-07'),
(3, '103', '2023-03-01', '2023-03-04'),
(4, '104', '2023-04-01', '2023-04-10'),
(5, '105', '2023-05-01', '2023-05-06'),
(6, '106', '2023-06-01', '2023-06-08'),
(7, '107', '2023-07-01', '2023-07-05'),
(8, '108', '2023-08-01', '2023-08-09'),
(9, '109', '2023-09-01', '2023-09-11'),
(10, '110', '2023-10-01', '2023-10-03');

-- Insert records into Diagnoses table
INSERT INTO Diagnoses (PatientID, DoctorID, DiagnosisDate, Disease, Notes) VALUES
(1, 1, '2023-01-02', 'Flu', 'Rest and hydration'),
(2, 2, '2023-02-02', 'Migraine', 'Prescribed medication'),
(3, 3, '2023-03-02', 'Fracture', 'Cast applied'),
(4, 4, '2023-04-02', 'Asthma', 'Inhaler prescribed'),
(5, 5, '2023-05-02', 'Skin Rash', 'Topical cream prescribed'),
(6, 6, '2023-06-02', 'Cancer', 'Chemotherapy initiated'),
(7, 7, '2023-07-02', 'Ulcer', 'Dietary changes recommended'),
(8, 8, '2023-08-02', 'Bronchitis', 'Antibiotics prescribed'),
(9, 9, '2023-09-02', 'Diabetes', 'Insulin therapy started'),
(10, 10, '2023-10-02', 'Kidney Stones', 'Surgery scheduled');

-- Insert records into Medications table
INSERT INTO Medications (PatientID, DoctorID, MedicationName, Dosage, StartDate, EndDate) VALUES
(1, 1, 'Paracetamol', '500mg', '2023-01-02', '2023-01-05'),
(2, 2, 'Sumatriptan', '50mg', '2023-02-02', '2023-02-07'),
(3, 3, 'Ibuprofen', '200mg', '2023-03-02', '2023-03-04'),
(4, 4, 'Albuterol', '100mcg', '2023-04-02', '2023-04-10'),
(5, 5, 'Hydrocortisone', '1%', '2023-05-02', '2023-05-06'),
(6, 6, 'Cisplatin', '50mg', '2023-06-02', '2023-06-08'),
(7, 7, 'Omeprazole', '20mg', '2023-07-02', '2023-07-05'),
(8, 8, 'Azithromycin', '500mg', '2023-08-02', '2023-08-09'),
(9, 9, 'Metformin', '500mg', '2023-09-02', '2023-09-11'),
(10, 10, 'Tamsulosin', '0.4mg', '2023-10-02', '2023-10-03');

-- Insert records into Payments table
INSERT INTO Payments (PatientID, Amount, PaymentDate, PaymentMethod, InsuranceDetails) VALUES
(1, 100.00, '2023-01-03', 'Cash', NULL),
(2, 200.00, '2023-02-03', 'Credit Card', NULL),
(3, 300.00, '2023-03-03', 'E-Banking', NULL),
(4, 400.00, '2023-04-03', 'Insurance', 'Policy ABC123'),
(5, 500.00, '2023-05-03', 'Cash', NULL),
(6, 600.00, '2023-06-03', 'Credit Card', NULL),
(7, 700.00, '2023-07-03', 'E-Banking', NULL),
(8, 800.00, '2023-08-03', 'Insurance', 'Policy XYZ456'),
(9, 900.00, '2023-09-03', 'Cash', NULL),
(10, 1000.00, '2023-10-03', 'Credit Card', NULL);

-- Insert records into Calls table
INSERT INTO Calls (PatientID, CallDate, Purpose, Resolved) VALUES
(1, '2023-01-04 10:00:00', 'Follow-up', TRUE),
(2, '2023-02-04 11:00:00', 'Prescription renewal', FALSE),
(3, '2023-03-04 12:00:00', 'Appointment scheduling', TRUE),
(4, '2023-04-04 13:00:00', 'Lab results', TRUE),
(5, '2023-05-04 14:00:00', 'Medical advice', FALSE),
(6, '2023-06-04 15:00:00', 'Billing inquiry', TRUE),
(7, '2023-07-04 16:00:00', 'Insurance query', FALSE),
(8, '2023-08-04 17:00:00', 'Feedback', TRUE),
(9, '2023-09-04 18:00:00', 'Complaint', TRUE),
(10, '2023-10-04 19:00:00', 'General inquiry', FALSE);

-- Insert records into Assignments table
INSERT INTO Assignments (PatientID, NurseID, AssistantID, StartDate, EndDate) VALUES
(1, 1, 1, '2023-01-01', '2023-01-05'),
(2, 2, 2, '2023-02-01', '2023-02-07'),
(3, 3, 3, '2023-03-01', '2023-03-04'),
(4, 4, 4, '2023-04-01', '2023-04-10'),
(5, 5, 5, '2023-05-01', '2023-05-06'),
(6, 6, 6, '2023-06-01', '2023-06-08'),
(7, 7, 7, '2023-07-01', '2023-07-05'),
(8, 8, 8, '2023-08-01', '2023-08-09'),
(9, 9, 9, '2023-09-01', '2023-09-11'),
(10, 10, 10, '2023-10-01', '2023-10-03');
