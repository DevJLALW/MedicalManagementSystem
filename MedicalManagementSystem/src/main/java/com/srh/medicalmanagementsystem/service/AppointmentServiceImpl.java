package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.AppointmentRepository;
import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.entity.AppointmentDto;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final List<String> ALL_SLOTS = Arrays.asList(
            "10:00-11:00", "11:00-12:00", "12:00-13:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00"
    );

    public static Date getCurrentDate() {
        // Get the current date
        LocalDate localDate = LocalDate.now();

        // Convert LocalDate to Date
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {

        return appointmentRepository.findAllAppointments();
    }

    @Override
    public AppointmentDto getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public boolean isSlotAvailable(int doctorId, Date date, Date startTime) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsAssignedForDoctorSlot(doctorId, date, startTime);
        return appointments.isEmpty();
    }

    @Override
    public Appointment saveAppointment(AppointmentDto appointmentDetails) {
        if ( isSlotAvailable(appointmentDetails.getDoctorId(), appointmentDetails.getDate(), appointmentDetails.getStartTime())) {
            Appointment appointment = new Appointment();
            appointment.setDoctorId(appointmentDetails.getDoctorId());
            appointment.setPatientId(appointmentDetails.getPatientId());
            appointment.setDate(appointmentDetails.getDate());
            appointment.setStartTime(appointmentDetails.getStartTime());
            appointment.setEndTime(appointmentDetails.getEndTime());
            return appointmentRepository.save(appointment);
        }else {
            throw new IllegalArgumentException("Slot is already booked.");
        }
    }

    @Override
    public Appointment updateAppointment(int appointmentId, AppointmentDto updatedAppointment) {
        if (appointmentRepository.existsById(appointmentId)) {
            updatedAppointment.setAppointmentId(appointmentId);
            if ( isSlotAvailable(updatedAppointment.getDoctorId(), updatedAppointment.getDate(), updatedAppointment.getStartTime())) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(updatedAppointment.getAppointmentId());
                appointment.setDoctorId(updatedAppointment.getDoctorId());
                appointment.setPatientId(updatedAppointment.getPatientId());
                appointment.setDate(updatedAppointment.getDate());
                appointment.setStartTime(updatedAppointment.getStartTime());
                appointment.setEndTime(updatedAppointment.getEndTime());
                return appointmentRepository.save(appointment);
            }else {
                throw new IllegalArgumentException("Slot is already booked.");
            }
        }
        return null;
    }

    @Override
    public void deleteAppointment(int appointmentId) {

        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<String> getAllSlots() {
        return ALL_SLOTS;
    }

    @Override
    public boolean isSlotAvailableForDate(int doctorId, Date date, String slot) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String[] times = slot.split("-");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0].split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[0].split(":")[1]));
        Date slotStart = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[1].split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1].split(":")[1]));
        Date slotEnd = calendar.getTime();

        return isSlotAvailable(doctorId, date, slotStart);
    }


    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(Integer patientId) {
        return appointmentRepository.findAllByPatientId(patientId, getCurrentDate());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(Integer doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId, getCurrentDate());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByPatientIdAndDate(Integer patientId, Date date) {

        return appointmentRepository.findByPatientIdAndDate(patientId, date);
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctorIdAndDate(Integer doctorId, Date date) {

        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }

}
