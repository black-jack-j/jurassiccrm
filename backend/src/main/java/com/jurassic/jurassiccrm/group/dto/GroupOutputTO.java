package com.jurassic.jurassiccrm.group.dto;

import com.jurassic.jurassiccrm.accesscontroll.dto.SimpleUserInfoTO;
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
    private Set<SimpleUserInfoTO> users;
    private String description;

    public static GroupOutputTO fromGroup(Group group) {
        val dto = new GroupOutputTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setRoles(group.getRoles());
        dto.setUsers(group.getUsers().stream().map(SimpleUserInfoTO::fromUser).collect(Collectors.toSet()));
        dto.setDescription(group.getDescription());
        return dto;
    }
}
