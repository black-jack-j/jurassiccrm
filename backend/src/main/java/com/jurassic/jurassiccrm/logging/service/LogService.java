package com.jurassic.jurassiccrm.logging.service;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.logging.exceptions.UnauthorisedUserLogsReadOperation;
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
    private final RolesChecker rolesChecker;

    @Autowired
    public LogService(LogRepository logRepository, RolesChecker rolesChecker) {
        this.logRepository = logRepository;
        this.rolesChecker = rolesChecker;
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

    public <T> void logCrudAction(User actor, LogActionType actionType, String objectTypeName, String name) {
        String action = String.format("%s %s with name %s", actionType.getName(), objectTypeName, name);
        logAction(actor, action);
    }

    public void logAddUserAction(User actor, Long groupId, Long userId) {
        String action = String.format("added user with id %d to group with id %d", userId, groupId);
        logAction(actor, action);
    }

    public void logRemoveUserAction(User actor, Long groupId, Long userId) {
        String action = String.format("removed user with id %d to group with id %d", userId, groupId);
        logAction(actor, action);
    }

    public List<LogEntry> getLogs(User requester) {
        if (!rolesChecker.hasAnyRole(requester, Role.ADMIN, Role.SECURITY_READER))
            throw new UnauthorisedUserLogsReadOperation();
        return logRepository.findAll();
    }
}
