package by.pvt.service.coffeemachine.updatedata;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineUpdateCmd {

    private UUID coffeeMachineId;
    private String coffeeMachineName;
    private CoffeeMachineStatus status;

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

}
