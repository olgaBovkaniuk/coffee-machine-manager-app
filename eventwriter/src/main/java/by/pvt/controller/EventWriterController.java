package by.pvt.controller;

import by.pvt.service.EventWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class EventWriterController {

    Logger log = Logger.getLogger("EventWriterController");

    @Autowired
    EventWriterService eventWriterService;

    @PostMapping("/event-writer")
    public void addNewEvent(@RequestBody EventCmd eventCmd) {
        eventWriterService.saveEvent(eventCmd);
        log.info("New eventCm: " + eventCmd);
    }
}
