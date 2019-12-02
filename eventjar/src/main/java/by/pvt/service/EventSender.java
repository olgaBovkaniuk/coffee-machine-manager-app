package by.pvt.service;

import by.pvt.event.AbstractEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class EventSender {

    private static final String EVENT_WRITER_URL = "http://localhost:8088/event-writer";
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static void sendPostToEventWriter(AbstractEvent event) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventData = objectMapper.writeValueAsString(event);
        String eventDate = DATE_TIME_FORMATTER.format(new Date());
        EventCmd eventCmd = new EventCmd(event.getAggregateId(), event.getClass().getName(), eventData, eventDate);
        String body = objectMapper.writeValueAsString(eventCmd);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(EVENT_WRITER_URL))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status Code=" + response.statusCode());
        System.out.println("Response Body=" + response.body());
    }

    private static class EventCmd {
        private final UUID aggregateId;
        private final String eventType;
        private final String eventData;
        private final String eventDate;

        public EventCmd(UUID aggregateId, String eventType, String eventData, String eventDate) {
            this.aggregateId = aggregateId;
            this.eventType = eventType;
            this.eventData = eventData;
            this.eventDate = eventDate;
        }

        public UUID getAggregateId() {
            return aggregateId;
        }

        public String getEventType() {
            return eventType;
        }

        public String getEventData() {
            return eventData;
        }

        public String getEventDate() {
            return eventDate;
        }
    }
}

