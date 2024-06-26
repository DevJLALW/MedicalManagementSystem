package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.entity.AppointmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, p.patientId, p.firstName, p.lastName, d.employeeId, d.firstName, d.lastName, a.date, a.startTime, a.endTime) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId ")
  List<AppointmentDto> findAllAppointments();

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, p.patientId, p.firstName, p.lastName, d.employeeId, d.firstName, d.lastName, a.date, a.startTime, a.endTime) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId " +
          "WHERE a.appointmentId = :appointmentId")
  AppointmentDto findAppointmentById(@Param("appointmentId") Integer appointmentId);

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, p.patientId, p.firstName, p.lastName, d.employeeId, d.firstName, d.lastName, a.date, a.startTime, a.endTime) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId " +
          "WHERE a.patientId = :patientId AND a.date >= :date")
  List<AppointmentDto> findAllByPatientId(@Param("patientId") Integer patientId, @Param("date") Date date);

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, p.patientId, p.firstName, p.lastName, d.employeeId, d.firstName, d.lastName, a.date, a.startTime, a.endTime) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId " +
          "WHERE a.doctorId = :doctorId AND a.date >= :date")
  List<AppointmentDto> findAllByDoctorId(@Param("doctorId") Integer doctorId, @Param("date") Date date);

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, d.employeeId, p.patientId, a.date, a.startTime, a.endTime, " +
          "d.firstName, d.lastName, p.firstName, p.lastName) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId " +
          "WHERE d.employeeId = :doctorId AND a.date = :date")
  List<AppointmentDto> findByDoctorIdAndDate(@Param("doctorId") Integer doctorId, @Param("date") Date date);

  @Query("SELECT new com.srh.medicalmanagementsystem.entity.AppointmentDto(" +
          "a.appointmentId, d.employeeId, p.patientId, a.date, a.startTime, a.endTime, " +
          "d.firstName, d.lastName, p.firstName, p.lastName) " +
          "FROM Appointment a " +
          "JOIN Patient p ON a.patientId = p.patientId " +
          "JOIN Employee d ON a.doctorId = d.employeeId " +
          "WHERE a.patientId = :patientId AND a.date = :date")
  List<AppointmentDto> findByPatientIdAndDate(@Param("patientId") Integer patientId, @Param("date") Date date);

  @Query("SELECT a FROM Appointment a WHERE " +
          "a.doctorId = :doctorId AND " +
          "a.date = :date AND " +
          "a.startTime = :startTime")
  List<Appointment> getAppointmentsAssignedForDoctorSlot(@Param("doctorId") Integer doctorId, @Param("date") Date date, @Param("startTime") Time startTime);

}
