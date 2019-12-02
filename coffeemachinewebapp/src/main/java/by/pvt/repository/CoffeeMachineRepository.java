package by.pvt.repository;

import by.pvt.pojo.CoffeeMachine;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.service.coffeemachine.register.CoffeeMachineRegisterCmd;
import by.pvt.type.CoffeeMachineStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CoffeeMachineRepository {

    @Autowired
    private SessionFactory sessionFactory;
    private static final int limitResultsPerPage = 10;

    public List<CoffeeMachineData> findAllCMData(int page, UUID coffeeMachineId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine_data cmd " +
                        "where cmd.coffeeMachine.coffeeMachineId = :cmId", CoffeeMachineData.class)
                .setParameter("cmId", coffeeMachineId.toString())
                .setFirstResult(page * limitResultsPerPage)
                .setMaxResults(limitResultsPerPage)
                .list();
    }

    public Long dataCount(UUID coffeeMachineId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from coffee_machine_data where coffeeMachineId =:cmIdParam", Long.class)
                .setParameter("cmIdParam", coffeeMachineId.toString())
                .uniqueResult();
    }

    public Long dataCMCount() {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from coffee_machine", Long.class)
                .uniqueResult();
    }

    public CoffeeMachineData findItemById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(CoffeeMachineData.class, id);
    }

    public List<CoffeeMachine> findByCoffeeMachineName(String name, int count) {
        return sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineName like :searchStr", CoffeeMachine.class)
                .setParameter("searchStr", "%" + name + "%")
                .setMaxResults(count)
                .list();
    }

    public boolean addCoffeeMachine(CoffeeMachineRegisterCmd coffeeMachineCmd) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachineCmd.setStatus(CoffeeMachineStatus.OFF);
        coffeeMachine.setCoffeeMachineId(coffeeMachineCmd.getCoffeeMachineId().toString());
        coffeeMachine.setCoffeeMachineName(coffeeMachineCmd.getCoffeeMachineName());
        coffeeMachine.setImageName(coffeeMachineCmd.getImageName());
        coffeeMachine.setStatus(coffeeMachineCmd.getStatus());
        sessionFactory.getCurrentSession().persist(coffeeMachine);
        return true;
    }

    public List<CoffeeMachine> findAllCM(int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine", CoffeeMachine.class)
                .setFirstResult(page * limitResultsPerPage)
                .setMaxResults(limitResultsPerPage)
                .list();
    }

    public CoffeeMachine findItemByUuid(UUID coffeeMachineId) {
        List<CoffeeMachine> coffeeMachine = sessionFactory.getCurrentSession()
                .createQuery("from coffee_machine where coffeeMachineId =:cmId", CoffeeMachine.class)
                .setParameter("cmId", coffeeMachineId.toString())
                .setMaxResults(1)
                .list();
        if (coffeeMachine != null && !coffeeMachine.isEmpty()) {
            return coffeeMachine.get(0);
        }
        return null;
    }
}
