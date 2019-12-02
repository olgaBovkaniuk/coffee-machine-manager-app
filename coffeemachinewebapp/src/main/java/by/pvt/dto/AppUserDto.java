package by.pvt.dto;

import by.pvt.pojo.AppUserRole;

import java.util.Set;

public class AppUserDto {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<AppUserRole> appUserRoles;

    public AppUserDto(String userId, String firstName, String lastName, String email, String password, Set<AppUserRole> appUserRoles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRoles = appUserRoles;
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

    public String getPassword() {
        return password;
    }

    public Set<AppUserRole> getAppUserRoles() {
        return appUserRoles;
    }
}
