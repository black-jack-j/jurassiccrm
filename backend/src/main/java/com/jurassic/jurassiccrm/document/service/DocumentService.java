package com.jurassic.jurassiccrm.document.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    Logger log = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Document createDocumentWith(Document document, User owner) {
        Document savedDocument = documentRepository.save(document);

        //TODO: investigate why exception is thrown
/*        Role documentCreatorRole = roleService.getBasicRole("ROLE_DOCUMENT_ADMIN");

        documentCreatorRole.addResource(document);

        roleRepository.save(documentCreatorRole);

        if (owner.addRole(documentCreatorRole)) {
            userRepository.save(owner);
        }*/

        return savedDocument;
    }

}
