package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.logging.exceptions.UnauthorisedUserLogsReadOperation;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.repository.LogRepository;
import com.jurassic.jurassiccrm.logging.service.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class LogServiceTest {
    private final LogService logService;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private User admin = new User();
    private User security = new User();
    private User simpleUser = new User();

    @Autowired
    public LogServiceTest(LogRepository logRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.logService = new LogService(logRepository, new RolesChecker(userRepository));
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @BeforeEach
    void init(@Autowired GroupRepository groupRepository, @Autowired UserRepository userRepository) {
        User adminUser = userRepository.save(createUser("admin"));
        Group adminGroup = new Group("admin group");
        adminGroup.addUser(adminUser);
        adminGroup.addRole(Role.ADMIN);
        groupRepository.save(adminGroup);
        admin = adminUser;

        User securityUser = userRepository.save(createUser("security"));
        Group securityGroup = new Group("security group");
        securityGroup.addUser(securityUser);
        securityGroup.addRole(Role.SECURITY_READER);
        securityGroup.addRole(Role.SECURITY_WRITER);
        groupRepository.save(securityGroup);
        security = securityUser;

        simpleUser = userRepository.save(createUser("simple user"));
    }

    @Test
    void allowAdminToReadLogs() {
        Assertions.assertDoesNotThrow(() -> logService.getLogs(admin));
    }

    @Test
    void allowSecurityUserToReadLogs() {
        Assertions.assertDoesNotThrow(() -> logService.getLogs(security));
    }

    @Test
    void forbidSimpleUserToReadLogs() {
        Assertions.assertThrows(UnauthorisedUserLogsReadOperation.class, () -> logService.getLogs(simpleUser));
    }

    @Test
    void returnEmptyLogsIfNothingWasLogged() {
        Assertions.assertEquals(0, logService.getLogs(admin).size());
    }

    @Test
    void returnSavedLogEntries() {
        logService.logAction(new User("username"), "");
        Assertions.assertEquals(1, logService.getLogs(admin).size());
    }

    @Test
    void returnMultipleSavedLogEntries() {
        logService.logAction(new User("username"), "");
        logService.logAction(new User("username"), "");
        Assertions.assertEquals(2, logService.getLogs(admin).size());
    }

    @Test
    void setUsernameToLogEntry() {
        logService.logAction(new User("username"), "");
        Assertions.assertEquals("username", logService.getLogs(admin).get(0).getUsername());
    }

    @Test
    void setActionToLogEntry() {
        logService.logAction(new User("username"), "action");
        Assertions.assertEquals("action", logService.getLogs(admin).get(0).getAction());
    }

    @Test
    void setTimestampToLogEntry() {
        LocalDateTime timeBeforeLog = LocalDateTime.now().minusSeconds(1);
        logService.logAction(new User("username"), "");
        Assertions.assertTrue(timeBeforeLog.isBefore(logService.getLogs(admin).get(0).getTimestamp()));
    }

    @Test
    void setActionForLogCrudAction() {
        logService.logCrudAction(new User("username"), LogActionType.CREATE, LogService.class, "log service");
        String generatedAction = logService.getLogs(admin).get(0).getAction();
        Assertions.assertTrue(generatedAction.contains(LogActionType.CREATE.getName()));
        Assertions.assertTrue(generatedAction.contains(LogService.class.getSimpleName()));
        Assertions.assertTrue(generatedAction.contains("log service"));
    }

    private User createUser(String username) {
        User user = new User(username);
        user.setPassword("");
        return user;
    }
}
