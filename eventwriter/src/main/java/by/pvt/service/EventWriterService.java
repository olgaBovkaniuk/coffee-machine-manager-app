package by.pvt.service;

import by.pvt.controller.EventCmd;
import by.pvt.event.Event;
import by.pvt.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventWriterService {

    @Autowired
    EventRepository eventRepository;

    public void saveEvent(EventCmd eventCmd) {
        Event event = new Event();
        event.setAggregateId(eventCmd.getAggregateId().toString());
        event.setEventData(eventCmd.getEventData());
        event.setEventType(eventCmd.getEventType());
        event.setEventDate(eventCmd.getEventDate());
        eventRepository.save(event);
    }
}
