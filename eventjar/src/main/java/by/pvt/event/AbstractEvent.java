package by.pvt.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public abstract class AbstractEvent {

    private final Long eventId;
    private final UUID aggregateId;

    public AbstractEvent(UUID aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = null;
    }

    // For deserialization
    public AbstractEvent(UUID aggregateId, Long eventId) {
        this.aggregateId = aggregateId;
        this.eventId = eventId;
    }

    @JsonIgnore
    public UUID getAggregateId() {
        return aggregateId;
    }

    @JsonIgnore
    public Long getEventId() {
        return eventId;
    }
}
