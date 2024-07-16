package com.srh.medicalmanagementsystem.controller;
import com.srh.medicalmanagementsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/availableSlots")
    public ResponseEntity<List<String>> getAvailableSlots(
            @RequestParam("doctorId") Integer doctorId,
            @RequestParam("date") Date date
    ) {
        System.out.println("=====================Rest Controller=====================");
        List<String> availableSlots = appointmentService.getAvailableSlots(doctorId, date);
        System.out.println("==availableSlots=="+availableSlots);
        return ResponseEntity.ok(availableSlots);
    }
}

