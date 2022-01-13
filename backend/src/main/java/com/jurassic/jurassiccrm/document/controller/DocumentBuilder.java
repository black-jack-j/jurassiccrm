package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.document.dto.input.*;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

public class DocumentBuilder {

    public static Document build(DocumentType type, String json) throws DocumentParseException {
        switch (type) {
            case THEME_ZONE_PROJECT:
                return parseDocument(json, ThemeZoneProjectInputTO.class).toThemeZoneProject();
            case DINOSAUR_PASSPORT:
                return parseDocument(json, DinosaurPassportInputTO.class).toDinosaurPassport();
            case TECHNOLOGICAL_MAP:
                return parseDocument(json, TechnologicalMapInputTO.class).toTechnologicalMap();
            case AVIARY_PASSPORT:
                return parseDocument(json, AviaryPassportInputTO.class).toAviaryPassport();
            case RESEARCH_DATA:
                try {
                    return parseDocument(json, ResearchDataInputTO.class).toResearchData();
                } catch (IOException e) {
                    throw DocumentParseException.attachmentReadError(e);
                }
            default: throw DocumentParseException.unsupportedDocumentType(type);
        }
    }

    private static <T extends DocumentInputTO> T parseDocument(String json, Class<T> toType) throws DocumentParseException {
        ObjectMapper mapper = new ObjectMapper();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        try {
            T to = mapper.readValue(json, toType);
            Set<ConstraintViolation<T>> validationErrors = validator.validate(to);
            if(!validationErrors.isEmpty())
                throw DocumentParseException.validationError(validationErrors);
            return to;
        } catch (JsonMappingException e){
            throw DocumentParseException.jsonMappingError(e);
        } catch (JsonProcessingException e){
            throw DocumentParseException.jsonProcessingError(e);
        }
    }
}
