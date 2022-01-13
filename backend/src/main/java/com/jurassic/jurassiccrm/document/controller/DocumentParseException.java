package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import org.apache.commons.lang.text.StrBuilder;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

public class DocumentParseException extends Exception {
    public DocumentParseException(String message) {
        super(message);
    }

    public DocumentParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static <T> DocumentParseException validationError(Set<ConstraintViolation<T>> errors){
        StringBuilder messageBuilder = new StringBuilder();
        errors.forEach(e -> messageBuilder.append(e.getMessage()).append("\n"));
        return new DocumentParseException(String.format("Invalid document\n%s", messageBuilder));
    }

    public static DocumentParseException jsonProcessingError(JsonProcessingException e){
        return new DocumentParseException(String.format("Failed to process JSON. Reason: %s", e.getMessage()), e);
    }

    public static DocumentParseException jsonMappingError(JsonMappingException e){
        return new DocumentParseException(String.format("Failed to map JSON fields. Reason: %s", e.getMessage()), e);
    }

    public static DocumentParseException attachmentReadError(IOException e){
        return new DocumentParseException(String.format("Failed to read file attachment. Reason: %s", e.getMessage()), e);
    }

    public static DocumentParseException unsupportedDocumentType(DocumentType type){
        return new DocumentParseException(String.format("Document type %s is not supported", type));
    }
}
