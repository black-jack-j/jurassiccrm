package com.jurassic.jurassiccrm.logging.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.document.controller.DocumentController;
import com.jurassic.jurassiccrm.logging.exceptions.UnauthorisedUserLogsReadOperation;
import com.jurassic.jurassiccrm.logging.model.LogEntry;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "logs")
public class LogController {

    private final LogService logService;
    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    @Operation(operationId = "getLogs")
    public ResponseEntity<List<LogEntry>> getLogs(@Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            List<LogEntry> logs = logService.getLogs(userDetails.getUserInfo()).stream()
                    .sorted(Comparator.comparing(LogEntry::getTimestamp)).collect(Collectors.toList());
            return ResponseEntity.ok(logs);
        } catch (UnauthorisedUserLogsReadOperation e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
