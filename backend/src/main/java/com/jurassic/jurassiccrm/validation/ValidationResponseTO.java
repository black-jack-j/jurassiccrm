package com.jurassic.jurassiccrm.validation;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel
public class ValidationResponseTO {

    private List<ViolationTO> violations = new ArrayList<>();

}
