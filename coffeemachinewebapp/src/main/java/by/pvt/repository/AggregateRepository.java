package by.pvt.repository;

import by.pvt.updater.EventRecord;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public class AggregateRepository {

    private static Logger log = Logger.getLogger("AggregateRepository is calling..");

    @Autowired
    @Qualifier("writeLocalSessionFactoryBean")
    SessionFactory writeLocalSessionFactoryBean;

    public List<EventRecord> findEventsByUuid(UUID aggregateId) {
        return writeLocalSessionFactoryBean.getCurrentSession()
                .createQuery("from Event where aggregateId =:aggregateId", EventRecord.class)
                .setParameter("aggregateId", aggregateId.toString())
                .list();
    }
}
