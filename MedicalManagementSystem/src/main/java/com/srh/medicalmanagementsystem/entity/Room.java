package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Room")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PatientID")
    private Integer patientId;

    @Column(name = "RoomNumber", nullable = false)
    private String roomNumber;

    @Column(name = "RoomType", nullable = false)
    private String roomType;

    @Column(name = "RoomAdmissionStartDate")
    private LocalDate roomAdmissionStartDate;

    @Column(name = "RoomAdmissionEndDate")
    private LocalDate roomAdmissionEndDate;

    @Column(name = "NumberOfDays")
    private Long numberOfDays;

    @Column(name = "RoomBill")
    private BigDecimal roomBill;

    public void setTotalRoomCost(BigDecimal totalRoomCost) {
        this.roomBill = totalRoomCost;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "PatientID", insertable = false, updatable = false)
    private Patient patient;
}
