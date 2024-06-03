package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.DoctorNurseAssignmentRepository;
import com.srh.medicalmanagementsystem.entity.DoctorNurseAssignment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorNurseAssignmentServiceImpl implements DoctorNurseAssignmentService{

    private DoctorNurseAssignmentRepository assignmentRepository;

    @Autowired
    public DoctorNurseAssignmentServiceImpl(DoctorNurseAssignmentRepository assignmentRepository) {

        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<DoctorNurseAssignment> getAllDoctorNurseAssignments() {
        List<DoctorNurseAssignment> assignments = assignmentRepository.findAll();
        assignments.sort(Comparator.comparing(
                DoctorNurseAssignment::getAssignmentId
        ).reversed());
        return assignments;
    }

    @Transactional
    public DoctorNurseAssignment saveDoctorNurseAssignment (DoctorNurseAssignment assignment) {
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

    public List<DoctorNurseAssignment> findAssignmentsByIds (Integer employeeId) {
        return assignmentRepository.searchAssignments(employeeId);
    }

}
