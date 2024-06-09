package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



    @GetMapping("/all")
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "patients/showAppointments";
    }

    @GetMapping("/create")
    public String showCreateAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
        return "patients/createAppointment";
    }

    @PostMapping("/create")
    public String createAppointment(
            @Valid @ModelAttribute("appointment") Appointment appointment,
            @RequestParam("slot") String slot,
            @RequestParam("date") String dateString,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
            return "patients/createAppointment";
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            if (appointmentService.isSlotAvailableForDate(appointment.getDoctorId(), slot, date)) {
                String[] times = slot.split("-");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                appointment.setStartDateTime(dateFormat.parse(dateString + " " + times[0]));
                appointment.setEndDateTime(dateFormat.parse(dateString + " " + times[1]));

                appointmentService.saveAppointment(appointment);
                return "redirect:/appointments/all";
            } else {
                model.addAttribute("error", "Slot is already booked.");
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid date format.");
        }

        model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
        return "patients/createAppointment";
    }

    @GetMapping("/update/{id}")
    public String showUpdateAppointmentForm(@PathVariable("id") int id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
        return "patients/updateAppointment";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("appointment") Appointment appointment,
            @RequestParam("slot") String slot,
            @RequestParam("date") String dateString,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            //System.out.println(bindingResult.getAllErrors());
            model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
            return "patients/updateAppointment";
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            if (appointmentService.isSlotAvailableForDate(appointment.getDoctorId(), slot, date)) {
                String[] times = slot.split("-");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                appointment.setStartDateTime(dateFormat.parse(dateString + " " + times[0]));
                appointment.setEndDateTime(dateFormat.parse(dateString + " " + times[1]));

                appointmentService.updateAppointment(id,appointment);
                return "redirect:/appointments/all";
            } else {
                model.addAttribute("error", "Slot is already booked.");
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid date format.");
        }

        model.addAttribute("availableSlots", appointmentService.getAvailableSlots());
        return "patients/showAppointments";
    }



    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") int id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments/all";
    }
}
