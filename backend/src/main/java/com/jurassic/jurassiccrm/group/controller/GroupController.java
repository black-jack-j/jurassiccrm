package com.jurassic.jurassiccrm.group.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags = "group")
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
    @ApiOperation(value = "getUsers", nickname = "getUsers")
    public ResponseEntity<List<UserOutputTO>> getAvailableUsers() {
        val dtoList = groupService.getAvailableUsers().stream().map(UserOutputTO::fromUser).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get group info", nickname = "getGroup")
    public ResponseEntity<GroupOutputTO> getGroup(@PathVariable("id") Long id) {
        Group group = groupService.getGroup(id);

        return ResponseEntity.ok(GroupOutputTO.fromGroup(group));
    }

    @GetMapping(value = "/{id}/icon", produces = {"image/png", "image/jpeg", "image/*"})
    @ApiOperation(value = "get group icon", nickname = "getGroupIcon", produces = "image/*")
    @Transactional
    public ResponseEntity<byte[]> getGroupIcon(@PathVariable("id") Long id) {
        Group group = groupService.getGroup(id);

        return ResponseEntity.ok(group.getAvatar());
    }

    @PostMapping(value = "/{groupId}/user")
    @ApiOperation(value = "addUser", nickname = "addUser")
    public ResponseEntity<String> addUser(@PathVariable Long groupId,
                                          @RequestBody @Valid UserIdInputTO userIdTo,
                                          @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
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
    @ApiOperation(value = "removeUser", nickname = "removeUser")
    public ResponseEntity<String> removeUser(@PathVariable Long groupId,
                                             @PathVariable Long userId,
                                             @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
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
    @ApiOperation(value = "getRoles", nickname = "getRoles")
    public ResponseEntity<List<String>> getAvailableRoles() {
        List<String> roles = groupService.getAvailableRoles().stream().map(Objects::toString).collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "createGroup", nickname = "createGroup")
    public ResponseEntity<GroupOutputTO> saveGroup(@RequestPart("avatar") MultipartFile avatar,
                                                   @RequestPart("groupInfo") String groupInfo,
                                                   @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            val dto = new ObjectMapper().readValue(groupInfo, GroupInputTO.class);
            dto.setAvatar(avatar);
            Group saved = groupService.createGroup(dto.toGroup(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, Group.class, saved.getName());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{groupId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "updateGroup", nickname = "updateGroup")
    public ResponseEntity<GroupOutputTO> updateGroup(@PathVariable Long groupId,
                                                     @RequestPart("avatar") MultipartFile avatar,
                                                     @RequestPart("groupInfo") String groupInfo,
                                                     @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            val dto = new ObjectMapper().readValue(groupInfo, GroupInputTO.class);
            dto.setAvatar(avatar);
            Group saved = groupService.updateGroup(groupId, dto.toGroup(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, Group.class, saved.getName());
            return ResponseEntity.ok(GroupOutputTO.fromGroup(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @ApiOperation(value = "getGroup", nickname = "getAllGroups")
    public ResponseEntity<List<GroupOutputTO>> getAllGroups(@ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            List<GroupOutputTO> roles = groupService.getAllGroups(userDetails.getUserInfo()).stream()
                    .map(GroupOutputTO::fromGroup).collect(Collectors.toList());
            return ResponseEntity.ok(roles);
        } catch (UnauthorisedGroupOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
