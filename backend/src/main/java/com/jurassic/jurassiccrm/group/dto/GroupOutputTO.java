package com.jurassic.jurassiccrm.group.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import lombok.Data;
import lombok.val;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroupOutputTO {
    private Long id;
    private String name;
    private Set<Role> roles;
    private Set<UserOutputTO> users;

    public static GroupOutputTO fromGroup(Group group) {
        val dto = new GroupOutputTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setRoles(group.getRoles());
        dto.setUsers(group.getUsers().stream().map(UserOutputTO::fromUser).collect(Collectors.toSet()));
        return dto;
    }
}
