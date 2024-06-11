package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.SpecializationRepository;
import com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto;
import com.srh.medicalmanagementsystem.entity.Specialization;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SpecializationServiceImpl implements SpecializationService{

    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<DoctorSpecializationDto> getAllDoctorsSpecializations() {
        List<DoctorSpecializationDto> specializations = specializationRepository.findAllDoctorsSpecializations();
        specializations.sort(Comparator.comparing(
                DoctorSpecializationDto::getDoctorId
        ).reversed());
        return specializations;
    }

    @Transactional
    public Specialization saveSpecialization(DoctorSpecializationDto specializationDto) {
        Specialization specialization = new Specialization();
        specialization.setDoctorId(specializationDto.getDoctorId());
        specialization.setSpecializationName(specializationDto.getSpecializationName());
        return specializationRepository.save(specialization);
    }

    @Override
    public boolean deleteDoctorSpecialization(List<Integer> doctorIds) {
        boolean allDeleted = true;
        for (Integer doctorId: doctorIds) {
            if (specializationRepository.existsById(doctorId)) {
                specializationRepository.deleteById(doctorId);
            } else {
                allDeleted = false;
            }
        }
        return allDeleted;
    }

    @Override
    public List<DoctorSpecializationDto> findSpecializationByDoctorId(Integer doctorId) {
        return specializationRepository.searchByDoctorId(doctorId);
    }

    @Override
    public List<DoctorSpecializationDto> findSpecializationByKeyword(String keyword) {
        return specializationRepository.searchDoctorSpecializationByKeyword(keyword);
    }

    @Override
    public Specialization updateSpecialization(Integer doctorId, DoctorSpecializationDto specializationDto) {
        Specialization specialization = specializationRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found with id " + doctorId));
        specialization.setSpecializationName(specializationDto.getSpecializationName());
        return specializationRepository.save(specialization);
    }
}
