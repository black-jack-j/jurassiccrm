package com.jurassic.jurassiccrm.group.dto;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleGroupTO {

    private Long id;
    private String name;
    private String description;

    public static SimpleGroupTO fromGroup(Group group) {
        return new SimpleGroupTO(group.getId(), group.getName(), group.getDescription());
    }
}
