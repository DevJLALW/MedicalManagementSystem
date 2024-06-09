package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {


  List<Appointment> findByDoctorIdAndStartDateTimeBetween(int doctorId, Date startDateTime, Date endDateTime);

  }
