package com.jurassic.jurassiccrm.document.controller;

import com.jurassic.jurassiccrm.document.dto.CreateDocumentDTO;
import com.jurassic.jurassiccrm.document.service.DocumentService;
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

import javax.validation.Valid;

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
        return "document/index";
    }

    @GetMapping
    public String documentDashboard(Model model, Authentication authentication) {
        return "/document/index";
    }

    @GetMapping("/upload")
    public String documentUpload(Model model) {
        model.addAttribute("createDocumentDTO", new CreateDocumentDTO());
        return "/document/upload";
    }
}
