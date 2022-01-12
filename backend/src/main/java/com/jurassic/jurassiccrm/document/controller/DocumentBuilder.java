package com.jurassic.jurassiccrm.document.controller;

import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentTO;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import com.jurassic.jurassiccrm.document.model.TechnologicalMap;
import com.jurassic.jurassiccrm.document.model.ThemeZoneProject;
import lombok.val;

import java.util.Map;

public class DocumentBuilder {

    public static Document build(DocumentTO dto) throws DocumentParseException {
        switch (dto.getType()) {
            case THEME_ZONE_PROJECT:
                return parseThemeZoneProject(dto.getFields());
            case DINOSAUR_PASSPORT:
                return parseDinosaurPassport(dto.getFields());
            case TECHNOLOGICAL_MAP:
                return parseTechnologicalMap(dto.getFields());
            case AVIARY_PASSPORT:
                return parseAviaryPassport(dto.getFields());
            case RESEARCH_DATA:
                return parseResearchData(dto.getFields());
            default:
                throw new IllegalArgumentException(String.format("Unknown document type %s", dto.getType()));
        }
    }

    private static AviaryPassport parseAviaryPassport(Map<String, String> fields) {
        val aviaryPassport = new AviaryPassport();
        parseBaseDocumentFields(fields, aviaryPassport);
        return aviaryPassport;
    }

    private static DinosaurPassport parseDinosaurPassport(Map<String, String> fields) {
        val dinosaurPassport = new DinosaurPassport();
        parseBaseDocumentFields(fields, dinosaurPassport);
        return dinosaurPassport;
    }

    private static ThemeZoneProject parseThemeZoneProject(Map<String, String> fields) {
        val themeZoneProject = new ThemeZoneProject();
        parseBaseDocumentFields(fields, themeZoneProject);
        return themeZoneProject;
    }

    private static ResearchData parseResearchData(Map<String, String> fields) {
        val researchData = new ResearchData();
        parseBaseDocumentFields(fields, researchData);
        return researchData;
    }

    private static TechnologicalMap parseTechnologicalMap(Map<String, String> fields) {
        val technologicalMap = new TechnologicalMap();
        parseBaseDocumentFields(fields, technologicalMap);
        return technologicalMap;
    }

    private static void parseBaseDocumentFields(Map<String, String> fields, Document document) {
        val id = "id";
        val idValue = fields.get(id);
        if(idValue != null)
            try {
                document.setId(Long.parseLong(idValue));
            } catch (NumberFormatException e){
                throw DocumentParseException.parsingFailure(id, idValue, Long.class);
            }

        val name = "name";
        val nameValue = fields.get(name);
        if(nameValue == null) throw DocumentParseException.missing(name);
        document.setName(nameValue);

        document.setDescription(fields.get("description"));
    }
}
