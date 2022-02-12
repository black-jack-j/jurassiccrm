package com.jurassic.jurassiccrm.document.dto.output.document;

import com.jurassic.jurassiccrm.common.dto.UserOutputTO;
import com.jurassic.jurassiccrm.document.model.*;
import lombok.Data;

import java.time.Instant;

@Data
public class DocumentOutputTO {
    private Long id;
    private String name;
    private DocumentType type;
    private UserOutputTO author;
    private UserOutputTO lastUpdater;
    private Instant created;
    private Instant lastUpdate;
    private String description;

    protected void setBaseFields(Document document) {
        id = document.getId();
        name = document.getName();
        type = document.getType();
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
