package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Room")
@Data

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "PatientID")
    private Long patientId;

    @Column(name = "RoomNumber", unique = true, nullable = false)
    private String roomNumber;

    @Column(name = "RoomType", nullable = false)
    private String roomType;

    @Column(name = "RoomAdmissionStartDate")
    private LocalDate roomAdmissionStartDate;

    @Column(name = "RoomAdmissionEndDate")
    private LocalDate roomAdmissionEndDate;

    @Column(name = "NumberOfDays")
    private Long numberOfDays;

    @Column(name = "TotalRoomCost")
    private Float totalRoomCost;

    // Getters and Setters

    public LocalDate getRoomAdmissionStartDate() {
        return roomAdmissionStartDate;
    }

    public void setRoomAdmissionStartDate(LocalDate roomAdmissionStartDate) {
        this.roomAdmissionStartDate = roomAdmissionStartDate;
    }

    public LocalDate getRoomAdmissionEndDate() {
        return roomAdmissionEndDate;
    }

    public void setRoomAdmissionEndDate(LocalDate roomAdmissionEndDate) {
        this.roomAdmissionEndDate = roomAdmissionEndDate;
    }

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Float getTotalRoomCost() {
        return totalRoomCost;
    }

    public void setTotalRoomCost(Float totalRoomCost) {
        this.totalRoomCost = totalRoomCost;
    }

}