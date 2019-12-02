package by.pvt.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Logger;

@Entity(name = "coffee_machine_data")
public class CoffeeMachineData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private static Logger log = Logger.getLogger("CoffeeMachineData");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double waterMl;

    @Column
    private Double milkMl;

    @Column
    private Double coffeeBeansGr;

    @Column
    private int usedCoffeeContainerPortion;

    @ManyToOne
    @JoinColumn(name = "coffeeMachineId")
    private CoffeeMachine coffeeMachine;

    public CoffeeMachineData() {
    }

    public CoffeeMachineData(Double waterMl, Double milkMl, Double coffeeBeansGr, int usedCoffeeContainerPortion, CoffeeMachine coffeeMachine) {
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansGr = coffeeBeansGr;
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
        this.coffeeMachine = coffeeMachine;
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

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWaterMl(Double waterMl) {
        this.waterMl = waterMl;
    }

    public void setMilkMl(Double milkMl) {
        this.milkMl = milkMl;
    }

    public void setCoffeeBeansGr(Double coffeeBeansGr) {
        this.coffeeBeansGr = coffeeBeansGr;
    }

    public void setUsedCoffeeContainerPortion(int usedCoffeeContainerPortion) {
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public UUID getCoffeeMachineId() {
        return UUID.fromString(coffeeMachine.getCoffeeMachineId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeMachineData that = (CoffeeMachineData) o;

        if (usedCoffeeContainerPortion != that.usedCoffeeContainerPortion) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (waterMl != null ? !waterMl.equals(that.waterMl) : that.waterMl != null) return false;
        if (milkMl != null ? !milkMl.equals(that.milkMl) : that.milkMl != null) return false;
        if (coffeeBeansGr != null ? !coffeeBeansGr.equals(that.coffeeBeansGr) : that.coffeeBeansGr != null)
            return false;
        return coffeeMachine != null ? coffeeMachine.equals(that.coffeeMachine) : that.coffeeMachine == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (waterMl != null ? waterMl.hashCode() : 0);
        result = 31 * result + (milkMl != null ? milkMl.hashCode() : 0);
        result = 31 * result + (coffeeBeansGr != null ? coffeeBeansGr.hashCode() : 0);
        result = 31 * result + usedCoffeeContainerPortion;
        result = 31 * result + (coffeeMachine != null ? coffeeMachine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoffeeMachineData{" +
                "id=" + id +
                ", waterMl=" + waterMl +
                ", milkMl=" + milkMl +
                ", coffeeBeansGr=" + coffeeBeansGr +
                ", usedCoffeeContainerPortion=" + usedCoffeeContainerPortion +
                ", coffeeMachine=" + coffeeMachine +
                '}';
    }
}
