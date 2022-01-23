package com.jurassic.jurassiccrm.logging.service;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.model.LogEntry;
import com.jurassic.jurassiccrm.logging.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void logAction(User actor, String action) {
        LogEntry logEntry = new LogEntry();
        logEntry.setUsername(actor.getUsername());
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setAction(action);
        logRepository.save(logEntry);
    }

    public <T> void logCrudAction(User actor, LogActionType actionType, Class<T> type, String name) {
        String action = String.format("%s %s with name %s", actionType.getName(), type.getSimpleName(), name);
        logAction(actor, action);
    }

    public List<LogEntry> getLogs() {
        return logRepository.findAll();
    }
}
