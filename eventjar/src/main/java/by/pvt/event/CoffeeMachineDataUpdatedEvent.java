package by.pvt.event;

import java.util.UUID;

public class CoffeeMachineDataUpdatedEvent extends AbstractEvent implements CoffeeMachineAggregate {

    private Double waterMl;
    private Double milkMl;
    private Double coffeeBeansGr;
    private Integer usedCoffeeContainerPortion;

    public CoffeeMachineDataUpdatedEvent(UUID coffeeMachineId, Double waterMl, Double milkMl, Double coffeeBeansGr, Integer usedCoffeeContainerPortion) {
        super(coffeeMachineId);
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansGr = coffeeBeansGr;
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
    }

    // For deserialization
    public CoffeeMachineDataUpdatedEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
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
