package com.jurassic.jurassiccrm.document.controller;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.document.dao.exception.DocumentDaoException;
import com.jurassic.jurassiccrm.document.dto.output.document.DocumentOutputTO;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.service.DocumentService;
import com.jurassic.jurassiccrm.document.service.exceptions.UnauthorisedDocumentOperationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/document")
@Tag(name = "document")
public class DocumentController {

    Logger log = LoggerFactory.getLogger(DocumentController.class);

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/{documentType}")
    @Operation(operationId = "createDocument")
    public ResponseEntity<DocumentOutputTO> createDocument(@PathVariable DocumentType documentType,
                                                           HttpEntity<String> httpEntity,
                                                           @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try{
            Document document = DocumentBuilder.build(documentType, httpEntity.getBody());
            Document created = documentService.createDocument(document, userDetails.getUserInfo());
            return ResponseEntity.ok(DocumentOutputTO.fromDocument(created));
        } catch (DocumentBuilderException | DocumentDaoException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedDocumentOperationException e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{documentType}/{documentId}")
    @Operation(operationId = "updateDocument")
    public ResponseEntity<DocumentOutputTO> updateDocument(@PathVariable DocumentType documentType,
                                                           @PathVariable Long documentId,
                                                           HttpEntity<String> httpEntity,
                                                           @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try{
            Document document = DocumentBuilder.build(documentType, httpEntity.getBody());
            Document created = documentService.updateDocument(documentId, document, userDetails.getUserInfo());
            return ResponseEntity.ok(DocumentOutputTO.fromDocument(created));
        } catch (DocumentBuilderException | DocumentDaoException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedDocumentOperationException e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{documentType}")
    @Operation(operationId = "getDocumentsByType")
    public ResponseEntity<List<DocumentOutputTO>> getDocuments(@PathVariable DocumentType documentType,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try{
            List<? extends Document> documents = documentService.getDocuments(documentType, userDetails.getUserInfo());
            List<DocumentOutputTO> dtos = new ArrayList<>();
            documents.forEach(doc -> dtos.add(DocumentOutputTO.fromDocument(doc)));
            return ResponseEntity.ok(dtos);
        } catch (DocumentDaoException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedDocumentOperationException e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public String documentDashboard() {
        return "/document/index";
    }
}
