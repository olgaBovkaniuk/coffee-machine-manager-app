package by.pvt.event;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineRegisteredEvent extends AbstractEvent implements CoffeeMachineAggregate {

    private String coffeeMachineName;
    private CoffeeMachineStatus status;
    private String imageName;

    public CoffeeMachineRegisteredEvent(UUID coffeeMachineId, String coffeeMachineName, CoffeeMachineStatus status, String imageName) {
        super(coffeeMachineId);
        this.coffeeMachineName = coffeeMachineName;
        this.status = status;
        this.imageName = imageName;
    }

    // For deserialization
    public CoffeeMachineRegisteredEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
    }

    public String getCoffeeMachineName() {
        return coffeeMachineName;
    }

    public void setCoffeeMachineName(String coffeeMachineName) {
        this.coffeeMachineName = coffeeMachineName;
    }

    public CoffeeMachineStatus getStatus() {
        return status;
    }

    public void setStatus(CoffeeMachineStatus status) {
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
