package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jurassic.jurassiccrm.document.model.DocumentType;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Set;

public class DocumentBuilderException extends Exception {
    public DocumentBuilderException(String message) {
        super(message);
    }

    public DocumentBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public static <T> DocumentBuilderException invalidDocument(Set<ConstraintViolation<T>> errors){
        StringBuilder messageBuilder = new StringBuilder();
        errors.forEach(e -> messageBuilder.append(e.getPropertyPath()).append(" ").append(e.getMessage()).append("\n"));
        return new DocumentBuilderException(String.format("Invalid document\n%s", messageBuilder));
    }

    public static DocumentBuilderException validationError(ValidationException e){
        return new DocumentBuilderException(String.format("Failed to validate document. Reason: %s", e.getMessage()), e);
    }

    public static DocumentBuilderException jsonProcessingError(JsonProcessingException e){
        return new DocumentBuilderException(String.format("Failed to process JSON. Reason: %s", e.getMessage()), e);
    }

    public static DocumentBuilderException jsonMappingError(JsonMappingException e){
        return new DocumentBuilderException(String.format("Failed to map JSON fields. Reason: %s", e.getMessage()), e);
    }

    public static DocumentBuilderException attachmentReadError(IOException e){
        return new DocumentBuilderException(String.format("Failed to read file attachment. Reason: %s", e.getMessage()), e);
    }

    public static DocumentBuilderException unsupportedDocumentType(DocumentType type){
        return new DocumentBuilderException(String.format("Document type %s is not supported", type));
    }
}
