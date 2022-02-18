package com.jurassic.jurassiccrm.document.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.document.dao.exception.DocumentDaoException;
import com.jurassic.jurassiccrm.document.dto.input.DocumentInputTO;
import com.jurassic.jurassiccrm.document.dto.input.ResearchDataInputTO;
import com.jurassic.jurassiccrm.document.dto.output.document.DocumentOutputTO;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.service.DocumentService;
import com.jurassic.jurassiccrm.document.service.exceptions.UnauthorisedDocumentOperationException;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.research.model.Research;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/document")
@Api(tags = "document")
public class DocumentController {

    Logger log = LoggerFactory.getLogger(DocumentController.class);

    private final DocumentService documentService;
    private final JpaRepository<Research, Long> researchRepository;
    private final LogService logService;

    @Autowired
    public DocumentController(DocumentService documentService,
                              JpaRepository<Research, Long> researchRepository, LogService logService) {
        this.documentService = documentService;
        this.researchRepository = researchRepository;
        this.logService = logService;
    }

    @PostMapping(value = "/{documentType}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "createDocument", nickname = "createDocument")
    public ResponseEntity<DocumentOutputTO> createDocument(@PathVariable DocumentType documentType,
                                                           @RequestBody DocumentInputTO to,
                                                           @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try{
            Document document = DocumentBuilder.build(to);
            Document created = documentService.createDocument(document, userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, documentType.getName(), created.getName());
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
    @ApiOperation(value = "updateDocument", nickname = "updateDocument")
    public ResponseEntity<DocumentOutputTO> updateDocument(@PathVariable DocumentType documentType,
                                                           @PathVariable Long documentId,
                                                           @RequestBody DocumentInputTO to,
                                                           @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        try{
            Document document = DocumentBuilder.build(to);
            Document created = documentService.updateDocument(documentId, document, userDetails.getUserInfo());
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, documentType.getName(), created.getName());
            return ResponseEntity.ok(DocumentOutputTO.fromDocument(created));
        } catch (DocumentBuilderException | DocumentDaoException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().build();
        } catch (UnauthorisedDocumentOperationException e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/type")
    @ApiOperation(value = "get all document types", nickname = "getDocumentTypes", response = DocumentType.class)
    public ResponseEntity<DocumentType> getDocumentTypes() throws IllegalAccessException {
        throw new IllegalAccessException("not implemented");
    }

    @PostMapping(value = "/RESEARCH_DATA", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "createResearchData", nickname = "createResearchData", response = DocumentOutputTO.class)
    public ResponseEntity<DocumentOutputTO> createResearchData(@ApiParam(required = true) @RequestPart("researchData") String researchDataTOString,
                                                               @RequestPart("attachment") MultipartFile attachment,
                                                               @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) throws IOException {

        val researchDataTO = new ObjectMapper().readValue(researchDataTOString, ResearchDataInputTO.class);
        researchDataTO.setAttachment(attachment);
        val researchData = researchDataTO.toDocument();
        if (researchDataTO.isNewResearch()) {
            researchData.setResearch(researchRepository.save(researchData.getResearch()));
        } else {
            researchData.setResearch(researchRepository.getOne(researchData.getResearch().getId()));
        }
        researchData.setAttachmentName(attachment.getName());
        Document created = documentService.createDocument(researchData, userDetails.getUserInfo());
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, DocumentType.RESEARCH_DATA.getName(), created.getName());
        return ResponseEntity.ok(DocumentOutputTO.fromDocument(created));
    }

    @PutMapping(value = "/RESEARCH_DATA/{documentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "updateResearchData", nickname = "updateResearchData", response = DocumentOutputTO.class)
    public ResponseEntity<DocumentOutputTO> updateResearchData(@ApiParam(required = true) @PathVariable("documentId") Long documentId,
                                                               @ApiParam(required = true) @RequestPart("researchData") String researchDataTOString,
                                                               @RequestPart("attachment") MultipartFile attachment,
                                                               @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) throws IOException {

        val researchDataTO = new ObjectMapper().readValue(researchDataTOString, ResearchDataInputTO.class);
        researchDataTO.setAttachment(attachment);
        val researchData = researchDataTO.toDocument();
        if (researchDataTO.isNewResearch()) {
            researchData.setResearch(researchRepository.save(researchData.getResearch()));
        } else {
            researchData.setResearch(researchRepository.getOne(researchData.getResearch().getId()));
        }
        researchData.setAttachmentName(attachment.getName());
        Document updated = documentService.updateDocument(documentId, researchData, userDetails.getUserInfo());
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, DocumentType.RESEARCH_DATA.getName(), updated.getName());
        return ResponseEntity.ok(DocumentOutputTO.fromDocument(updated));
    }

    @GetMapping("/{documentType}")
    @ApiOperation(value = "getDocumentsByType", nickname = "getDocumentsByType")
    public ResponseEntity<List<DocumentOutputTO>> getDocuments(@PathVariable DocumentType documentType,
                                                               @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
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
    @ApiOperation(value = "get all documents", nickname = "getAllDocuments")
    public ResponseEntity<List<DocumentOutputTO>> getAllDocuments(
            @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {

        return ResponseEntity.ok(
                documentService.getAllDocuments(userDetails.getUserInfo()).stream()
                        .map(DocumentOutputTO::fromDocument)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{documentType}/{id}")
    @ApiOperation(value = "get document by id", nickname = "getDocumentById")
    @Transactional
    public ResponseEntity<DocumentOutputTO> getDocumentById(
            @ApiParam(required = true) @PathVariable DocumentType documentType,
            @ApiParam(required = true) @PathVariable Long id,
            @ApiIgnore @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Document document = documentService.getDocumentById(id, documentType, userDetails.getUserInfo());
        return ResponseEntity.ok(DocumentOutputTO.fromDocument(document));
    }

}
