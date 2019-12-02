package by.pvt.service.coffeemachine.register;

import by.pvt.type.CoffeeMachineStatus;

import java.util.UUID;

public class CoffeeMachineRegisterCmd {

    private String coffeeMachineId;
    private String coffeeMachineName;
    private CoffeeMachineStatus status;
    private byte[] coffeeMachineImage;
    private String imageName;

    public String getCoffeeMachineId() {
        return coffeeMachineId;
    }

    public void setCoffeeMachineId(String coffeeMachineId) {
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

    public byte[] getCoffeeMachineImage() {
        return coffeeMachineImage;
    }

    public void setCoffeeMachineImage(byte[] coffeeMachineImage) {
        this.coffeeMachineImage = coffeeMachineImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
