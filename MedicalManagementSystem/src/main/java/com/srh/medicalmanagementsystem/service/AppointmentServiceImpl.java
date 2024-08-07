package com.srh.medicalmanagementsystem.service;


import com.srh.medicalmanagementsystem.dao.AppointmentRepository;
import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.entity.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final List<String> ALL_SLOTS = Arrays.asList(
            "10:00-11:00", "11:00-12:00", "12:00-13:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00"
    );

    public static java.sql.Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();

        return Date.valueOf(localDate);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {

        return appointmentRepository.findAllAppointments();
    }

    @Override
    public AppointmentDto getAppointmentById(int appointmentId) {
        return appointmentRepository.findAppointmentById(appointmentId);
    }

    @Override
    public boolean isSlotAvailable(int doctorId, Date date, Time startTime) {
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
        Time slotStart = new Time(calendar.getTimeInMillis());

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[1].split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1].split(":")[1]));
        Time slotEnd = new Time(calendar.getTimeInMillis());

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

    @Override
    public List<String> getAvailableSlots(Integer doctorId, Date date) {
        System.out.println(doctorId+" "+date);
        List<AppointmentDto> appointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);

        System.out.println("Test===================================================");
        System.out.println(appointments);
        List<String> bookedSlots =new ArrayList<>();
        for(AppointmentDto appointment: appointments){

            System.out.println(appointment.getStartTime());
            System.out.println(appointment.getEndTime());
            String getStartTime = appointment.getStartTime().toString().substring(0, 5);
            String getEndTime = appointment.getEndTime().toString().substring(0, 5);
            System.out.println(getStartTime+"-"+getEndTime);
            bookedSlots.add(getStartTime+"-"+getEndTime);
        }
        // Filter out the booked slots from the ALL_SLOTS list
        List<String> availableSlots = new ArrayList<>(ALL_SLOTS);
        availableSlots.removeAll(bookedSlots);
        System.out.println("----------------availableSlots-------------------"+availableSlots);
        return availableSlots;
    }
}
