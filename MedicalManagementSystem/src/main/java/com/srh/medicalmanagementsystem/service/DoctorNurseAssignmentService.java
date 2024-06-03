package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;

import java.util.List;

public interface DoctorNurseAssignmentService {

    public List<DoctorNurseAssignment> getAllDoctorNurseAssignments();

    public DoctorNurseAssignment saveDoctorNurseAssignment(DoctorNurseAssignment assignment);

    public boolean deleteDoctorNurseAssignments(List<Integer> assignmentIds);

    public List<DoctorNurseAssignment> findAssignmentsByIds(Integer employeeId);
}
