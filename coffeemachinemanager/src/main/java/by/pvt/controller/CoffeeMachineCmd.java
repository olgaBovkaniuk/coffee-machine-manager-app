package by.pvt.controller;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineCmd {
    private UUID coffeeMachineId;
    private String coffeeMachineName;
    private CoffeeMachineStatus status;
    private String imageName;

    public UUID getCoffeeMachineId() {
        return coffeeMachineId;
    }

    public void setCoffeeMachineId(UUID coffeeMachineId) {
        this.coffeeMachineId = coffeeMachineId;
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
