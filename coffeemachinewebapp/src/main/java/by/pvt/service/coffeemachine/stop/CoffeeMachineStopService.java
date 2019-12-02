package by.pvt.service.coffeemachine.stop;

import by.pvt.event.CoffeeMachineStoppedEvent;
import by.pvt.pojo.CoffeeMachine;
import by.pvt.service.EventSender;
import by.pvt.type.CoffeeMachineStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CoffeeMachineStopService {

    private static Logger log = Logger.getLogger(CoffeeMachineStopService.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void stop(UUID coffeeMachineId) {
        CoffeeMachine coffeeMachine =
                sessionFactory.getCurrentSession()
                        .createQuery("from coffee_machine where coffeeMachineId =:cmId", CoffeeMachine.class)
                        .setParameter("cmId", coffeeMachineId.toString())
                        .getSingleResult();
        try {
            Runtime.getRuntime().exec("kill " + coffeeMachine.getProcessId());
            CoffeeMachineStoppedEvent cmStoppedEvent = new CoffeeMachineStoppedEvent(
                    coffeeMachineId,
                    CoffeeMachineStatus.OFF,
                    null
            );
            EventSender.sendPostToEventWriter(cmStoppedEvent);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
