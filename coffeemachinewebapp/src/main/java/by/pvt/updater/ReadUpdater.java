package by.pvt.updater;

import by.pvt.event.AppUserRegisteredEvent;
import by.pvt.service.EventSender;
import by.pvt.service.role.AppUserRoleService;
import by.pvt.type.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class ReadUpdater {

    private static Logger log = Logger.getLogger(ReadUpdater.class.getName());

    private static volatile boolean isStarted = false;

    @Autowired
    private ReadUpdaterAsync readUpdaterAsync;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserRoleService appUserRoleService;

    @Autowired
    EventReader eventReader;

    @PostConstruct
    public void updateLastProcessedEventId() throws IOException, InterruptedException {
        if (isStarted) {
            log.info("updateLastProcessedEventId(): Read updater already started. Return...");
            return;
        }
        isStarted = true;
        if (!eventReader.isAggregateExist("00000000-0000-0000-0000-000000000000")) {
            String password = passwordEncoder.encode("admin");
            UUID appUserRoleId = appUserRoleService.getRoleIdByName(RoleName.ADMIN);
            EventSender.sendPostToEventWriter(new AppUserRegisteredEvent(
                    UUID.fromString("00000000-0000-0000-0000-000000000000"),
                    "admin",
                    "admin",
                    "admin@admin.com",
                    password,
                    Set.of(appUserRoleId)
            ));
        }

        readUpdaterAsync.async();
        log.info("updateLastProcessedEventId(): Read updater started!");
    }
}