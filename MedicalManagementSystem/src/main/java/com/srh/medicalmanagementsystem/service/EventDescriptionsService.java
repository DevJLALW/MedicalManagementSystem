package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.EventDescriptions;

import java.util.List;

public interface EventDescriptionsService {

    List<EventDescriptions> getAllEventDescriptions();
    EventDescriptions saveEventDescription(EventDescriptions eventDescription);
    EventDescriptions getEventDescriptionById(int eventType);
    void deleteEventDescription(int eventType);
}
