package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Appointment;
import com.srh.medicalmanagementsystem.entity.AppointmentDto;
import com.srh.medicalmanagementsystem.entity.MedicalRecord;
import com.srh.medicalmanagementsystem.service.AppointmentService;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import com.srh.medicalmanagementsystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    private PatientService patientService;
    private EmployeeService employeeService;


    public AppointmentController(AppointmentService appointmentService, PatientService patientService, EmployeeService employeeService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public String getAllAppointments(Model model) {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "patients/ShowAppointments";
    }

    @GetMapping("/{id}/viewFutureAppointments")
    public String getFutureAppointments(@PathVariable("id") int id, Model model) {
        List<AppointmentDto> appointments;
        if (id/100000 > 0) {
            appointments = appointmentService.getAppointmentsByPatientId(id);
        }
        else {
            appointments = appointmentService.getAppointmentsByDoctorId(id);
        }
        model.addAttribute("appointments", appointments);
        return "patients/ShowAppointments";
    }

    @GetMapping("/{id}/viewDateWiseAppointments/")
    public String getDateWiseAppointments(@PathVariable("id") int id, @RequestParam("date") String date, Model model) throws ParseException {
        List<AppointmentDto> appointments;
        Date dateFormat = Date.valueOf(date);
        if (id/100000 > 0) {
            appointments = appointmentService.getAppointmentsByPatientIdAndDate(id, dateFormat);
        }
        else {
            appointments = appointmentService.getAppointmentsByDoctorIdAndDate(id, dateFormat);
        }
        model.addAttribute("appointments", appointments);
        return "patients/ShowAppointments";
    }



    @GetMapping("/create")
    public String showCreateAppointmentForm(Model model) {
        model.addAttribute("appointment", new AppointmentDto());
        model.addAttribute("allSlots", appointmentService.getAllSlots());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", employeeService.getRoleSpecificEmployees("Doctor"));

        return "patients/CreateAppointment";
    }

    @PostMapping("/create")
    public String createAppointment(
            @Valid @ModelAttribute("appointment") AppointmentDto appointment,
            @ModelAttribute("slot") String slot,
            BindingResult bindingResult,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        if (bindingResult.hasErrors()) {
            model.addAttribute("allSlots", appointmentService.getAllSlots());
            return "patients/CreateAppointment";
        }

        try {
            if (appointmentService.isSlotAvailableForDate(appointment.getDoctorId(), appointment.getDate(), slot)) {
                String[] times = slot.split("-");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                // Changed this part for correct parsing of time
                java.util.Date startTimeUtilFormat = timeFormat.parse(times[0]);
                java.util.Date endTimeUtilFormat = timeFormat.parse(times[1]);

                java.sql.Time startTime = new java.sql.Time(startTimeUtilFormat.getTime());
                java.sql.Time endTime = new java.sql.Time(endTimeUtilFormat.getTime());

                appointment.setStartTime(startTime);
                appointment.setEndTime(endTime);

                System.out.println(appointment.getDate());
                appointmentService.saveAppointment(appointment);
                if (userDetails.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_PATIENT"))) {
                    return "redirect:/patients/details/" + userDetails.getUsername();
                } else {
                    return "redirect:/appointments/all";
                }
            } else {
                model.addAttribute("error", "Slot is already booked.");
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid time format.");
        }

        model.addAttribute("allSlots", appointmentService.getAllSlots());
        return "patients/CreateAppointment";
    }


    @GetMapping("/update/{id}")
    public String showUpdateAppointmentForm(@PathVariable("id") int id, Model model) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", employeeService.getRoleSpecificEmployees("Doctor"));
        model.addAttribute("allSlots", appointmentService.getAllSlots());
        return "patients/UpdateAppointment";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("appointment") AppointmentDto appointment,

            @RequestParam("slot") String slot,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("allSlots", appointmentService.getAllSlots());
            return "patients/UpdateAppointment";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        try {
            if (appointmentService.isSlotAvailableForDate(appointment.getDoctorId(), appointment.getDate(), slot)) {
                String[] times = slot.split("-");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                java.util.Date startTimeUtilFormat = timeFormat.parse(times[0]);
                java.util.Date endTimeUtilFormat = timeFormat.parse(times[1]);

                java.sql.Time startTime = new java.sql.Time(startTimeUtilFormat.getTime());
                java.sql.Time endTime = new java.sql.Time(endTimeUtilFormat.getTime());

                appointment.setStartTime(startTime);
                appointment.setEndTime(endTime);

                appointmentService.updateAppointment(id, appointment);
                model.addAttribute("appointments", appointmentService.getAllAppointments());
                if (userDetails.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_PATIENT"))) {
                    return "redirect:/patients/details/" + userDetails.getUsername();
                } else {
                return "patients/ShowAppointments";}
            } else {
                model.addAttribute("error", "Slot is already booked.");
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid time format.");
        }

        model.addAttribute("allSlots", appointmentService.getAllSlots());
        return "patients/UpdateAppointment";
    }



    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        appointmentService.deleteAppointment(id);
        if (userDetails.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_PATIENT"))) {
            return "redirect:/patients/details/" + userDetails.getUsername();
        } else {
        return "redirect:/appointments/all";}
    }




    @GetMapping("/search")
    public String searchAppointmentsById(
            @RequestParam("id") Integer id,
            Model model
    ) {
        List<AppointmentDto> appointments;
        if (id/100000 > 0) {
            appointments = appointmentService.getAppointmentsByPatientId(id);
        }
        else {
            appointments = appointmentService.getAppointmentsByDoctorId(id);
        }
        model.addAttribute("appointments", appointments);
        return "patients/ShowAppointments";
    }

}
