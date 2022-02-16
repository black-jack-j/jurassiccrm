package com.jurassic.jurassiccrm.accesscontroll.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleUserInfoTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Department department;

    public static SimpleUserInfoTO fromUser(User user) {
        return new SimpleUserInfoTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getDepartment()
        );
    }

}
