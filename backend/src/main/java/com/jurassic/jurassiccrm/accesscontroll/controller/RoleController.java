package com.jurassic.jurassiccrm.accesscontroll.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/role")
@Tag(name = "role")
public class RoleController {

    @GetMapping
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Role.class)))
    public ResponseEntity<Role> getAllRoles(
            @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails
    ) throws IllegalAccessException {
        throw new IllegalAccessException("Not implemented");
    }

}
