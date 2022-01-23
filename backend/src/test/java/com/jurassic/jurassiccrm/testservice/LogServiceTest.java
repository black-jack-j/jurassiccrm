package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.logging.repository.LogRepository;
import com.jurassic.jurassiccrm.logging.service.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class LogServiceTest {
    private final LogService logService;

    @Autowired
    public LogServiceTest(LogRepository logRepository) {
        this.logService = new LogService(logRepository);
    }

    @Test
    void returnEmptyLogsIfNothingWasLogged() {
        Assertions.assertEquals(0, logService.getLogs().size());
    }

    @Test
    void returnSavedLogEntries() {
        logService.logAction(new User("username"), "");
        Assertions.assertEquals(1, logService.getLogs().size());
    }

    @Test
    void returnMultipleSavedLogEntries() {
        logService.logAction(new User("username"), "");
        logService.logAction(new User("username"), "");
        Assertions.assertEquals(2, logService.getLogs().size());
    }

    @Test
    void setUsernameToLogEntry() {
        logService.logAction(new User("username"), "");
        Assertions.assertEquals("username", logService.getLogs().get(0).getUsername());
    }

    @Test
    void setActionToLogEntry() {
        logService.logAction(new User("username"), "action");
        Assertions.assertEquals("action", logService.getLogs().get(0).getAction());
    }

    @Test
    void setTimestampToLogEntry() {
        LocalDateTime timeBeforeLog = LocalDateTime.now().minusSeconds(1);
        logService.logAction(new User("username"), "");
        Assertions.assertTrue(timeBeforeLog.isBefore(logService.getLogs().get(0).getTimestamp()));
    }
}
