package com.jurassic.jurassiccrm.accesscontroll.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.group.dto.SimpleGroupTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FullUserOutputTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Department department;
    private List<SimpleGroupTO> groupIds = new ArrayList<>();

    private void addGroup(Group group) {
        groupIds.add(SimpleGroupTO.fromGroup(group));
    }

    public static FullUserOutputTO fromUser(User user) {
        FullUserOutputTO dto = new FullUserOutputTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDepartment(user.getDepartment());
        user.getGroups().forEach(dto::addGroup);
        return dto;
    }
}
