package com.jurassic.jurassiccrm.common.dto;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleEntityInputTO {
    @NotBlank
    private String name;

    public AviaryType toAviaryType() {
        return new AviaryType(name);
    }

    public DinosaurType toDinosaurType() {
        return new DinosaurType(name);
    }

    public DecorationType toDecorationType() {
        return new DecorationType(name);
    }

    public Research toResearch() {
        return new Research(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends SimpleEntity> T toEntity(Class<T> type) {
        if (type.equals(AviaryType.class))
            return (T) this.toAviaryType();
        if (type.equals(DinosaurType.class))
            return (T) this.toDinosaurType();
        if (type.equals(DecorationType.class))
            return (T) this.toDecorationType();
        if (type.equals(Research.class))
            return (T) this.toResearch();
        throw new IllegalArgumentException("Unsupported type");
    }
}
