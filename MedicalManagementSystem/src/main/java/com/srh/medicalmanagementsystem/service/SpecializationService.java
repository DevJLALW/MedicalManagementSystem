package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.DoctorSpecializationDto;
import com.srh.medicalmanagementsystem.entity.Specialization;

import java.util.List;

public interface SpecializationService{
    public List<DoctorSpecializationDto> getAllDoctorsSpecializations();

    public Specialization saveSpecialization(DoctorSpecializationDto specialization);

    public boolean deleteDoctorSpecialization(List<Integer> doctorIds);

    public List<DoctorSpecializationDto> findSpecializationByDoctorId(Integer doctorId);

    public List<DoctorSpecializationDto> findSpecializationByKeyword(String keyword);

    public Specialization updateSpecialization (Integer doctorId);

}
