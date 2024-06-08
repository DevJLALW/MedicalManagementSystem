package com.srh.medicalmanagementsystem.entity;

public class DoctorSpecializationDto {

    private Integer doctorId;
    private String firstName;
    private String lastName;
    private String specializationName;

    public DoctorSpecializationDto () {}

    public DoctorSpecializationDto (Integer doctorId, String firstName, String lastName, String specializationName) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializationName = specializationName;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
}
