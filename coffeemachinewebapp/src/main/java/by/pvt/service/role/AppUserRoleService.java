package by.pvt.service.role;

import by.pvt.event.AppUserRoleCreatedEvent;
import by.pvt.pojo.AppUserRole;
import by.pvt.repository.AppRoleRepository;
import by.pvt.service.EventSender;
import by.pvt.type.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AppUserRoleService {

    private static Logger log = Logger.getLogger("AppUserRoleService");

    @Autowired
    AppRoleRepository roleRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public UUID getRoleIdByName(RoleName roleName) {
        AppUserRole appUserRole = roleRepository.findByRoleName(roleName);
        if (appUserRole == null) {
            appUserRole = new AppUserRole();
            appUserRole.setRoleName(roleName);
            appUserRole.setRoleId((UUID.randomUUID()).toString());
            createNewAppUserRoleEvent(appUserRole);
        }
        return UUID.fromString(appUserRole.getRoleId());
    }

    private void createNewAppUserRoleEvent(AppUserRole appUserRole) {
        AppUserRoleCreatedEvent appUserRoleCreatedEvent = new AppUserRoleCreatedEvent(
                UUID.fromString(appUserRole.getRoleId()),
                appUserRole.getRoleName()
        );
        try {
            EventSender.sendPostToEventWriter(appUserRoleCreatedEvent);
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }
    }
}
