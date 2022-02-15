package com.jurassic.jurassiccrm.accesscontroll.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Set;

@Data
public class FullUserInputTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Department department;

    private Set<Long> groupIds;

    private MultipartFile avatar;

    public User toUser() throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDepartment(department);
        user.setAvatar(avatar.getBytes());
        groupIds.forEach(groupId -> user.addGroup(new Group(groupId)));
        return user;
    }
}
