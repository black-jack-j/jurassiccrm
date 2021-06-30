package com.jurassic.jurassiccrm.document.controller;

import com.jurassic.jurassiccrm.document.dto.CreateDocumentDTO;
import com.jurassic.jurassiccrm.document.dto.DocumentMetaDTO;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.service.DocumentService;
import com.jurassic.jurassiccrm.accesscontroll.entity.JurassicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/document")
public class DocumentController {

    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DOCUMENT_WRITER', 'ADMIN')")
    public String uploadDocument(@ModelAttribute("createDocumentDTO") @Valid CreateDocumentDTO createDocumentDTO,
                                 BindingResult multipartFileBindingResult,
                                 Model model,
                                 Authentication authentication) {
        if (multipartFileBindingResult.hasErrors()) {
            return "/document/upload";
        }
        JurassicUserDetails userDetails = (JurassicUserDetails) (authentication.getPrincipal());
        Document document = new Document();
        document.setName(createDocumentDTO.getDocumentName());
        document.setType(createDocumentDTO.getDocumentType());
        document.setContentType(createDocumentDTO.getDocument().getContentType());
        document.setDescription(createDocumentDTO.getDescription());
        document.setAuthor(userDetails.getUsername());
        try {
            document.setContent(createDocumentDTO.getDocument().getBytes());
            document.setSize(document.getContent().length);
        } catch (IOException e) {
            log.error("error during 'getBytes' from uploaded document. principal: {}"
                    , userDetails.getUserInfo().getUsername());
            model.addAttribute("tryagain", true);
            model.addAttribute("tryagain-message", "something went wrong, try again");
            return "/document/upload";
        }
        try {
            Document savedDocument = documentService.createDocumentWith(document, userDetails.getUserInfo());
            model.addAttribute("document", DocumentMetaDTO.buildFromDocument(savedDocument));
            return "document/view";
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }
        return "document/index";
    }

    @GetMapping
    public String documentDashboard(Model model, Authentication authentication) {
        JurassicUserDetails userDetails = (JurassicUserDetails)authentication.getPrincipal();
        Set<DocumentMetaDTO> documents = documentService.findAllDocumentsAvailableFor(userDetails.getUserInfo()).stream()
                .map(DocumentMetaDTO::buildFromMeta)
                .collect(Collectors.toSet());

        model.addAttribute("documents", documents);
        return "/document/index";
    }

    @GetMapping("/upload")
    public String documentUpload(Model model) {
        model.addAttribute("createDocumentDTO", new CreateDocumentDTO());
        return "/document/upload";
    }
}
