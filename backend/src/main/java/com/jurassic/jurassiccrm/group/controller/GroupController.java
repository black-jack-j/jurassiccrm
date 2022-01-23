package com.jurassic.jurassiccrm.group.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.accesscontroll.service.UnauthorisedGroupOperationException;
import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import com.jurassic.jurassiccrm.group.dto.GroupInputTO;
import com.jurassic.jurassiccrm.group.dto.GroupOutputTO;
import com.jurassic.jurassiccrm.group.dto.UserIdInputTO;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Tag(name = "group")
@RequestMapping("/api/group")
public class GroupController {

    Logger log = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;
    private final LogService logService;

    @Autowired
    public GroupController(GroupService groupService, LogService logService) {
        this.groupService = groupService;
        this.logService = logService;
    }

    @GetMapping(value = "/user")
    @Operation(operationId = "getUsers")
    public ResponseEntity<List<UserOutputTO>> getAvailableUsers() {
        val dtoList = groupService.getAvailableUsers().stream().map(UserOutputTO::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping(value = "/{groupId}/user")
    @Operation(operationId = "addUser")
    public ResponseEntity<String> addUser(@PathVariable Long groupId,
                                          @RequestBody @Valid UserIdInputTO userIdTo,
                                          @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            groupService.addUser(groupId, userIdTo.getId(), userDetails.getUserInfo());
            logService.logAddUserAction(userDetails.getUserInfo(), groupId, userIdTo.getId());
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("User added");
    }

    @DeleteMapping(value = "/{groupId}/user/{userId}")
    @Operation(operationId = "removeUser")
    public ResponseEntity<String> removeUser(@PathVariable Long groupId,
                                             @PathVariable Long userId,
                                             @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            groupService.removeUser(groupId, userId, userDetails.getUserInfo());
            logService.logRemoveUserAction(userDetails.getUserInfo(), groupId, userId);
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("User removed");
    }

    @GetMapping(value = "/role")
    @Operation(operationId = "getRoles")
    public ResponseEntity<List<String>> getAvailableRoles() {
        List<String> roles = groupService.getAvailableRoles().stream().map(Objects::toString).collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    @Operation(operationId = "createGroup")
    public ResponseEntity<GroupOutputTO> saveGroup(@RequestBody @Valid GroupInputTO dto,
                                                   @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            Group saved = groupService.createGroup(dto.toGroup(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, Group.class, saved.getName());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/{groupId}")
    @Operation(operationId = "updateGroup")
    public ResponseEntity<GroupOutputTO> updateGroup(@PathVariable Long groupId,
                                                     @RequestBody @Valid GroupInputTO dto,
                                                     @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            Group saved = groupService.updateGroup(groupId, dto.toGroup(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, Group.class, saved.getName());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    @Operation(operationId = "getGroup")
    public ResponseEntity<List<GroupOutputTO>> getAllGroups(@Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            List<GroupOutputTO> roles = groupService.getAllGroups(userDetails.getUserInfo()).stream()
                    .map(GroupOutputTO::fromGroup).collect(Collectors.toList());
            return ResponseEntity.ok(roles);
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
