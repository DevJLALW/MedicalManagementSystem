package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Appointment;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int appointmentId);

    boolean isSlotAvailable(int doctorId, Date startDateTime, Date endDateTime);

    Appointment saveAppointment(Appointment appointment);

    Appointment updateAppointment(int appointmentId, Appointment updatedAppointment);

    void deleteAppointment(int appointmentId);

    List<String> getAvailableSlots();

    boolean isSlotAvailableForDate(int doctorId, String slot, Date date);

    List<Appointment> getAppointmentsByPatientId(Integer patientId);
}
