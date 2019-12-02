package by.pvt.service.aggregate;

public class AggregateStateDto {

    private String aggregateStateType;
    private String aggregateStateData;

    public AggregateStateDto(String aggregateStateType, String aggregateStateData) {
        this.aggregateStateType = aggregateStateType;
        this.aggregateStateData = aggregateStateData;
    }

    public String getAggregateStateType() {
        return aggregateStateType;
    }

    public String getAggregateStateData() {
        return aggregateStateData;
    }

    public void setAggregateStateType(String aggregateStateType) {
        this.aggregateStateType = aggregateStateType;
    }

    public void setAggregateStateData(String aggregateStateData) {
        this.aggregateStateData = aggregateStateData;
    }
}
