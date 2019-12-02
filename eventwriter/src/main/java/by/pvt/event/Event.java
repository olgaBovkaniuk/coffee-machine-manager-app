package by.pvt.event;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(name = "aggregateId")
    private String aggregateId;

    @Column(name = "eventData")
    private String eventData;

    @Column(name = "eventType")
    private String eventType;

    @Column(name = "eventDate")
    private String eventDate;

    public Event() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
