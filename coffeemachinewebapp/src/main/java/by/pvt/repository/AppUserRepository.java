package by.pvt.repository;

import by.pvt.pojo.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class AppUserRepository {

    private static Logger log = Logger.getLogger("AppUserRepository");

    @Autowired
    private SessionFactory sessionFactory;

    public AppUser findUserByEmail(String email) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from AppUser where email like: emailParam", AppUser.class)
                    .setParameter("emailParam", email)
                    .getSingleResult();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    public void save(AppUser appUser) {
        log.info("Save user = " + appUser);
        sessionFactory.getCurrentSession()
                .save(appUser);
    }
}
