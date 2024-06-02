package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.UserDto;
import com.srh.medicalmanagementsystem.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByPatientId(String patientId);

    List<UserDto> findAllUsers();
}