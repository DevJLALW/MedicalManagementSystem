-- Use the database
USE HospitalDB;

-- Insert records into Patients table
INSERT INTO Patients (FirstName, LastName, DOB, Gender, ContactNumber, Email, Address, Status) VALUES
('John', 'Doe', '1980-01-01', 'Male', '1234567890', 'john.doe@example.com', '123 Main St', 1),
('Jane', 'Smith', '1990-02-02', 'Female', '2345678901', 'jane.smith@example.com', '456 Oak St', 1),
('Jim', 'Brown', '1975-03-03', 'Male', '3456789012', 'jim.brown@example.com', '789 Pine St', 1),
('Jake', 'White', '1985-04-04', 'Male', '4567890123', 'jake.white@example.com', '101 Maple St', 1),
('Jill', 'Black', '1995-05-05', 'Female', '5678901234', 'jill.black@example.com', '202 Elm St', 1),
('Joan', 'Green', '1982-06-06', 'Female', '6789012345', 'joan.green@example.com', '303 Cedar St', 1),
('Jerry', 'Red', '1992-07-07', 'Male', '7890123456', 'jerry.red@example.com', '404 Birch St', 1),
('Jenny', 'Blue', '1987-08-08', 'Female', '8901234567', 'jenny.blue@example.com', '505 Walnut St', 1),
('Jeff', 'Yellow', '1978-09-09', 'Male', '9012345678', 'jeff.yellow@example.com', '606 Ash St', 1),
('Janet', 'Gray', '1988-10-10', 'Female', '0123456789', 'janet.gray@example.com', '707 Spruce St', 1);

-- Insert records into Employee table
INSERT INTO Employee (FirstName, LastName, ContactNumber, Email, Role, Status) VALUES
('Alice', 'Brown', '1234509876', 'alice.brown@example.com', 'Doctor', 1),
('Bob', 'White', '2345609876', 'bob.white@example.com', 'Doctor', 1),
('Charlie', 'Black', '3456709876', 'charlie.black@example.com', 'Doctor', 1),
('David', 'Green', '4567809876', 'david.green@example.com', 'Doctor', 1),
('Eva', 'Red', '5678909876', 'eva.red@example.com', 'Doctor', 1),
('Frank', 'Blue', '6789009876', 'frank.blue@example.com', 'Doctor', 1),
('Grace', 'Yellow', '7890109876', 'grace.yellow@example.com', 'Doctor', 1),
('Henry', 'Gray', '8901209876', 'henry.gray@example.com', 'Doctor', 1),
('Ivy', 'Purple', '9012309876', 'ivy.purple@example.com', 'Doctor', 1),
('Jack', 'Orange', '0123409876', 'jack.orange@example.com', 'Doctor', 1),
('Nancy', 'Smith', '2345678902', 'nancy.smith@example.com', 'Nurse', 1),
('Olivia', 'Johnson', '3456789013', 'olivia.johnson@example.com', 'Nurse', 1),
('Patricia', 'Brown', '4567890124', 'patricia.brown@example.com', 'Nurse', 1),
('Quincy', 'Williams', '5678901235', 'quincy.williams@example.com', 'Nurse', 1),
('Rachel', 'Jones', '6789012346', 'rachel.jones@example.com', 'Nurse', 1),
('Samantha', 'Garcia', '7890123457', 'samantha.garcia@example.com', 'Nurse', 1),
('Tina', 'Martinez', '8901234568', 'tina.martinez@example.com', 'Nurse', 1),
('Uma', 'Davis', '9012345679', 'uma.davis@example.com', 'Nurse', 1),
('Victoria', 'Rodriguez', '0123456780', 'victoria.rodriguez@example.com', 'Nurse', 1),
('Wendy', 'Martinez', '1234567891', 'wendy.martinez@example.com', 'Nurse', 1),
('Xander', 'Thompson', '2345678903', 'xander.thompson@example.com', 'Medical Assistant', 1),
('Yara', 'Anderson', '3456789014', 'yara.anderson@example.com', 'Medical Assistant', 1),
('Zack', 'Thomas', '4567890125', 'zack.thomas@example.com', 'Medical Assistant', 1),
('Aaron', 'Harris', '5678901236', 'aaron.harris@example.com', 'Medical Assistant', 1),
('Bella', 'Clark', '6789012347', 'bella.clark@example.com', 'Medical Assistant', 1),
('Cathy', 'Lewis', '7890123458', 'cathy.lewis@example.com', 'Medical Assistant', 1),
('Diana', 'Walker', '8901234569', 'diana.walker@example.com', 'Medical Assistant', 1),
('Evan', 'Hall', '9012345670', 'evan.hall@example.com', 'Medical Assistant', 1),
('Fiona', 'Allen', '0123456781', 'fiona.allen@example.com', 'Medical Assistant', 1),
('George', 'Young', '1234567892', 'george.young@example.com', 'Medical Assistant', 1);

-- Insert records into Specialization table
INSERT INTO Specialization (DoctorID, SpecializationName) VALUES
(1, 'Cardiology'),
(2, 'Neurology'),
(3, 'Orthopedics'),
(4, 'Pediatrics'),
(5, 'Dermatology'),
(6, 'Oncology'),
(7, 'Gastroenterology'),
(8, 'Pulmonology'),
(9, 'Endocrinology'),
(10, 'Urology');

-- Insert records into DoctorNurseAssignment table
INSERT INTO DoctorNurseAssignment (DoctorID, NurseID) VALUES
(1, 11),
(2, 12),
(3, 13),
(4, 14),
(5, 15),
(6, 16),
(7, 17),
(8, 18),
(9, 19),
(10, 20),
(1, 21),
(2, 22),
(3, 23),
(4, 24),
(5, 25),
(6, 26),
(7, 27),
(8, 28),
(9, 29),
(10, 30);

