package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;

import java.util.List;

public interface DoctorNurseAssignmentService {

    public List<DoctorNurseAssignmentDto> getAllDoctorNurseAssignments();

    public DoctorNurseAssignment saveDoctorNurseAssignment(DoctorNurseAssignmentDto assignment);

    public boolean deleteDoctorNurseAssignments(List<Integer> assignmentIds);

    public List<DoctorNurseAssignmentDto> findAssignmentsByIds(Integer employeeId);

    public List<DoctorNurseAssignmentDto> findAssignmentsByName(String name);
}
