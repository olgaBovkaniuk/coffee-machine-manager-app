package by.pvt.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ReadUpdaterAsync {

    private static Logger log = Logger.getLogger(ReadUpdaterAsync.class.getName());

    @Autowired
    EventReader eventReader;

    @Autowired
    LastProcessedEventUpdater lastEventUpdater;

    @Autowired
    EventHandler eventHandler;

    private static volatile boolean isTerminated = false;

    @PreDestroy
    public void destroy() {
        log.info("Read updater stopped!");
        isTerminated = true;
    }

    @Async
    public void async() {
        log.info("Read updater started async!");
        while (!isTerminated) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            Long lastProcessedEventId = lastEventUpdater.getLastProcessedEvent();

            List<EventRecord> events = eventReader.readEvents(lastProcessedEventId);

            if (events == null || events.isEmpty()) {
                continue;
            }
            Long lastProcessedId = getLastProcessedId(events);

            if (eventHandler.handleEvents(events)) {
                lastEventUpdater.updateLastProcessedEvent(lastProcessedId);
            }
        }
    }

    private Long getLastProcessedId(List<EventRecord> events) {
        return events.get(events.size() - 1).getEventId();
    }
}
