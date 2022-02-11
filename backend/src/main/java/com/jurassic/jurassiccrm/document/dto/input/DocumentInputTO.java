package com.jurassic.jurassiccrm.document.dto.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(subTypes = {
        DinosaurPassportInputTO.class,
        AviaryPassportInputTO.class,
        ResearchDataInputTO.class,
        TechnologicalMapInputTO.class,
        ThemeZoneProjectInputTO.class
}, discriminator = "documentType")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "documentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DinosaurPassportInputTO.class, name = DocumentType.Constants.DINOSAUR_PASSPORT),
        @JsonSubTypes.Type(value = AviaryPassportInputTO.class, name = DocumentType.Constants.AVIARY_PASSPORT),
        @JsonSubTypes.Type(value = ResearchDataInputTO.class, name = DocumentType.Constants.RESEARCH_DATA),
        @JsonSubTypes.Type(value = TechnologicalMapInputTO.class, name = DocumentType.Constants.TECHNOLOGICAL_MAP),
        @JsonSubTypes.Type(value = ThemeZoneProjectInputTO.class, name = DocumentType.Constants.THEME_ZONE_PROJECT)
})
public abstract class DocumentInputTO {
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull
    private String documentType;

    protected void setBaseFields(Document document){
        document.setName(name);
        document.setDescription(description);
    }
}
