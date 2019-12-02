package by.pvt.dto;

import java.util.UUID;

public class CoffeeMachineDataDto {
    private Long id;
    private Double waterMl;
    private Double milkMl;
    private Double coffeeBeansGr;
    private int usedCoffeeContainerPortion;
    private UUID coffeeMachineId;

    public CoffeeMachineDataDto(Long id, Double waterMl, Double milkMl, Double coffeeBeansGr, int usedCoffeeContainerPortion, UUID coffeeMachineId) {
        this.id = id;
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansGr = coffeeBeansGr;
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
        this.coffeeMachineId = coffeeMachineId;
    }

    public Long getId() {
        return id;
    }

    public Double getWaterMl() {
        return waterMl;
    }

    public Double getMilkMl() {
        return milkMl;
    }

    public Double getCoffeeBeansGr() {
        return coffeeBeansGr;
    }

    public int getUsedCoffeeContainerPortion() {
        return usedCoffeeContainerPortion;
    }

    public UUID getCoffeeMachineId() {
        return coffeeMachineId;
    }
}
