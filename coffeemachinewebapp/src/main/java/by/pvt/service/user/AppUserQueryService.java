package by.pvt.service.user;

import by.pvt.pojo.AppUser;
import by.pvt.repository.AppRoleRepository;
import by.pvt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
public class AppUserQueryService {

    private static Logger log = Logger.getLogger("UserService");

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean isUserWithEmailExist(String email) {
        AppUser userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail != null) {
            return true;
        }
        return false;
    }
}
