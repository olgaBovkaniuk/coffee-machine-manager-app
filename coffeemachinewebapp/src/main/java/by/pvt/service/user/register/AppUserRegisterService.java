package by.pvt.service.user.register;

import by.pvt.event.AppUserRegisteredEvent;
import by.pvt.repository.AppUserRepository;
import by.pvt.service.EventSender;
import by.pvt.service.role.AppUserRoleService;
import by.pvt.type.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AppUserRegisterService {

    private static Logger log = Logger.getLogger("AppUserRegisterService");

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserRoleService appUserRoleService;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean saveUser(AppUserRegisterCmd appUserCmd) {
        log.info("Saving user = " + appUserCmd);
        if (appUserCmd == null || appUserCmd.getFirstName().isEmpty() || appUserCmd.getLastName().isEmpty() ||
                appUserCmd.getEmail().isEmpty() || appUserCmd.getPassword().isEmpty() ||
                userRepository.findUserByEmail(appUserCmd.getEmail()) != null) {
            return false;
        }
        UUID appUserRoleId = appUserRoleService.getRoleIdByName(RoleName.USER);
        String encodedPassword = passwordEncoder.encode(appUserCmd.getPassword());
        log.info("Encoded password: " + encodedPassword);
        appUserCmd.setPassword(encodedPassword);
        appUserCmd.setUserId(UUID.randomUUID());
        createNewUserEvent(appUserCmd, appUserRoleId);
        return true;
    }

    private void createNewUserEvent(AppUserRegisterCmd appUserCmd, UUID appUserRoleId) {
        AppUserRegisteredEvent userRegisteredEvent = new AppUserRegisteredEvent(
                appUserCmd.getUserId(),
                appUserCmd.getFirstName(),
                appUserCmd.getLastName(),
                appUserCmd.getEmail(),
                appUserCmd.getPassword(),
                Set.of(appUserRoleId)
        );
        try {
            EventSender.sendPostToEventWriter(userRegisteredEvent);
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }
    }
}
