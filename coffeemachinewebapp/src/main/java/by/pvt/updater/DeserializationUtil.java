package by.pvt.updater;

import by.pvt.event.AbstractEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.UUID;

public class DeserializationUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static AbstractEvent deserializeEvent(EventRecord event) {
        String className = event.getEventType();
        Class<?> clazz = Class.forName(className);
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> uuidConstructor = Arrays.stream(constructors)
                .filter(constructor -> constructor.getParameterCount() == 2)
                .filter(constructor -> constructor.getParameterTypes()[0].equals(UUID.class) && constructor.getParameterTypes()[1].equals(Long.class))
                .findFirst()
                .orElseThrow();
        Object abstractEvent = uuidConstructor.newInstance(UUID.fromString(event.getAggregateId()), event.getEventId());
        return (AbstractEvent) objectMapper.readerForUpdating(abstractEvent).readValue(event.getEventData());
    }
}
