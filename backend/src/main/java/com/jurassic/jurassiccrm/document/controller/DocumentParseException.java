package com.jurassic.jurassiccrm.document.controller;

import java.lang.reflect.Type;

public class DocumentParseException extends RuntimeException {
    public DocumentParseException(String message) {
        super(message);
    }

    public static DocumentParseException missing(String fieldName){
        return new DocumentParseException(String.format("Field %s is missing", fieldName));
    }

    public static DocumentParseException parsingFailure(String fieldName, String fieldValue, Type targetClass){
        return new DocumentParseException(String.format("Unable to parse convert field %s with value %s to type %s",
                fieldName, fieldValue, targetClass.getTypeName()));
    }
}
