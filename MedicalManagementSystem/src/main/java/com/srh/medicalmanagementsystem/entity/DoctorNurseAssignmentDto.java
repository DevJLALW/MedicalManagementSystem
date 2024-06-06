package com.srh.medicalmanagementsystem.entity;

public class DoctorNurseAssignmentDto {

    private Integer assignmentId;
    private Integer doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Integer nurseId;
    private String nurseFirstName;
    private String nurseLastName;

    public DoctorNurseAssignmentDto(Integer assignmentId, Integer doctorId, String doctorFirstName, String doctorLastName,
                                    Integer nurseId, String nurseFirstName, String nurseLastName) {
        this.assignmentId = assignmentId;
        this.doctorId = doctorId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.nurseId = nurseId;
        this.nurseFirstName = nurseFirstName;
        this.nurseLastName = nurseLastName;
    }

    public DoctorNurseAssignmentDto() {};


    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public Integer getNurseId() {
        return nurseId;
    }

    public void setNurseId(Integer nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseFirstName() {
        return nurseFirstName;
    }

    public void setNurseFirstName(String nurseFirstName) {
        this.nurseFirstName = nurseFirstName;
    }

    public String getNurseLastName() {
        return nurseLastName;
    }

    public void setNurseLastName(String nurseLastName) {
        this.nurseLastName = nurseLastName;
    }
}

