package by.pvt.service.aggregate;

import by.pvt.dto.CoffeeMachineDataDto;
import by.pvt.event.*;
import by.pvt.pojo.CoffeeMachineData;
import by.pvt.type.CoffeeMachineStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoffeeMachineAggregateState implements AggregateState{

    private UUID coffeeMachineId;
    private String coffeeMachineName;
    private CoffeeMachineStatus status;
    private List<CoffeeMachineDataDto> coffeeMachineData = new ArrayList<>();

    public void handle(CoffeeMachineRegisteredEvent event) {
        this.coffeeMachineId = event.getAggregateId();
        this.coffeeMachineName = event.getCoffeeMachineName();
        this.status = event.getStatus();
    }

    public void handle(CoffeeMachineStartedEvent event) {
        this.status = event.getStatus();
    }

    public void handle(CoffeeMachineStoppedEvent event) {
        this.status = event.getStatus();
    }

    public void handle(CoffeeMachineDataUpdatedEvent event) {
        CoffeeMachineDataDto coffeeMachineDataDto = new CoffeeMachineDataDto(
                event.getEventId(),
                event.getWaterMl(),
                event.getMilkMl(),
                event.getCoffeeBeansGr(),
                event.getUsedCoffeeContainerPortion(),
                event.getAggregateId()
        );
        this.coffeeMachineData.add(coffeeMachineDataDto);
    }

    public void handle(CoffeeMachineDeletedEvent event) {
        this.coffeeMachineName = null;
        this.status = CoffeeMachineStatus.DELETED;
        this.coffeeMachineData = null;
    }

    public UUID getCoffeeMachineId() {
        return coffeeMachineId;
    }

    public String getCoffeeMachineName() {
        return coffeeMachineName;
    }

    public CoffeeMachineStatus getStatus() {
        return status;
    }

    public List<CoffeeMachineDataDto> getCoffeeMachineData() {
        return coffeeMachineData;
    }
}
