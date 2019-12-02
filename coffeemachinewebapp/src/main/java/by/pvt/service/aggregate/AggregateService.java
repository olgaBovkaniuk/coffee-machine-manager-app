package by.pvt.service.aggregate;

import by.pvt.event.AbstractEvent;
import by.pvt.event.AppUserAggregate;
import by.pvt.event.AppUserRoleAggregate;
import by.pvt.event.CoffeeMachineAggregate;
import by.pvt.repository.AggregateRepository;
import by.pvt.updater.DeserializationUtil;
import by.pvt.updater.EventRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AggregateService {

    private Logger log = Logger.getLogger("AggregateService is calling..");

    @Autowired
    AggregateRepository aggregateRepository;

    @Transactional(transactionManager = "writeTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public List<EventRecord> findEventsByUuid(UUID aggregateId) {
        return aggregateRepository.findEventsByUuid(aggregateId);
    }

    @SneakyThrows
    public AggregateState getLastAggregateState(List<EventRecord> eventRecords) {
        AggregateState aggregateState;
        List<AbstractEvent> events = eventRecords.stream()
                .map(DeserializationUtil::deserializeEvent)
                .collect(Collectors.toList());

        AbstractEvent firstEvent = events.get(0);
        if (firstEvent instanceof AppUserAggregate) {
            aggregateState = new AppUserAggregateState();
        } else if (firstEvent instanceof AppUserRoleAggregate) {
            aggregateState = new AppUserRoleAggregateState();
        } else if (firstEvent instanceof CoffeeMachineAggregate) {
            aggregateState = new CoffeeMachineAggregateState();
        } else {
            throw new UnsupportedOperationException("Aggregate state is not found for event: " + firstEvent);
        }
        for (AbstractEvent event : events) {
            Method handle = aggregateState.getClass().getMethod("handle", event.getClass());
            handle.invoke(aggregateState, event);
        }
        return aggregateState;
    }

    @SneakyThrows
    public AggregateStateDto getAggregateState(List<EventRecord> eventRecords) {
        AggregateState aggregateState = getLastAggregateState(eventRecords);
        ObjectMapper objectMapper = new ObjectMapper();
        String aggregateStateData = objectMapper.writeValueAsString(aggregateState);
        return new AggregateStateDto(aggregateState.getClass().getName(), aggregateStateData);
    }
}
