package com.jurassic.jurassiccrm.accesscontroll.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.group.dto.SimpleGroupTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserWithRolesTO {

    private Long id;
    private String avatarSrc;
    private String username;
    private String firstName;
    private String lastName;
    private Department department;
    private List<Role> roles;
    private List<SimpleGroupTO> groups;

    public static UserWithRolesTO fromUser(User user) {
        List<SimpleGroupTO> groups = user.getGroups().stream()
                .map(SimpleGroupTO::fromGroup)
                .collect(Collectors.toList());

        List<Role> roles = user.getGroups().stream()
                .flatMap(group -> group.getRoles().stream())
                .distinct()
                .collect(Collectors.toList());

        boolean hasAvatar = user.getAvatar() != null && user.getAvatar().length != 0;

        return new UserWithRolesTO(
                user.getId(),
                hasAvatar ? "/api/user/"+user.getId()+"/icon" : null,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getDepartment(),
                roles,
                groups
        );
    }

}
