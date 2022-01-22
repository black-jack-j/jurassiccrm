package com.jurassic.jurassiccrm.accesscontroll.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FullUserOutputTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Department department;
    private List<SimpleEntityOutputTO> groupIds = new ArrayList<>();

    private void addGroup(Group group) {
        groupIds.add(SimpleEntityOutputTO.fromEntity(group));
    }

    public static FullUserOutputTO fromUser(User user) {
        FullUserOutputTO dto = new FullUserOutputTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDepartment(user.getDepartment());
        user.getGroups().forEach(dto::addGroup);
        return dto;
    }
}
