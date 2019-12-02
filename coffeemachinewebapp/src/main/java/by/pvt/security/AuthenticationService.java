package by.pvt.security;

import by.pvt.pojo.AppUser;
import by.pvt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {

    private static Logger log = Logger.getLogger("AuthenticationService");

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findUserByEmail(userName);
        log.info("Searching for userName = " + userName + " found: " + appUser);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User(
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getAppUserRoles().stream()
                        .map(appUserRole -> new SimpleGrantedAuthority("ROLE_" + appUserRole.getRoleName().name()))
                        .collect(Collectors.toList())
        );
        log.info("Returned user = " + user);
        return user;
    }
}
