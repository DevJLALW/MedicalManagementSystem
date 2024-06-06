package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.EventDescriptionsRepository;
import com.srh.medicalmanagementsystem.entity.EventDescriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDescriptionsServiceImpl implements EventDescriptionsService {

        @Autowired
        private EventDescriptionsRepository repository;

        @Override
        public List<EventDescriptions> getAllEventDescriptions() {
            return repository.findAll();
        }

        @Override
        public EventDescriptions saveEventDescription(EventDescriptions eventDescription) {
            return repository.save(eventDescription);
        }

        @Override
        public EventDescriptions getEventDescriptionById(int eventType) {
            return repository.findById(eventType).orElse(null);
        }

        @Override
        public void deleteEventDescription(int eventType) {
            repository.deleteById(eventType);
        }
}
