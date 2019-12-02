package by.pvt.service.coffeemachine.updatedata;

import by.pvt.pojo.CoffeeMachine;

public class CoffeeMachineDataUpdateCmd {

    private CoffeeMachine coffeeMachine;
    private Double waterMl;
    private Double milkMl;
    private Double coffeeBeansGr;
    private int usedCoffeeContainerPortion;

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
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

    public int getUsedCoffeeContainerPortion() {
        return usedCoffeeContainerPortion;
    }

    public void setUsedCoffeeContainerPortion(int usedCoffeeContainerPortion) {
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
    }
}
