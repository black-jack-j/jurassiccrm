package com.jurassic.jurassiccrm.group.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.accesscontroll.service.UnauthorisedGroupOperationException;
import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import com.jurassic.jurassiccrm.group.dto.GroupInputTO;
import com.jurassic.jurassiccrm.group.dto.GroupOutputTO;
import com.jurassic.jurassiccrm.group.dto.UserIdInputTO;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    Logger log = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserOutputTO>> getAvailableUsers() {
        val dtoList = groupService.getAvailableUsers().stream().map(UserOutputTO::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping(value = "/{groupId}/user")
    public ResponseEntity<String> addUser(@PathVariable Long groupId,
                                          @RequestBody @Valid UserIdInputTO userIdTo,
                                          Authentication authentication) {
        try {
            JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
            groupService.addUser(groupId, userIdTo.getId(), userDetails.getUserInfo());
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("User added");
    }

    @DeleteMapping(value = "/{groupId}/user/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long groupId,
                                             @PathVariable Long userId,
                                             Authentication authentication) {
        try {
            JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
            groupService.removeUser(groupId, userId, userDetails.getUserInfo());
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("User removed");
    }

    @GetMapping(value = "/role")
    public ResponseEntity<List<String>> getAvailableRoles() {
        List<String> roles = groupService.getAvailableRoles().stream().map(Objects::toString).collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<GroupOutputTO> saveGroup(@RequestBody @Valid GroupInputTO dto,
                                                   Authentication authentication) {
        try {
            JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
            Group saved = groupService.createGroup(dto.toGroup(), userDetails.getUserInfo());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/{groupId}")
    public ResponseEntity<GroupOutputTO> updateGroup(@PathVariable Long groupId,
                                                     @RequestBody @Valid GroupInputTO dto,
                                                     Authentication authentication) {
        try {
            JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
            Group saved = groupService.updateGroup(groupId, dto.toGroup(), userDetails.getUserInfo());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GroupOutputTO>> getAllGroups(Authentication authentication) {
        try {
            JurassicUserDetails userDetails = (JurassicUserDetails) authentication.getPrincipal();
            List<GroupOutputTO> roles = groupService.getAllGroups(userDetails.getUserInfo()).stream()
                    .map(GroupOutputTO::fromGroup).collect(Collectors.toList());
            return ResponseEntity.ok(roles);
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
