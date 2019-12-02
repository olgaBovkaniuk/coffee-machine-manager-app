package by.pvt.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Entity(name = "coffee_machine")
public class CoffeeMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private static Logger log = Logger.getLogger("CoffeeMachineData");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coffeeMachineName")
    private String coffeeMachineName;

    @OneToMany(mappedBy = "coffeeMachine")
    private List<CoffeeMachineData> coffeeMachineDataList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoffeeMachineName() {
        return coffeeMachineName;
    }

    public void setCoffeeMachineName(String coffeeMachineName) {
        this.coffeeMachineName = coffeeMachineName;
    }

    public List<CoffeeMachineData> getCoffeeMachineDataList() {
        return coffeeMachineDataList;
    }

    public void setCoffeeMachineDataList(List<CoffeeMachineData> coffeeMachineDataList) {
        this.coffeeMachineDataList = coffeeMachineDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeMachine that = (CoffeeMachine) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (coffeeMachineName != null ? !coffeeMachineName.equals(that.coffeeMachineName) : that.coffeeMachineName != null)
            return false;
        return coffeeMachineDataList != null ? coffeeMachineDataList.equals(that.coffeeMachineDataList) : that.coffeeMachineDataList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coffeeMachineName != null ? coffeeMachineName.hashCode() : 0);
        result = 31 * result + (coffeeMachineDataList != null ? coffeeMachineDataList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoffeeMachine{" +
                "id=" + id +
                ", coffeeMachineName='" + coffeeMachineName + '\'' +
                ", machineDataList=" + coffeeMachineDataList +
                '}';
    }
}
