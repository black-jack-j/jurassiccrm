package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.document.dao.exception.DocumentDaoException;
import com.jurassic.jurassiccrm.document.model.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DocumentDao {
    private final DinosaurPassportRepository dinosaurPassportRepository;
    private final AviaryPassportRepository aviaryPassportRepository;
    private final ResearchDataRepository researchDataRepository;
    private final TechnologicalMapRepository technologicalMapRepository;
    private final ThemeZoneProjectRepository themeZoneProjectRepository;

    public Document createDocument(Document document, User author) {
        if (checkDocumentExistsByName(document.getName()))
            throw DocumentDaoException.duplicateDocumentName(document.getName());
        document.setAuthor(author);
        document.setLastUpdater(author);
        val timestamp = Timestamp.valueOf(LocalDateTime.now());
        document.setCreated(timestamp);
        document.setLastUpdate(timestamp);
        return saveOrUpdateDocument(document);
    }

    private boolean checkDocumentExistsByName(String name) {
        return dinosaurPassportRepository.existsByName(name) ||
                aviaryPassportRepository.existsByName(name) ||
                researchDataRepository.existsByName(name) ||
                technologicalMapRepository.existsByName(name) ||
                themeZoneProjectRepository.existsByName(name);
    }

    public Document updateDocument(Long id, Document newDocument, User updater) {
        if (!checkDocumentExistsById(id)) {
            throw DocumentDaoException.documentIdNotFound(id);
        }
        newDocument.setId(id);
        newDocument.setLastUpdater(updater);
        val timestamp = Timestamp.valueOf(LocalDateTime.now());
        newDocument.setLastUpdate(timestamp);
        return saveOrUpdateDocument(newDocument);
    }

    private boolean checkDocumentExistsById(Long id) {
        return dinosaurPassportRepository.existsById(id) ||
                aviaryPassportRepository.existsById(id) ||
                researchDataRepository.existsById(id) ||
                technologicalMapRepository.existsById(id) ||
                themeZoneProjectRepository.existsById(id);
    }

    private Document saveOrUpdateDocument(Document document) {
        switch (document.getType()) {

            case THEME_ZONE_PROJECT:
                return themeZoneProjectRepository.save((ThemeZoneProject) document);
            case DINOSAUR_PASSPORT:
                return dinosaurPassportRepository.save((DinosaurPassport) document);
            case TECHNOLOGICAL_MAP:
                return technologicalMapRepository.save((TechnologicalMap) document);
            case AVIARY_PASSPORT:
                return aviaryPassportRepository.save((AviaryPassport) document);
            case RESEARCH_DATA:
                return researchDataRepository.save((ResearchData) document);
            default:
                throw DocumentDaoException.unsupportedDocumentType(document.getType());
        }
    }

    public List<? extends Document> getDocuments(DocumentType type) throws DocumentDaoException {
        switch (type) {
            case THEME_ZONE_PROJECT:
                return themeZoneProjectRepository.findAll();
            case DINOSAUR_PASSPORT:
                return dinosaurPassportRepository.findAll();
            case TECHNOLOGICAL_MAP:
                return technologicalMapRepository.findAll();
            case AVIARY_PASSPORT:
                return aviaryPassportRepository.findAll();
            case RESEARCH_DATA:
                return researchDataRepository.findAll();
            default:
                throw DocumentDaoException.unsupportedDocumentType(type);
        }
    }

    @Autowired
    public DocumentDao(
            DinosaurPassportRepository dinosaurPassportRepository,
            AviaryPassportRepository aviaryPassportRepository,
            ResearchDataRepository researchDataRepository,
            TechnologicalMapRepository technologicalMapRepository,
            ThemeZoneProjectRepository themeZoneProjectRepository) {
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.researchDataRepository = researchDataRepository;
        this.technologicalMapRepository = technologicalMapRepository;
        this.themeZoneProjectRepository = themeZoneProjectRepository;
    }
}
