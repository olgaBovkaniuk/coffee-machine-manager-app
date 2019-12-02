package by.pvt.updater;

import by.pvt.pojo.LastProcessedEvent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LastProcessedEventUpdater {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(value = "readTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public void updateLastProcessedEvent(Long lastProcessedId) {
        LastProcessedEvent lastProcessedEvent = new LastProcessedEvent();
        lastProcessedEvent.setId(1L);
        lastProcessedEvent.setEventId(lastProcessedId);
        sessionFactory.getCurrentSession()
                .saveOrUpdate(lastProcessedEvent);
    }

    @Transactional(value = "readTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public Long getLastProcessedEvent() {
        List<LastProcessedEvent> lastProcessedEvents = sessionFactory.getCurrentSession()
                .createQuery("from last_processed_event", LastProcessedEvent.class).list();

        return lastProcessedEvents.isEmpty() ? 0 : lastProcessedEvents.get(0).getEventId();
    }
}
