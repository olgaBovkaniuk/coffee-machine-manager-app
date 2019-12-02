package by.pvt.dto;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineDto {
    private Long order;
    private UUID coffeeMachineId;
    private String coffeeMachineName;
    private CoffeeMachineStatus status;
    private String imageName;

    public CoffeeMachineDto(Long order, UUID coffeeMachineId, String coffeeMachineName, CoffeeMachineStatus status, String imageName) {
        this.order = order;
        this.coffeeMachineId = coffeeMachineId;
        this.coffeeMachineName = coffeeMachineName;
        this.status = status;
        this.imageName = imageName;
    }

    public Long getOrder() {
        return order;
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

    public String getImageName() {
        return imageName;
    }
}
