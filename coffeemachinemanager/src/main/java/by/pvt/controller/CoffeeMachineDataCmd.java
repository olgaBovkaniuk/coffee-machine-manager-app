package by.pvt.controller;

import java.util.UUID;

public class CoffeeMachineDataCmd {

    private UUID coffeeMachineId;
    private Double waterMl;
    private Double milkMl;
    private Double coffeeBeansGr;
    private Integer usedCoffeeContainerPortion;

    public UUID getCoffeeMachineId() {
        return coffeeMachineId;
    }

    public void setCoffeeMachineId(UUID coffeeMachineId) {
        this.coffeeMachineId = coffeeMachineId;
    }

    public Double getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(Double waterMl) {
        this.waterMl = waterMl;
    }

    public Double getMilkMl() {
        return milkMl;
    }

    public void setMilkMl(Double milkMl) {
        this.milkMl = milkMl;
    }

    public Double getCoffeeBeansGr() {
        return coffeeBeansGr;
    }

    public void setCoffeeBeansGr(Double coffeeBeansGr) {
        this.coffeeBeansGr = coffeeBeansGr;
    }

    public Integer getUsedCoffeeContainerPortion() {
        return usedCoffeeContainerPortion;
    }

    public void setUsedCoffeeContainerPortion(Integer usedCoffeeContainerPortion) {
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
    }
}
