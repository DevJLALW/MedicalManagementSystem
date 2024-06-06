package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.Room;
import com.srh.medicalmanagementsystem.service.RoomService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "patients/Rooms";
    }

    @GetMapping("/rooms/new")
    public String createRoomForm(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "patients/CreateRoom";
    }

    @PostMapping("/rooms")
    public String saveRoom(@ModelAttribute("room") Room room) {
        roomService.saveRoom(room);
        return "redirect:/Rooms";
    }

    @GetMapping("/rooms/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "patients/EditRoom";
    }

    @PostMapping("/rooms/update/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute("room") Room roomDetails) {
        roomService.updateRoom(id, roomDetails);
        return "redirect:/Rooms";
    }
}