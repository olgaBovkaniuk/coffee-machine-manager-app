package by.pvt.repository;

import by.pvt.pojo.AppUserRole;
import by.pvt.type.RoleName;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class AppRoleRepository {

    private static Logger log = Logger.getLogger("AppRoleRepository");

    @Autowired
    SessionFactory sessionFactory;

    public AppUserRole findByRoleName(RoleName roleName) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from AppUserRole where roleName like :roleParam", AppUserRole.class)
                    .setParameter("roleParam", roleName)
                    .getSingleResult();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }

    }

    public void save(AppUserRole userRole) {
        sessionFactory.getCurrentSession()
                .save(userRole);
    }
}
