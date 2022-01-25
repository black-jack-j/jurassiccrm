package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.document.dto.input.*;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

public class DocumentBuilder {

    public static Document build(DocumentType type, String json) throws DocumentBuilderException {
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
                return getResearchData(json);
            default: throw DocumentBuilderException.unsupportedDocumentType(type);
        }
    }

    private static Document getResearchData(String json) throws DocumentBuilderException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Map<String, Object> properties = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
            if (properties.containsKey("researchId")) {
                ExistingResearchDataInputTO research = mapper.readValue(json, ExistingResearchDataInputTO.class);
                validateDocumentOrThrowException(research);
                return research.toResearchData();
            } else {
                NewResearchDataInputTO research = mapper.readValue(json, NewResearchDataInputTO.class);
                validateDocumentOrThrowException(research);
                return research.toResearchData();
            }
        } catch (JsonProcessingException e) {
            throw DocumentBuilderException.jsonProcessingError(e);
        }

    }

    private static <T extends DocumentInputTO> T parseDocument(String json, Class<T> toType) throws DocumentBuilderException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            T to = mapper.readValue(json, toType);
            validateDocumentOrThrowException(to);
            return to;
        } catch (JsonMappingException e){
            throw DocumentBuilderException.jsonMappingError(e);
        } catch (JsonProcessingException e){
            throw DocumentBuilderException.jsonProcessingError(e);
        } catch (ValidationException e){
            throw DocumentBuilderException.validationError(e);
        }
    }

    private static <T extends DocumentInputTO> void validateDocumentOrThrowException(T document) throws DocumentBuilderException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(document);
        if(!constraintViolations.isEmpty()) {
            throw DocumentBuilderException.invalidDocument(constraintViolations);
        }
    }
}
