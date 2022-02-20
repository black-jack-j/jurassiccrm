package com.jurassic.jurassiccrm.document.dto.output.document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import com.jurassic.jurassiccrm.document.dto.input.*;
import com.jurassic.jurassiccrm.document.model.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.Instant;

@Data
@ApiModel(subTypes = {
        DinosaurPassportOutputTO.class,
        AviaryPassportOutputTO.class,
        ResearchDataOutputTO.class,
        TechnologicalMapOutputTO.class,
        ThemeZoneProjectOutputTO.class
}, discriminator = "documentType")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "documentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DinosaurPassportOutputTO.class, name = DocumentType.Constants.DINOSAUR_PASSPORT),
        @JsonSubTypes.Type(value = AviaryPassportOutputTO.class, name = DocumentType.Constants.AVIARY_PASSPORT),
        @JsonSubTypes.Type(value = ResearchDataOutputTO.class, name = DocumentType.Constants.RESEARCH_DATA),
        @JsonSubTypes.Type(value = TechnologicalMapOutputTO.class, name = DocumentType.Constants.TECHNOLOGICAL_MAP),
        @JsonSubTypes.Type(value = ThemeZoneProjectOutputTO.class, name = DocumentType.Constants.THEME_ZONE_PROJECT)
})
public class DocumentOutputTO {
    private Long id;
    private String name;
    private DocumentType documentType;
    private UserOutputTO author;
    private UserOutputTO lastUpdater;
    private Instant created;
    private Instant lastUpdate;
    private String description;

    protected void setBaseFields(Document document) {
        id = document.getId();
        name = document.getName();
        documentType = document.getType();
        author = UserOutputTO.fromUser(document.getAuthor());
        lastUpdater = UserOutputTO.fromUser(document.getLastUpdater());
        created = document.getCreated();
        lastUpdate = document.getLastUpdate();
        description = document.getDescription();
    }

    public static DocumentOutputTO fromDocument(Document document) {
        switch (document.getType()) {

            case THEME_ZONE_PROJECT:
                return ThemeZoneProjectOutputTO.fromDocument((ThemeZoneProject) document);
            case DINOSAUR_PASSPORT:
                return DinosaurPassportOutputTO.fromDocument((DinosaurPassport) document);
            case TECHNOLOGICAL_MAP:
                return TechnologicalMapOutputTO.fromDocument((TechnologicalMap) document);
            case AVIARY_PASSPORT:
                return AviaryPassportOutputTO.fromDocument((AviaryPassport) document);
            case RESEARCH_DATA:
                return ResearchDataOutputTO.fromDocument((ResearchData) document);
            default:
                return null;
        }
    }
}
