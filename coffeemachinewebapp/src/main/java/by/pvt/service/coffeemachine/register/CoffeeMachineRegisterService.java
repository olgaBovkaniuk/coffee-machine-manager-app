package by.pvt.service.coffeemachine.register;

import by.pvt.converter.CoffeeMachineDataDomainToDtoConverter;
import by.pvt.event.CoffeeMachineRegisteredEvent;
import by.pvt.repository.CoffeeMachineRepository;
import by.pvt.service.EventSender;
import by.pvt.type.CoffeeMachineStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CoffeeMachineRegisterService {

    private static Logger log = Logger.getLogger("CoffeeMachineRegisterService");

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    @Autowired
    CoffeeMachineDataDomainToDtoConverter converter;

    @Transactional
    public boolean register(CoffeeMachineRegisterCmd registerCmd) {
        if (registerCmd.getCoffeeMachineName().isEmpty() || registerCmd.getCoffeeMachineName() == null) {
            return false;
        }
        createNewCoffeeMachineEvent(registerCmd);
//        return coffeeMachineRepository.addCoffeeMachine(registerCmd);
        return true;
    }

    private void createNewCoffeeMachineEvent(CoffeeMachineRegisterCmd registerCmd){
        CoffeeMachineRegisteredEvent coffeeMachineRegisteredEvent = new CoffeeMachineRegisteredEvent(
                UUID.fromString(registerCmd.getCoffeeMachineId()),
                registerCmd.getCoffeeMachineName(),
                CoffeeMachineStatus.OFF,
                registerCmd.getImageName()
        );
        try {
            EventSender.sendPostToEventWriter(coffeeMachineRegisteredEvent);
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }
    }
}
