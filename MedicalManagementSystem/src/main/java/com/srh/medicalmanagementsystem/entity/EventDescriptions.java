package com.srh.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EventDescriptions")
@Data
public class EventDescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventType")
    private int eventType;

    @Column(name = "Description", nullable = false)
    private String description;



}
