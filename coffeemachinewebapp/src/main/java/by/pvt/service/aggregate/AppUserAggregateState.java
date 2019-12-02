package by.pvt.service.aggregate;

import by.pvt.event.AppUserRegisteredEvent;

import java.util.Set;
import java.util.UUID;

public class AppUserAggregateState implements AggregateState{
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<UUID> appUserRoles;

    public void handle(AppUserRegisteredEvent event) {
        this.userId = event.getAggregateId().toString();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.appUserRoles = event.getAppUserRoleIds();
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<UUID> getAppUserRoles() {
        return appUserRoles;
    }
}
