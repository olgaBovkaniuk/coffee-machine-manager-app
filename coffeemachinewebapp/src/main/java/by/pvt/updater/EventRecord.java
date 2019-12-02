package by.pvt.updater;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Event")
@Table(name = "Event", catalog = "coffee_machine_write", schema = "coffee_machine_write")
public class EventRecord implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public EventRecord() {
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

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", aggregateId='" + aggregateId + '\'' +
                ", eventData='" + eventData + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
