package by.pvt.updater;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventReader {

    @Autowired
    @Qualifier("writeLocalSessionFactoryBean")
    SessionFactory writeLocalSessionFactoryBean;

    @Transactional(transactionManager = "writeTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public List<EventRecord> readEvents(Long lastProcessedEventId) {
        return writeLocalSessionFactoryBean
                .getCurrentSession()
                .createQuery("from Event where eventId > :param1 order by eventId", EventRecord.class)
                .setParameter("param1", lastProcessedEventId)
                .setMaxResults(10)
                .list();
    }

    @Transactional(transactionManager = "writeTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public boolean isAggregateExist(String aggregateId) {
        List<EventRecord> event = writeLocalSessionFactoryBean
                .getCurrentSession()
                .createQuery("from Event where aggregateId = :param1", EventRecord.class)
                .setParameter("param1", aggregateId)
                .setMaxResults(1)
                .list();
        return event != null && !event.isEmpty();
    }

}
