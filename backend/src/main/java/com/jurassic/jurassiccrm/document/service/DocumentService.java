package com.jurassic.jurassiccrm.document.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import com.jurassic.jurassiccrm.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional
    public Document createDocumentWith(Document document, User owner) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        document.setAuthor(owner);
        document.setCreated(now);
        document.setLastUpdate(now);

        return documentRepository.save(document);
    }

    public Set<DocumentMeta> findAllDocuments() {
        return documentRepository.findAllBy();
    }

}
