package com.jurassic.jurassiccrm.document.controller;

import com.jurassic.jurassiccrm.document.dto.input.DocumentInputTO;
import com.jurassic.jurassiccrm.document.model.Document;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class DocumentBuilder {

    public static Document build(DocumentInputTO to) {
        validateDocumentOrThrowException(to);
        return to.toDocument();
    }

    private static <T extends DocumentInputTO> void validateDocumentOrThrowException(T document) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(document);
        if(!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
