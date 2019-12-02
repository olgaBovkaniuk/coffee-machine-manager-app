package by.pvt.updater;

import by.pvt.event.*;
import by.pvt.pojo.CoffeeMachine;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.type.CoffeeMachineStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeMachineEventHandler {

    @Autowired
    SessionFactory sessionFactory;

    public void register(CoffeeMachineRegisteredEvent event) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setCoffeeMachineId(event.getAggregateId().toString());
        coffeeMachine.setCoffeeMachineName(event.getCoffeeMachineName());
        coffeeMachine.setStatus(event.getStatus());
        coffeeMachine.setImageName(event.getImageName());
        coffeeMachine.setEventId(event.getEventId());
        sessionFactory.getCurrentSession()
                .save(coffeeMachine);
    }

    public void updateData(CoffeeMachineDataUpdatedEvent event) {

        CoffeeMachineData coffeeMachineData = new CoffeeMachineData();
        CoffeeMachine coffeeMachine = sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineId = :cmId", CoffeeMachine.class)
                .setParameter("cmId", event.getAggregateId().toString())
                .getSingleResult();
        coffeeMachineData.setCoffeeMachine(coffeeMachine);
        coffeeMachineData.setWaterMl(event.getWaterMl());
        coffeeMachineData.setMilkMl(event.getMilkMl());
        coffeeMachineData.setCoffeeBeansGr(event.getCoffeeBeansGr());
        coffeeMachineData.setUsedCoffeeContainerPortion(event.getUsedCoffeeContainerPortion());
        sessionFactory.getCurrentSession()
                .save(coffeeMachineData);
    }

    public void start(CoffeeMachineStartedEvent event) {

        CoffeeMachine coffeeMachine = sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineId = :cmId", CoffeeMachine.class)
                .setParameter("cmId", event.getAggregateId().toString())
                .getSingleResult();
        coffeeMachine.setStatus(event.getStatus());
        coffeeMachine.setProcessId(event.getProcessId());
        sessionFactory.getCurrentSession()
                .saveOrUpdate(coffeeMachine);
    }

    public void stop(CoffeeMachineStoppedEvent event) {

        CoffeeMachine coffeeMachine = sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineId =:cmId", CoffeeMachine.class)
                .setParameter("cmId", event.getAggregateId().toString())
                .getSingleResult();
        coffeeMachine.setStatus(event.getStatus());
        coffeeMachine.setProcessId(event.getProcessId());
        sessionFactory.getCurrentSession()
                .saveOrUpdate(coffeeMachine);
    }

    public void delete(CoffeeMachineDeletedEvent event) {

        CoffeeMachine coffeeMachine = sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineId =:cmId", CoffeeMachine.class)
                .setParameter("cmId", event.getAggregateId().toString())
                .getSingleResult();
        sessionFactory.getCurrentSession()
                .delete(coffeeMachine);
    }
}
