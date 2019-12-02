package by.pvt.updater;

import by.pvt.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EventHandler {

    private static Logger log = Logger.getLogger(EventHandler.class.getName());

    @Autowired
    CoffeeMachineEventHandler coffeeMachineEventHandler;

    @Autowired
    AppUserEventHandler appUserEventHandler;

    @Transactional(value = "readTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public boolean handleEvents(List<EventRecord> events) {
        for (EventRecord event : events) {
            try {
                AbstractEvent abstractEvent = DeserializationUtil.deserializeEvent(event);
                if (abstractEvent instanceof CoffeeMachineRegisteredEvent) {
                    CoffeeMachineRegisteredEvent coffeeMachineRegisteredEvent = (CoffeeMachineRegisteredEvent) abstractEvent;
                    coffeeMachineEventHandler.register(coffeeMachineRegisteredEvent);
                } else if (abstractEvent instanceof CoffeeMachineDataUpdatedEvent) {
                    CoffeeMachineDataUpdatedEvent coffeeMachineDataUpdatedEvent = (CoffeeMachineDataUpdatedEvent) abstractEvent;
                    coffeeMachineEventHandler.updateData(coffeeMachineDataUpdatedEvent);
                } else if (abstractEvent instanceof CoffeeMachineStartedEvent) {
                    CoffeeMachineStartedEvent coffeeMachineStartedEvent = (CoffeeMachineStartedEvent) abstractEvent;
                    coffeeMachineEventHandler.start(coffeeMachineStartedEvent);
                } else if (abstractEvent instanceof CoffeeMachineStoppedEvent) {
                    CoffeeMachineStoppedEvent coffeeMachineStoppedEvent = (CoffeeMachineStoppedEvent) abstractEvent;
                    coffeeMachineEventHandler.stop(coffeeMachineStoppedEvent);
                } else if (abstractEvent instanceof AppUserRoleCreatedEvent) {
                    AppUserRoleCreatedEvent appUserRoleCreatedEvent = (AppUserRoleCreatedEvent) abstractEvent;
                    appUserEventHandler.createRole(appUserRoleCreatedEvent);
                } else if (abstractEvent instanceof AppUserRegisteredEvent) {
                    AppUserRegisteredEvent appUserRegisteredEvent = (AppUserRegisteredEvent) abstractEvent;
                    appUserEventHandler.register(appUserRegisteredEvent);
                } else if (abstractEvent instanceof CoffeeMachineDeletedEvent) {
                    CoffeeMachineDeletedEvent coffeeMachineDeletedEvent = (CoffeeMachineDeletedEvent) abstractEvent;
                    coffeeMachineEventHandler.delete(coffeeMachineDeletedEvent);
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.severe(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
