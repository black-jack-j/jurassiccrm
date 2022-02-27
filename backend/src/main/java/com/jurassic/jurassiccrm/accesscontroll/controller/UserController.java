package com.jurassic.jurassiccrm.accesscontroll.controller;

import com.jurassic.jurassiccrm.accesscontroll.dto.*;
import com.jurassic.jurassiccrm.accesscontroll.exception.UnauthorisedUserOperationException;
import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.task.controller.TaskController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Api(tags = "user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final UserService userService;
    private final LogService logService;

    @Autowired
    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "createUser", nickname = "createUser")
    public ResponseEntity<FullUserOutputTO> saveUser(@RequestBody @Valid CreateUserTO user,
                                                     @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            User saved = userService.createUser(user.toUser(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, User.class, saved.getUsername());
            return ResponseEntity.ok(FullUserOutputTO.fromUser(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "updateUser", nickname = "updateUser")
    public ResponseEntity<FullUserOutputTO> updateUser(@PathVariable Long userId,
                                                       @RequestBody UpdateUserTO user,
                                                       @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            User saved = userService.updateUser(userId, user.toUser(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, User.class, saved.getUsername());
            return ResponseEntity.ok(FullUserOutputTO.fromUser(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/{userId}/icon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "update user avatar", nickname = "updateUserAvatar")
    public ResponseEntity<?> updateUserAvatar(@PathVariable Long userId,
                                              @RequestPart("avatar") MultipartFile avatar,
                                              @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            userService.updateUserAvatar(userId, avatar.getBytes(), userDetails.getUserInfo());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            log.error("Error updating user avatar " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Transactional
    @ApiOperation(value = "getUsers", nickname = "getUsersFull")
    public ResponseEntity<List<FullUserOutputTO>> getAllUsers(@ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            List<FullUserOutputTO> roles = userService.getAllUsers(userDetails.getUserInfo()).stream()
                    .map(FullUserOutputTO::fromUser).collect(Collectors.toList());
            return ResponseEntity.ok(roles);
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Transactional
    @GetMapping("/simple")
    @ApiOperation(value = "get users simple", nickname = "getUsersSimple")
    public ResponseEntity<List<SimpleUserInfoTO>> getAllUsersSimple(@ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            List<SimpleUserInfoTO> roles = userService.getAllUsers(userDetails.getUserInfo()).stream()
                    .map(SimpleUserInfoTO::fromUser).collect(Collectors.toList());
            return ResponseEntity.ok(roles);
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Transactional
    @GetMapping(value = "/{id}/icon", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "get user icon", nickname = "getUserIcon", produces = "image/png,image/jpeg", response = byte[].class)
    public ResponseEntity<Resource> getUserIcon(@PathVariable Long id,
                                              @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        val user = userService.getUserById(userDetails.getUserInfo(), id);
        byte[] avatar = user.getAvatar();
        if (avatar != null && avatar.length != 0) {
            return ResponseEntity.ok(new ByteArrayResource(avatar));
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/img/avatar.png");
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }
    }

    @Transactional
    @GetMapping("/{userId}")
    @ApiOperation(value = "getUserById", nickname = "getUserById")
    public ResponseEntity<FullUserOutputTO> getUserById(@PathVariable Long userId,
                                                        @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            User user = userService.getUserById(userDetails.getUserInfo(), userId);
            return ResponseEntity.ok(FullUserOutputTO.fromUser(user));
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    @GetMapping("/role-all")
    @ApiOperation(value = "findAllByRolesAll", nickname = "findAllByRolesAll")
    public ResponseEntity<List<FullUserOutputTO>> findAllByRolesAll(@RequestParam List<Role> roles) {
        List<FullUserOutputTO> users = userService.getAllByRolesAll(roles).stream()
                .map(FullUserOutputTO::fromUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Transactional
    @GetMapping("/role-any")
    @ApiOperation(value = "findAllByRolesAny", nickname = "findAllByRolesAny")
    public ResponseEntity<List<FullUserOutputTO>> findAllByRolesAny(@RequestParam List<Role> roles) {
        List<FullUserOutputTO> users = userService.getAllByRolesAny(roles).stream()
                .map(FullUserOutputTO::fromUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active/role")
    @ApiOperation(value = "getCurrentUserRoles", nickname = "getCurrentUserRoles")
    public ResponseEntity<Set<Role>> getCurrentUserRoles(@ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return ResponseEntity.ok(userService.getUserRoles(userDetails.getUserInfo()));
    }

    @Transactional
    @GetMapping("/active")
    @ApiOperation(value = "getCurrentUser", nickname = "getCurrentUser")
    public ResponseEntity<UserWithRolesTO> getCurrentUser(@ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        return ResponseEntity.ok(UserWithRolesTO.fromUser(userDetails.getUserInfo()));
    }

}
