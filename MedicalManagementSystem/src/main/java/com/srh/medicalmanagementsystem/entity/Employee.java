package com.srh.medicalmanagementsystem.entity;

import com.srh.medicalmanagementsystem.validation.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "ContactNumber", nullable = false)
    @NotEmpty(message = "Contact number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Contact number must contain only digits and optionally start with a '+'")
    private String contactNumber;

    @Column(name = "Email", nullable = false)
    @ValidEmail(message = "Email should be valid")
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role", nullable = false)
    private String role;

    @Column(name = "Status", nullable = false)
    private boolean status;
}
