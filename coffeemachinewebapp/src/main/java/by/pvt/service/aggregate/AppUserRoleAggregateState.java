package by.pvt.service.aggregate;

import by.pvt.event.AppUserRoleCreatedEvent;
import by.pvt.type.RoleName;

public class AppUserRoleAggregateState implements AggregateState{

    private RoleName roleName;

    public void handle(AppUserRoleCreatedEvent event) {
        this.roleName = event.getRoleName();
    }

    public RoleName getRoleName() {
        return roleName;
    }
}
