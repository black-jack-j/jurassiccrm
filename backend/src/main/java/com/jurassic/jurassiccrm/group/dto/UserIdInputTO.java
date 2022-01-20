package com.jurassic.jurassiccrm.group.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserIdInputTO {
    @NotNull
    private Long id;
}
