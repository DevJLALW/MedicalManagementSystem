package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Employee;
import com.srh.medicalmanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeControllerPages {

    private EmployeeService employeeService;

    public EmployeeControllerPages(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public String getAllEmployeesPage(Model model) {
        List<Employee> employeeList= employeeService.getAllEmployees();
        model.addAttribute("employees",employeeList);

        return "patients/ShowEmployees";
    }

    @GetMapping("/create")
    public String showCreateEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "patients/CreateEmployee";
    }

    @PostMapping("/create")
    public String createEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()){
            return "patients/CreateEmployee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees/all";
    }

    @GetMapping("/update/{employeeId}")
    public String showUpdateEmployeePage(
            @PathVariable("employeeId") Integer employeeId,
            Model model
    ) {
        Employee employee= employeeService.findEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        return "patients/UpdateEmployee";
    }

    @PostMapping("/update/{employeeId}")
    public String updateEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @ModelAttribute("employee") Employee employee
    ) {
        employeeService.updateEmployee(employeeId, employee);
        return "redirect:/employees/all";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeIds") List<Integer> employeeIds) {
        employeeService.deleteEmployees(employeeIds);

        return "redirect:/employees/all";
    }
//
//    @GetMapping("/search")
//    public String searchEmployees(
//            @RequestParam("keyword") String keyword,
//            Model model
//    ) {
//        List<Employee> employeeList =employeeService.searchEmployees(keyword);
//        model.addAttribute("employees", employeeList);
//
//        return "patients/ShowEmployees";
//    }

    @GetMapping("/search")
    public String searchEmployees(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<Employee> employeeList;
        try {
            // Attempt to parse the keyword as an integer
            Integer employeeId = Integer.parseInt(keyword);
            employeeList = employeeService.searchEmployeesById(employeeId);
        } catch (NumberFormatException e) {
            // If parsing fails, treat it as a string keyword
            employeeList =  employeeService.searchEmployeesByKeyword(keyword);
        }
//        List<Employee> employeeList =employeeService.searchEmployees(employeeId);
        model.addAttribute("employees", employeeList);

        return "patients/ShowEmployees";
    }
    /*@GetMapping("/employees/searchDoctors")
    public List<Employee> searchDoctors(@RequestParam("query") String query) {
        System.out.println("Searching doctors with query: " + query);
        List<Employee> doctors = employeeService.searchDoctorsByName(query);
        System.out.println("Found doctors: " + doctors);
        return doctors;
    }*/
}
