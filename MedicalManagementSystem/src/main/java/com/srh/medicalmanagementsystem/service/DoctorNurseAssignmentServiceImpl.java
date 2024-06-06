package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.DoctorNurseAssignmentRepository;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignmentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DoctorNurseAssignmentServiceImpl implements DoctorNurseAssignmentService{

    private DoctorNurseAssignmentRepository assignmentRepository;

    @Autowired
    public DoctorNurseAssignmentServiceImpl(DoctorNurseAssignmentRepository assignmentRepository) {

        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<DoctorNurseAssignmentDto> getAllDoctorNurseAssignments() {
        List<DoctorNurseAssignmentDto> assignments = assignmentRepository.findAllAssignments();
        assignments.sort(Comparator.comparing(
                DoctorNurseAssignmentDto::getAssignmentId
        ).reversed());
        return assignments;
    }

    @Transactional
    public DoctorNurseAssignment saveDoctorNurseAssignment (DoctorNurseAssignmentDto assignmentDto) {
        DoctorNurseAssignment assignment = new DoctorNurseAssignment();
        assignment.setAssignmentId(assignmentDto.getAssignmentId());
        assignment.setDoctorId(assignmentDto.getDoctorId());
        assignment.setNurseId(assignmentDto.getNurseId());
        return assignmentRepository.save(assignment);
    }

    @Override
    public boolean deleteDoctorNurseAssignments (List<Integer> assignmentIds) {
        boolean allDeleted = true;
        for (Integer assignmentId: assignmentIds) {
            if (assignmentRepository.existsById(assignmentId)) {
                assignmentRepository.deleteById(assignmentId);
            } else {
                allDeleted = false;
            }
        }
        return allDeleted;
    }

    public List<DoctorNurseAssignmentDto> findAssignmentsByIds (Integer employeeId) {
        return assignmentRepository.searchAssignmentsById(employeeId);
    }

    public List<DoctorNurseAssignmentDto> findAssignmentsByName(String name) {
        return assignmentRepository.searchAssignmentsByKeyword(name);
    }

}
