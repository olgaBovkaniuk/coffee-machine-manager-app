package by.pvt.event;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

@JsonSerialize
public class CoffeeMachineDeletedEvent extends AbstractEvent implements CoffeeMachineAggregate {

    public CoffeeMachineDeletedEvent(UUID coffeeMachineId) {
        super(coffeeMachineId);
    }

    // For deserialization
    public CoffeeMachineDeletedEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
    }
}
