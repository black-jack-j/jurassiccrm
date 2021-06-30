package com.jurassic.jurassiccrm.group.dto;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateGroupDTO {

    @NotNull
    @Size(min = 3, max = 128)
    private String name;

    private List<User> users = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();
}
