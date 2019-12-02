package by.pvt.service;

import by.pvt.controller.CoffeeMachineCmd;
import by.pvt.controller.CoffeeMachineDataCmd;
import by.pvt.event.CoffeeMachineDataUpdatedEvent;
import by.pvt.event.CoffeeMachineRegisteredEvent;
import by.pvt.repository.CoffeeMachineDataRepository;
import by.pvt.repository.CoffeeMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoffeeMachineService {

    @Autowired
    CoffeeMachineDataRepository coffeeMachineDataRepository;

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    public void createNewCoffeeMachineEvent(CoffeeMachineCmd coffeeMachineCmd) throws IOException, InterruptedException {
        CoffeeMachineRegisteredEvent coffeeMachineRegisteredEvent = new CoffeeMachineRegisteredEvent(
                coffeeMachineCmd.getCoffeeMachineId(),
                coffeeMachineCmd.getCoffeeMachineName(),
                coffeeMachineCmd.getStatus(),
                coffeeMachineCmd.getImageName()
        );
        EventSender.sendPostToEventWriter(coffeeMachineRegisteredEvent);
    }

    public void updateCoffeeMachineDataEvent(CoffeeMachineDataCmd coffeeMachineDataCmd) throws IOException, InterruptedException {
        CoffeeMachineDataUpdatedEvent coffeeMachineDataUpdatedEvent = new CoffeeMachineDataUpdatedEvent(
                coffeeMachineDataCmd.getCoffeeMachineId(),
                coffeeMachineDataCmd.getWaterMl(),
                coffeeMachineDataCmd.getMilkMl(),
                coffeeMachineDataCmd.getCoffeeBeansGr(),
                coffeeMachineDataCmd.getUsedCoffeeContainerPortion()
        );
        EventSender.sendPostToEventWriter(coffeeMachineDataUpdatedEvent);
    }
}
