package com.jurassic.jurassiccrm.validation;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class ViolationTO {

    private String fieldName;
    private String message;
}
