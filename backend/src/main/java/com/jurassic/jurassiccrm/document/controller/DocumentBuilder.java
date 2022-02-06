package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.document.dto.input.*;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class DocumentBuilder {

    public static Document build(DocumentType type, Map<String, Object> body) throws DocumentBuilderException {
        switch (type) {
            case THEME_ZONE_PROJECT:
                return parseDocument(body, ThemeZoneProjectInputTO.class).toThemeZoneProject();
            case DINOSAUR_PASSPORT:
                return parseDocument(body, DinosaurPassportInputTO.class).toDinosaurPassport();
            case TECHNOLOGICAL_MAP:
                return parseDocument(body, TechnologicalMapInputTO.class).toTechnologicalMap();
            case AVIARY_PASSPORT:
                return parseDocument(body, AviaryPassportInputTO.class).toAviaryPassport();
            case RESEARCH_DATA:
                try {
                    return parseDocument(body, ResearchDataInputTO.class).toResearchData();
                } catch (IOException e) {
                    throw DocumentBuilderException.attachmentReadError(e);
                }
            default: throw DocumentBuilderException.unsupportedDocumentType(type);
        }
    }

    private static <T extends DocumentInputTO> T parseDocument(Map<String, Object> body, Class<T> toType) throws DocumentBuilderException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            T to = mapper.convertValue(body, toType);
            validateDocumentOrThrowException(to);
            return to;
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
