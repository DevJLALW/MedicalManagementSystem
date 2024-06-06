package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.entity.EventDescriptions;
import com.srh.medicalmanagementsystem.service.EventDescriptionsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eventDescriptions")
public class EventDescriptionsController {

    private final EventDescriptionsService eventDescriptionsService;

    public EventDescriptionsController(EventDescriptionsService eventDescriptionsService) {
        this.eventDescriptionsService = eventDescriptionsService;
    }

    @GetMapping("/all")
    public String getAllEventDescriptionsPage(Model model) {
        List<EventDescriptions> eventDescriptionsList = eventDescriptionsService.getAllEventDescriptions();
        model.addAttribute("eventDescriptions", eventDescriptionsList);
        return "patients/ShowEventDescriptions";
    }

    @GetMapping("/create")
    public String showCreateEventDescriptionsPage(Model model) {
        model.addAttribute("eventDescriptions", new EventDescriptions());
        return "patients/createEventDescriptions";
    }

    @PostMapping("/create")
    public String createEventDescriptions(
            @Valid @ModelAttribute("eventDescriptions") EventDescriptions eventDescriptions,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/createEventDescriptions";
        }
        eventDescriptionsService.saveEventDescription(eventDescriptions);
        return "redirect:/eventDescriptions/all";
    }

    @GetMapping("/update/{eventDescriptionsId}")
    public String showUpdateEventDescriptionsPage(
            @PathVariable("eventDescriptionsId") Integer eventDescriptionsId,
            Model model
    ) {
        EventDescriptions eventDescriptions = eventDescriptionsService.getEventDescriptionById(eventDescriptionsId);
        model.addAttribute("eventDescriptions", eventDescriptions);
        return "patients/UpdateEventDescriptions";
    }

    @PostMapping("/update/{eventDescriptionsId}")
    public String updateEventDescriptions(
            @PathVariable("eventDescriptionsId") Integer eventDescriptionsId,
            @Valid @ModelAttribute("eventDescriptions") EventDescriptions eventDescriptions,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "patients/UpdateEventDescriptions";
        }
        eventDescriptions.setEventType(eventDescriptionsId);
        eventDescriptionsService.saveEventDescription(eventDescriptions);
        return "redirect:/eventDescriptions/all";
    }

    @GetMapping("/delete/{eventDescriptionsId}")
    public String deleteEventDescriptions(@PathVariable("eventDescriptionsId") Integer eventDescriptionsId) {
        eventDescriptionsService.deleteEventDescription(eventDescriptionsId);
        return "redirect:/eventDescriptions/all";
    }
}
