package by.pvt.pojo;

import by.pvt.type.CoffeeMachineStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Entity(name = "coffee_machine")
@Table(name = "coffee_machine", catalog = "coffee_machine_read", schema = "coffee_machine_read")
public class CoffeeMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger("CoffeeMachine");

    @Id
    @Column(name = "coffeeMachineId", nullable = false, columnDefinition = "char(36)")
    private String coffeeMachineId;

    @Column
    private Long eventId;

    @Column
    private String coffeeMachineName;

    @Column
    private String imageName;

    @Column
    @Enumerated(EnumType.STRING)
    private CoffeeMachineStatus status;

    @Column
    private Long processId;

    @OneToMany(mappedBy = "coffeeMachine", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CoffeeMachineData> data;

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

    public List<CoffeeMachineData> getData() {
        return data;
    }

    public void setData(List<CoffeeMachineData> data) {
        this.data = data;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public CoffeeMachineStatus getStatus() {
        return status;
    }

    public void setStatus(CoffeeMachineStatus status) {
        this.status = status;
    }

    public Long getProcessId() {
        return processId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeMachine that = (CoffeeMachine) o;

        return coffeeMachineId != null ? coffeeMachineId.equals(that.coffeeMachineId) : that.coffeeMachineId == null;
    }

    @Override
    public int hashCode() {
        return coffeeMachineId != null ? coffeeMachineId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CoffeeMachine{" +
                " coffeeMachineId=" + coffeeMachineId +
                ", coffeeMachineName='" + coffeeMachineName + '\'' +
                ", data=" + data +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