-- Insert records into PatientAdmissions table
INSERT INTO PatientAdmissions (PatientID, RoomNumber, AdmitDate, DischargeDate) VALUES
(100101, '101', '2023-01-01', '2023-01-05'),
(100102, '102', '2023-02-01', '2023-02-07'),
(100103, '103', '2023-03-01', '2023-03-04'),
(100104, '104', '2023-04-01', '2023-04-10'),
(100105, '105', '2023-05-01', '2023-05-06'),
(100106, '106', '2023-06-01', '2023-06-08'),
(100107, '107', '2023-07-01', '2023-07-05'),
(100108, '108', '2023-08-01', '2023-08-09'),
(100109, '109', '2023-09-01', '2023-09-11'),
(100110, '110', '2023-10-01', '2023-10-03');

-- Insert records into Diagnoses table
INSERT INTO Diagnosis (PatientID, DoctorID, DiagnosisDate, Disease, Notes) VALUES
(100101, 1, '2023-01-02', 'Flu', 'Rest and hydration'),
(100102, 2, '2023-02-02', 'Migraine', 'Prescribed medication'),
(100103, 3, '2023-03-02', 'Fracture', 'Cast applied'),
(100104, 4, '2023-04-02', 'Asthma', 'Inhaler prescribed'),
(100105, 5, '2023-05-02', 'Skin Rash', 'Topical cream prescribed'),
(100106, 6, '2023-06-02', 'Cancer', 'Chemotherapy initiated'),
(100107, 7, '2023-07-02', 'Ulcer', 'Dietary changes recommended'),
(100108, 8, '2023-08-02', 'Bronchitis', 'Antibiotics prescribed'),
(100109, 9, '2023-09-02', 'Diabetes', 'Insulin therapy started'),
(100110, 10, '2023-10-02', 'Kidney Stones', 'Surgery scheduled');

-- Insert records into Medications table
INSERT INTO Medications (PatientID, DoctorID, MedicationName, Dosage, StartDate, EndDate) VALUES
(100101, 1, 'Paracetamol', '500mg', '2023-01-02', '2023-01-05'),
(100102, 2, 'Sumatriptan', '50mg', '2023-02-02', '2023-02-07'),
(100103, 3, 'Ibuprofen', '200mg', '2023-03-02', '2023-03-04'),
(100104, 4, 'Albuterol', '100mcg', '2023-04-02', '2023-04-10'),
(100105, 5, 'Hydrocortisone', '1%', '2023-05-02', '2023-05-06'),
(100106, 6, 'Cisplatin', '50mg', '2023-06-02', '2023-06-08'),
(100107, 7, 'Omeprazole', '20mg', '2023-07-02', '2023-07-05'),
(100108, 8, 'Azithromycin', '500mg', '2023-08-02', '2023-08-09'),
(100109, 9, 'Metformin', '500mg', '2023-09-02', '2023-09-11'),
(100110, 10, 'Tamsulosin', '0.4mg', '2023-10-02', '2023-10-03');

-- Insert records into Payments table
INSERT INTO Payments (PatientID, Amount, PaymentDate, PaymentMethod, InsuranceDetails) VALUES
(100101, 100.00, '2023-01-03', 'Cash', NULL),
(100102, 200.00, '2023-02-03', 'Credit Card', NULL),
(100103, 300.00, '2023-03-03', 'E-Banking', NULL),
(100104, 400.00, '2023-04-03', 'Insurance', 'Policy ABC123'),
(100105, 500.00, '2023-05-03', 'Cash', NULL),
(100106, 600.00, '2023-06-03', 'Credit Card', NULL),
(100107, 700.00, '2023-07-03', 'E-Banking', NULL),
(100108, 800.00, '2023-08-03', 'Insurance', 'Policy XYZ456'),
(100109, 900.00, '2023-09-03', 'Cash', NULL),
(100110, 1000.00, '2023-10-03', 'Credit Card', NULL);

-- Insert records into Calls table
INSERT INTO Calls (PatientID, CallDate, Purpose, Resolved) VALUES
(100101, '2023-01-04 10:00:00', 'Follow-up', TRUE),
(100102, '2023-02-04 11:00:00', 'Prescription renewal', FALSE),
(100103, '2023-03-04 12:00:00', 'Appointment scheduling', TRUE),
(100104, '2023-04-04 13:00:00', 'Lab results', TRUE),
(100105, '2023-05-04 14:00:00', 'Medical advice', FALSE),
(100106, '2023-06-04 15:00:00', 'Billing inquiry', TRUE),
(100107, '2023-07-04 16:00:00', 'Insurance query', FALSE),
(100108, '2023-08-04 17:00:00', 'Feedback', TRUE),
(100109, '2023-09-04 18:00:00', 'Complaint', TRUE),
(100110, '2023-10-04 19:00:00', 'General inquiry', FALSE);

-- Insert records into Assignments table
INSERT INTO Assignments (PatientID, NurseID, AssistantID, StartDate, EndDate) VALUES
(100101, 11, 21, '2023-01-01', '2023-01-05'),
(100102, 12, 22, '2023-02-01', '2023-02-07'),
(100103, 13, 23, '2023-03-01', '2023-03-04'),
(100104, 14, 24, '2023-04-01', '2023-04-10'),
(100105, 15, 25, '2023-05-01', '2023-05-06'),
(100106, 16, 26, '2023-06-01', '2023-06-08'),
(100107, 17, 27, '2023-07-01', '2023-07-05'),
(100108, 18, 28, '2023-08-01', '2023-08-09'),
(100109, 19, 29, '2023-09-01', '2023-09-11'),
(100110, 20, 30, '2023-10-01', '2023-10-03');