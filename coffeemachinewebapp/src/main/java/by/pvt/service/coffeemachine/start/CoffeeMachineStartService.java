package by.pvt.service.coffeemachine.start;

import by.pvt.event.CoffeeMachineStartedEvent;
import by.pvt.service.EventSender;
import by.pvt.type.CoffeeMachineStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CoffeeMachineStartService {

    private static Logger log = Logger.getLogger(CoffeeMachineStartService.class.getName());

    @Transactional
    public void start(UUID coffeeMachineId) {
        try {
            Process ps = Runtime.getRuntime().exec("java -jar /Users/mr_nikolasmirnov/IdeaProjects/coffeemachine1/target/coffee-machine-1-1.0-SNAPSHOT.jar " + coffeeMachineId.toString());
            long pid = ps.pid();
            CoffeeMachineStartedEvent coffeeMachineStartedEvent = new CoffeeMachineStartedEvent(
                    coffeeMachineId,
                    CoffeeMachineStatus.ON,
                    pid);
            EventSender.sendPostToEventWriter(coffeeMachineStartedEvent);
        } catch (IOException | InterruptedException e) {
            log.info(e.getMessage());
        }
    }
}
