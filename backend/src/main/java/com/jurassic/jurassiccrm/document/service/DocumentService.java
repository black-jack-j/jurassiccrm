package com.jurassic.jurassiccrm.document.service;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.document.dao.DocumentDao;
import com.jurassic.jurassiccrm.document.dao.DocumentRepository;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.document.service.exceptions.UnauthorisedDocumentOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentDao documentDao;
    private final DocumentRepository documentRepository;
    private final RolesChecker rolesChecker;

    @Autowired
    public DocumentService(DocumentDao documentDao, DocumentRepository documentRepository, RolesChecker rolesChecker) {
        this.documentDao = documentDao;
        this.documentRepository = documentRepository;
        this.rolesChecker = rolesChecker;
    }

    @Transactional
    public Document createDocument(Document document, User author) {
        checkWritePermissions(document.getType(), author);
        return documentDao.createDocument(document, author);
    }

    @Transactional
    public List<? extends Document> getAllDocuments(User user) {
        checkReadPermissions(user);
        if (user.canReadDocuments()) {
            return documentRepository.findAll();
        }
        List<Document> availableDocuments = new ArrayList<>();
        if (user.canReadAviaryPassports()) {
            availableDocuments.addAll(documentDao.getDocuments(DocumentType.AVIARY_PASSPORT));
        }
        if (user.canReadDinosaurPassports()) {
            availableDocuments.addAll(documentDao.getDocuments(DocumentType.DINOSAUR_PASSPORT));
        }
        if (user.canReadResearchData()) {
            availableDocuments.addAll(documentDao.getDocuments(DocumentType.RESEARCH_DATA));
        }
        if (user.canReadTechnologicalMaps()) {
            availableDocuments.addAll(documentDao.getDocuments(DocumentType.TECHNOLOGICAL_MAP));
        }
        if (user.canReadThemeZoneProjects()) {
            availableDocuments.addAll(documentDao.getDocuments(DocumentType.THEME_ZONE_PROJECT));
        }
        return availableDocuments;
    }

    @Transactional
    public Document getDocumentById(Long id, DocumentType documentType, User user) {
        checkReadPermissions(documentType, user);
        return documentDao.getDocument(id, documentType);
    }

    @Transactional
    public Document updateDocument(Long id, Document document, User updater) {
        checkWritePermissions(document.getType(), updater);
        return documentDao.updateDocument(id, document, updater);
    }

    @Transactional
    public Document updateResearchData(Long researchDataId, ResearchData update, User updater) {
        if (update.getAttachment() == null) {
            ResearchData oldData = (ResearchData) documentDao.getDocument(researchDataId, DocumentType.RESEARCH_DATA);
            update.setAttachment(oldData.getAttachment());
        }
        return documentDao.updateDocument(researchDataId, update, updater);
    }

    @Transactional
    public List<? extends Document> getDocuments(DocumentType type, User user) {
        checkReadPermissions(type, user);
        return documentDao.getDocuments(type);
    }

    private void checkWritePermissions(DocumentType type, User user){
        switch (type){
            case THEME_ZONE_PROJECT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_WRITER, Role.THEME_ZONE_PROJECT_WRITER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case DINOSAUR_PASSPORT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_WRITER, Role.DINOSAUR_PASSPORT_WRITER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case TECHNOLOGICAL_MAP:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_WRITER, Role.TECHNOLOGICAL_MAP_WRITER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case AVIARY_PASSPORT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_WRITER, Role.AVIARY_PASSPORT_WRITER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case RESEARCH_DATA:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_WRITER, Role.RESEARCH_DATA_WRITER))
                    throw new UnauthorisedDocumentOperationException();
                break;
        }
    }

    private void checkReadPermissions(DocumentType type, User user){
        switch (type){
            case THEME_ZONE_PROJECT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_READER, Role.THEME_ZONE_PROJECT_READER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case DINOSAUR_PASSPORT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_READER, Role.DINOSAUR_PASSPORT_READER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case TECHNOLOGICAL_MAP:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_READER, Role.TECHNOLOGICAL_MAP_READER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case AVIARY_PASSPORT:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_READER, Role.AVIARY_PASSPORT_READER))
                    throw new UnauthorisedDocumentOperationException();
                break;
            case RESEARCH_DATA:
                if(!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.DOCUMENT_READER, Role.RESEARCH_DATA_READER))
                    throw new UnauthorisedDocumentOperationException();
                break;
        }
    }

    private void checkReadPermissions(User user) {
        if (!rolesChecker.hasAnyRole(user, Role.ADMIN,
                Role.DOCUMENT_READER, Role.THEME_ZONE_PROJECT_READER, Role.DINOSAUR_PASSPORT_READER,
                Role.AVIARY_PASSPORT_READER, Role.TECHNOLOGICAL_MAP_READER, Role.RESEARCH_DATA_READER,
                Role.DOCUMENT_WRITER, Role.THEME_ZONE_PROJECT_WRITER, Role.THEME_ZONE_PROJECT_WRITER,
                Role.AVIARY_PASSPORT_WRITER, Role.DINOSAUR_PASSPORT_WRITER, Role.RESEARCH_DATA_WRITER)
        ) {
            throw new UnauthorisedDocumentOperationException();
        }
    }
}
