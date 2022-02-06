package com.jurassic.jurassiccrm.accesscontroll.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "role")
@RequestMapping("/api/role")
public class RoleController {

    @GetMapping
    @ApiOperation(value = "get all roles", nickname = "getAllRoles")
    public ResponseEntity<Role> getAllRoles(
            @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails
    ) throws IllegalAccessException {
        throw new IllegalAccessException("Not implemented");
    }

}
