package com.jurassic.jurassiccrm.common.dto;

import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEntityOutputTO {
    private Long id;
    private String name;

    public static SimpleEntityOutputTO fromEntity(SimpleEntity entity) {
        return new SimpleEntityOutputTO(entity.getId(), entity.getName());
    }
}
