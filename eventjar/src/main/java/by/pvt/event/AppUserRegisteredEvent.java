package by.pvt.event;

import java.util.Set;
import java.util.UUID;

public class AppUserRegisteredEvent extends AbstractEvent implements AppUserAggregate {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<UUID> appUserRoleIds;

    public AppUserRegisteredEvent(UUID userId, String firstName, String lastName, String email, String password, Set<UUID> appUserRoleIds) {
        super(userId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRoleIds = appUserRoleIds;
    }

    // For deserialization
    public AppUserRegisteredEvent(UUID aggregateId, Long eventId) {
        super(aggregateId, eventId);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UUID> getAppUserRoleIds() {
        return appUserRoleIds;
    }

    public void setAppUserRoleIds(Set<UUID> appUserRoleIds) {
        this.appUserRoleIds = appUserRoleIds;
    }
}
