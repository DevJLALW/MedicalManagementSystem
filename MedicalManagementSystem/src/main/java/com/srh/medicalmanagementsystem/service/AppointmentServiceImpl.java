package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.AppointmentRepository;
import com.srh.medicalmanagementsystem.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final List<String> AVAILABLE_SLOTS = Arrays.asList(
            "10:00-11:00", "11:00-12:00", "12:00-13:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00"
    );

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public boolean isSlotAvailable(int doctorId, Date startDateTime, Date endDateTime) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndStartDateTimeBetween(doctorId, startDateTime, endDateTime);
        return appointments.isEmpty();
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        if (isSlotAvailable(appointment.getDoctorId(), appointment.getStartDateTime(), appointment.getEndDateTime())) {
            return appointmentRepository.save(appointment);
        } else {
            throw new IllegalArgumentException("Slot is already booked.");
        }
    }

    @Override
    public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment) {
        if (appointmentRepository.existsById(appointmentId)) {
            updatedAppointment.setAppointmentId(appointmentId);
            return appointmentRepository.save(updatedAppointment);
        }
        return null;
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<String> getAvailableSlots() {
        return AVAILABLE_SLOTS;
    }

    @Override
    public boolean isSlotAvailableForDate(int doctorId, String slot, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String[] times = slot.split("-");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0].split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[0].split(":")[1]));
        Date slotStart = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[1].split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1].split(":")[1]));
        Date slotEnd = calendar.getTime();

        return isSlotAvailable(doctorId, slotStart, slotEnd);
    }




}
