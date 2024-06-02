package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Role;
import com.srh.medicalmanagementsystem.entity.User;
import com.srh.medicalmanagementsystem.dao.UserDto;
import com.srh.medicalmanagementsystem.dao.RoleRepository;
import com.srh.medicalmanagementsystem.dao.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setPatientId(userDto.getPatientId());
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setGender(userDto.getGender());
        user.setAddress(userDto.getAddress());
        user.setContactNumber(userDto.getContactNumber());
        user.setInsuranceDetails(userDto.getInsuranceDetails());
        user.setMedicalHistory(userDto.getMedicalHistory());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByPatientId(String patientId) {
        return userRepository.findByPatientId(patientId);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setPatientId(user.getPatientId());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setGender(user.getGender());
        userDto.setAddress(user.getAddress());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setInsuranceDetails(user.getInsuranceDetails());
        userDto.setMedicalHistory(user.getMedicalHistory());

        // Calculate exact age
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(user.getDateOfBirth(), formatter);
        Period age = Period.between(dob, LocalDate.now());
        userDto.setAgeYears(age.getYears());
        userDto.setAgeMonths(age.getMonths());
        userDto.setAgeDays(age.getDays());

        return userDto;
    }
}