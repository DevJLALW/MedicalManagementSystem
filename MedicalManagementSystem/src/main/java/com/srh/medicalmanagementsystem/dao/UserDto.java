package com.srh.medicalmanagementsystem.dao;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;

    @NotEmpty(message = "Patient ID should not be empty")
    private String patientId;

    @NotEmpty(message = "First name should not be empty")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    private String lastName;

    @NotEmpty(message = "Date of birth should not be empty")
    private String dateOfBirth;

    @NotEmpty(message = "Gender should not be empty")
    private String gender;

    @NotEmpty(message = "Address should not be empty")
    private String address;

    @NotEmpty(message = "Contact number should not be empty")
    private String contactNumber;

    @NotEmpty(message = "Insurance details should not be empty")
    private String insuranceDetails;

    @NotEmpty(message = "Medical history should not be empty")
    private String medicalHistory;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    private int ageYears;
    private int ageMonths;
    private int ageDays;

}