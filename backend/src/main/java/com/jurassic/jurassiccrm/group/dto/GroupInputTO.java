package com.jurassic.jurassiccrm.group.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroupInputTO {
    @NotBlank
    private String name;
    private Set<Role> roles = new HashSet<>();
    private Set<Long> userIds = new HashSet<>();

    public Group toGroup() {
        Group group = new Group();
        group.setName(name);
        group.setRoles(roles);
        group.setUsers(userIds.stream().map(User::new).collect(Collectors.toSet()));
        return group;
    }
}
