package com.jurassic.jurassiccrm.accesscontroll.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.accesscontroll.dto.FullUserInputTO;
import com.jurassic.jurassiccrm.accesscontroll.dto.FullUserOutputTO;
import com.jurassic.jurassiccrm.accesscontroll.dto.SimpleUserInfoTO;
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
import io.swagger.annotations.ApiResponse;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.print.attribute.standard.Media;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "createUser", nickname = "createUser")
    public ResponseEntity<FullUserOutputTO> saveUser(@RequestPart("avatar") MultipartFile avatar,
                                                     @RequestPart("userInfo") String userInfo,
                                                     @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            val dto = new ObjectMapper().readValue(userInfo, FullUserInputTO.class);
            dto.setAvatar(avatar);
            User saved = userService.createUser(dto.toUser(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, User.class, saved.getUsername());
            return ResponseEntity.ok(FullUserOutputTO.fromUser(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(
            value = "/{userId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "updateUser", nickname = "updateUser")
    public ResponseEntity<FullUserOutputTO> updateUser(@PathVariable Long userId,
                                                       @RequestPart("avatar") MultipartFile avatar,
                                                       @RequestPart("userInfo") String userInfo,
                                                       @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try {
            val dto = new ObjectMapper().readValue(userInfo, FullUserInputTO.class);
            dto.setAvatar(avatar);
            User saved = userService.updateUser(userId, dto.toUser(), userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, User.class, saved.getUsername());
            return ResponseEntity.ok(FullUserOutputTO.fromUser(saved));
        } catch (IllegalArgumentException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedUserOperationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
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

    @GetMapping(value = "/{id}/icon", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "get user icon", nickname = "getUserIcon", produces = "image/png,image/jpeg", response = byte[].class)
    @Transactional
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

    @GetMapping("/role-all")
    @ApiOperation(value = "findAllByRolesAll", nickname = "findAllByRolesAll")
    public ResponseEntity<List<FullUserOutputTO>> findAllByRolesAll(@RequestParam List<Role> roles) {
        List<FullUserOutputTO> users = userService.getAllByRolesAll(roles).stream()
                .map(FullUserOutputTO::fromUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

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
}
