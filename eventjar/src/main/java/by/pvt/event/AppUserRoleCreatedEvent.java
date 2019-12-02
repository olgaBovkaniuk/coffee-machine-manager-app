package by.pvt.event;

import by.pvt.type.RoleName;

import java.util.UUID;

public class AppUserRoleCreatedEvent extends AbstractEvent implements AppUserRoleAggregate {

//    private UUID roleId;
    private RoleName roleName;

    public AppUserRoleCreatedEvent(UUID roleId, RoleName roleName) {
        super(roleId);
        this.roleName = roleName;
    }

    // For deserialization
    public AppUserRoleCreatedEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
    }

//    public UUID getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(UUID roleId) {
//        this.roleId = roleId;
//    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
