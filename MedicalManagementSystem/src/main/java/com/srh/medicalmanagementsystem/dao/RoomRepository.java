package com.srh.medicalmanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srh.medicalmanagementsystem.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
