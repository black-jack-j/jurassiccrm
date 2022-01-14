package com.jurassic.jurassiccrm.document.dao.exception;

import com.jurassic.jurassiccrm.document.model.DocumentType;

public class DocumentDaoException extends RuntimeException {
    public DocumentDaoException(String message) {
        super(message);
    }

    public DocumentDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public static DocumentDaoException unsupportedDocumentType(DocumentType type){
        return new DocumentDaoException(String.format("Unsupported document type %s", type));
    }

    public static DocumentDaoException duplicateDocumentName(String name){
        return new DocumentDaoException(String.format("Document with name %s already exists", name));
    }

    public static DocumentDaoException documentIdNotFound(Long id){
        return new DocumentDaoException(String.format("Can't update document by id %d. Not found", id));
    }
}
