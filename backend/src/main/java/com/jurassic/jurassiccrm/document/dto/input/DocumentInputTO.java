package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.document.model.Document;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public abstract class DocumentInputTO {
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    @Size(max = 1000)
    private String description;

    protected void setBaseFields(Document document){
        document.setName(name);
        document.setDescription(description);
    }
}
