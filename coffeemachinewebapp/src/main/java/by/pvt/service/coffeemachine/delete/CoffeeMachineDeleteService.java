package by.pvt.service.coffeemachine.delete;

import by.pvt.converter.CoffeeMachineDataDomainToDtoConverter;
import by.pvt.event.CoffeeMachineDeletedEvent;
import by.pvt.repository.CoffeeMachineRepository;
import by.pvt.service.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CoffeeMachineDeleteService {

    private static Logger log = Logger.getLogger("CoffeeMachineRegisterService");

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    @Autowired
    CoffeeMachineDataDomainToDtoConverter converter;

    @Transactional
    public boolean delete(UUID coffeeMachineId) {
        CoffeeMachineDeletedEvent coffeeMachineDeletedEvent = new CoffeeMachineDeletedEvent(
                coffeeMachineId
        );
        try {
            EventSender.sendPostToEventWriter(coffeeMachineDeletedEvent);
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }
        return true;
    }
}
