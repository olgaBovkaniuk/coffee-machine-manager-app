package by.pvt.updater;

import by.pvt.event.AppUserRegisteredEvent;
import by.pvt.event.AppUserRoleCreatedEvent;
import by.pvt.pojo.AppUser;
import by.pvt.pojo.AppUserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class AppUserEventHandler {

    @Autowired
    SessionFactory sessionFactory;

    public void register(AppUserRegisteredEvent event) {

        AppUser appUser = new AppUser();
        appUser.setUserId((event.getAggregateId()).toString());
        appUser.setFirstName(event.getFirstName());
        appUser.setLastName(event.getLastName());
        appUser.setEmail(event.getEmail());
        appUser.setPassword(event.getPassword());
        Set<UUID> appUserRoleIds = event.getAppUserRoleIds();
        Set<AppUserRole> appUserRoles = new HashSet<>();
        for (UUID appUserRoleId : appUserRoleIds) {
            AppUserRole appUserRole = sessionFactory.getCurrentSession()
                    .createQuery("from AppUserRole where roleId = :roleIdParam", AppUserRole.class)
                    .setParameter("roleIdParam", appUserRoleId.toString())
                    .getSingleResult();
            appUserRoles.add(appUserRole);
        }
        appUser.setAppUserRoles(appUserRoles);
        sessionFactory.getCurrentSession()
                .save(appUser);
    }

    public void createRole(AppUserRoleCreatedEvent event) {

        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setRoleId((event.getAggregateId()).toString());
        appUserRole.setRoleName(event.getRoleName());
        sessionFactory.getCurrentSession()
                .save(appUserRole);
    }
}
