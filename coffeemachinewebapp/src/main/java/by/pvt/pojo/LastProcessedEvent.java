package by.pvt.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "last_processed_event")
@Table(name = "last_processed_event", catalog = "coffee_machine_read", schema = "coffee_machine_read")
public class LastProcessedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Long eventId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
