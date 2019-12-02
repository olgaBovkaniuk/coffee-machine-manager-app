package by.pvt.event;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineStoppedEvent extends AbstractEvent implements CoffeeMachineAggregate {

    private CoffeeMachineStatus status;
    private Long processId;

    public CoffeeMachineStoppedEvent(UUID coffeeMachineId, CoffeeMachineStatus status, Long processId) {
        super(coffeeMachineId);
        this.status = status;
        this.processId = processId;
    }

    // For deserialization
    public CoffeeMachineStoppedEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public CoffeeMachineStatus getStatus() {
        return status;
    }

    public void setStatus(CoffeeMachineStatus status) {
        this.status = status;
    }
}
