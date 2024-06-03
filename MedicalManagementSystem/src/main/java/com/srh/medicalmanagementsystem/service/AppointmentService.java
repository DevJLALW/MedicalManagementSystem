package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.AppointmentRepository;
import com.srh.medicalmanagementsystem.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointment(){

        List<Appointment> appointment=new ArrayList<Appointment>();
        appointmentRepository.findAll().forEach(appointment1 -> appointment.add(appointment1));
        return appointment;
    }

    public Appointment getAppointmentById(int id){
        return appointmentRepository.findById(id).get();
    }

    public void saveOrUpdate(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void delete(int id){

        appointmentRepository.deleteById(id);
    }

    public void update(Appointment appointment, int appointmentId){
        appointmentRepository.save(appointment);
    }


}
