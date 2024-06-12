package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.entity.AppointmentDto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> getAllAppointments();

    AppointmentDto getAppointmentById(int appointmentId);

    boolean isSlotAvailable(int doctorId, Date date, Time startTime);

    Appointment saveAppointment(AppointmentDto appointment);

    Appointment updateAppointment(int appointmentId, AppointmentDto updatedAppointment);

    void deleteAppointment(int appointmentId);


    boolean isSlotAvailableForDate(int doctorId, Date date, String slot);

    List<AppointmentDto> getAppointmentsByPatientId(Integer patientId);

    List<AppointmentDto> getAppointmentsByDoctorId(Integer doctorId);

    List<AppointmentDto> getAppointmentsByPatientIdAndDate(Integer patientId, Date date);

    List<AppointmentDto> getAppointmentsByDoctorIdAndDate(Integer doctorId, Date date);

    List<String> getAllSlots();
}
