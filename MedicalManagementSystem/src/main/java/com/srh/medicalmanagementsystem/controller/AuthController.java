package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.dao.PatientRepository;
import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.entity.Patient;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import com.srh.medicalmanagementsystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthController {
    private final PatientService patientService;
    private EmployeeService employeeService;

    @Autowired
    public AuthController(PatientService patientService,EmployeeService employeeService) {
        this.patientService = patientService;
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public String home() {
        return "patients/index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "register";
    }


    @GetMapping("/registerEmployee")
    public String showRegisterEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("actionUrl", "/registerEmployee");
        return "patients/CreateEmployee";
    }

    @PostMapping("/registerEmployee")
    public String resgisterEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult, Model model
    ) {
        System.out.println("Check if response is reached");
        if(bindingResult.hasErrors()){
            model.addAttribute("actionUrl", "/registerEmployee");
            return "patients/CreateEmployee";
        }
        Employee savedEmployee = employeeService.saveEmployee(employee);
        model.addAttribute("employeeID", savedEmployee.getEmployeeId());
        return "patients/EmployeeCreated";
    }


    @GetMapping("/login")
    public String login() {
        return "patients/index";
    }

    @GetMapping("/medicalmanagement/dashboard")
    public String dashboard() {
        return "patients/EmployeeDashboard";
    }


}
