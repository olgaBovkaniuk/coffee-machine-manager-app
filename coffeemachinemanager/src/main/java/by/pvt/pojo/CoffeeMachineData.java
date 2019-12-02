package by.pvt.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Logger;

@Entity(name = "coffee_machine_data")
public class CoffeeMachineData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private static Logger log = Logger.getLogger("CoffeeMachineData");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "waterMl")
    private Double waterMl;

    @Column(name = "milkMl")
    private Double milkMl;

    @Column(name = "coffeeBeansGr")
    private Double coffeeBeansGr;

    @Column(name = "usedCoffeeContainerPortion")
    private int usedCoffeeContainerPortion;

    @Column(name = "coffeeMachineImage")
    @Lob
    private byte[] coffeeMachineImage;

    @Column(name = "imageName")
    private String imageName;

    @ManyToOne
    private CoffeeMachine coffeeMachine;

    public CoffeeMachineData() {
    }

    public CoffeeMachineData(Long id, Double waterMl, Double milkMl, Double coffeeBeansGr, int usedCoffeeContainerPortion, CoffeeMachine coffeeMachine) {
        this.id = id;
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansGr = coffeeBeansGr;
        this.usedCoffeeContainerPortion = usedCoffeeContainerPortion;
        this.coffeeMachine = coffeeMachine;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

//    public void getCoffeeMachineId

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
        if (!Arrays.equals(coffeeMachineImage, that.coffeeMachineImage)) return false;
        if (imageName != null ? !imageName.equals(that.imageName) : that.imageName != null) return false;
        return coffeeMachine != null ? coffeeMachine.equals(that.coffeeMachine) : that.coffeeMachine == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (waterMl != null ? waterMl.hashCode() : 0);
        result = 31 * result + (milkMl != null ? milkMl.hashCode() : 0);
        result = 31 * result + (coffeeBeansGr != null ? coffeeBeansGr.hashCode() : 0);
        result = 31 * result + usedCoffeeContainerPortion;
        result = 31 * result + Arrays.hashCode(coffeeMachineImage);
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (coffeeMachine != null ? coffeeMachine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoffeeMachineData{" +
                "id=" + id +
                ", waterMl=" + waterMl +
                ", creamMl=" + milkMl +
                ", coffeeBeansGr=" + coffeeBeansGr +
                ", usedCoffeeContainerPortion=" + usedCoffeeContainerPortion +
                ", coffeeMachineImage=" + Arrays.toString(coffeeMachineImage) +
                ", imageName='" + imageName + '\'' +
                ", coffeeMachine=" + coffeeMachine +
                '}';
    }

}

