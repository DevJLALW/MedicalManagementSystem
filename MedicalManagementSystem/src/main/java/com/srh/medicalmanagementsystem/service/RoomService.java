package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Room;
import com.srh.medicalmanagementsystem.dao.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.temporal.ChronoUnit;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found with id " + id));
    }

    @Transactional
    public Room saveRoom(Room room) {
        calculateRoomDetails(room);
        return roomRepository.save(room);
    }

    @Transactional
    public Room updateRoom(Long roomId, Room roomDetails) {
        Room existingRoom = findById(roomId);

        existingRoom.setPatientId(roomDetails.getPatientId());
        existingRoom.setRoomNumber(roomDetails.getRoomNumber());
        existingRoom.setRoomType(roomDetails.getRoomType());
        existingRoom.setRoomAdmissionStartDate(roomDetails.getRoomAdmissionStartDate());
        existingRoom.setRoomAdmissionEndDate(roomDetails.getRoomAdmissionEndDate());

        calculateRoomDetails(existingRoom);

        return roomRepository.save(existingRoom);
    }

    private void calculateRoomDetails(Room room) {
        if (room.getRoomAdmissionStartDate() != null && room.getRoomAdmissionEndDate() != null) {
            long days = ChronoUnit.DAYS.between(room.getRoomAdmissionStartDate(), room.getRoomAdmissionEndDate());
            room.setNumberOfDays(days);
            room.setTotalRoomCost(calculateTotalCost(room.getRoomType(), days));
        }
    }

    private Float calculateTotalCost(String roomType, long numberOfDays) {
        float costPerNight;
        switch (roomType) {
            case "ICU":
                costPerNight = 80;
                break;
            case "CCU":
                costPerNight = 85;
                break;
            case "Private Room":
                costPerNight = 50;
                break;
            case "Semi-Private Room":
                costPerNight = 35;
                break;
            case "General Ward":
            default:
                costPerNight = 20;
                break;
        }
        return costPerNight * numberOfDays;
    }
}