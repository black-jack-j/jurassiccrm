package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import lombok.Data;

@Data
public class UserOutputTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Department department;

    public static UserOutputTO fromUser(User user){
        UserOutputTO dto = new UserOutputTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDepartment(user.getDepartment());
        return dto;
    }
}
