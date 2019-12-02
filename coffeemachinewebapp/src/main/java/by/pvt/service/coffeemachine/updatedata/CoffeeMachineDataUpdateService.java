package by.pvt.service.coffeemachine.updatedata;

import by.pvt.converter.CoffeeMachineDataDomainToDtoConverter;
import by.pvt.event.CoffeeMachineDataUpdatedEvent;
import by.pvt.repository.CoffeeMachineRepository;
import by.pvt.service.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Service
public class CoffeeMachineDataUpdateService {

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    @Autowired
    CoffeeMachineDataDomainToDtoConverter converter;

    @Transactional
    public boolean updateData(CoffeeMachineDataUpdateCmd updateCmd) {
        if (updateCmd.getCoffeeMachine() == null || updateCmd.getCoffeeMachine().getCoffeeMachineId() == null || updateCmd.getCoffeeMachine().getCoffeeMachineId().isEmpty()) {
            return false;
        }
        createNewCoffeeMachineDataEvent(updateCmd);
        return true;
    }

    public void createNewCoffeeMachineDataEvent(CoffeeMachineDataUpdateCmd updateCmd){
        CoffeeMachineDataUpdatedEvent coffeeMachineDataUpdatedEvent = new CoffeeMachineDataUpdatedEvent(
                UUID.fromString(updateCmd.getCoffeeMachine().getCoffeeMachineId()),
                updateCmd.getWaterMl(),
                updateCmd.getMilkMl(),
                updateCmd.getCoffeeBeansGr(),
                updateCmd.getUsedCoffeeContainerPortion()
        );
        try {
            EventSender.sendPostToEventWriter(coffeeMachineDataUpdatedEvent);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
