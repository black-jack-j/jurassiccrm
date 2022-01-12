package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.aviary.dao.AviaryPassportRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryPassport;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.research.model.ResearchData;
import com.jurassic.jurassiccrm.research.repository.ResearchDataRepository;
import com.jurassic.jurassiccrm.species.model.DinosaurPassport;
import com.jurassic.jurassiccrm.species.model.TechnologicalMap;
import com.jurassic.jurassiccrm.species.repository.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.themezone.model.ThemeZoneProject;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneProjectRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DocumentDao {
    private final DinosaurPassportRepository dinosaurPassportRepository;
    private final AviaryPassportRepository aviaryPassportRepository;
    private final ResearchDataRepository researchDataRepository;
    private final TechnologicalMapRepository technologicalMapRepository;
    private final ThemeZoneProjectRepository themeZoneProjectRepository;

    public void createDocument(Document document, User author) {
        if (checkDocumentExistsByName(document.getName()))
            throw new IllegalStateException(String.format("Document with name %s already exists", document.getName()));
        document.setAuthor(author);
        document.setLastUpdater(author);
        val timestamp = Timestamp.valueOf(LocalDateTime.now());
        document.setCreated(timestamp);
        document.setLastUpdate(timestamp);
        saveOrUpdateDocument(document);
    }

    private boolean checkDocumentExistsByName(String name) {
        return dinosaurPassportRepository.existsByName(name) ||
                aviaryPassportRepository.existsByName(name) ||
                researchDataRepository.existsByName(name) ||
                technologicalMapRepository.existsByName(name) ||
                themeZoneProjectRepository.existsByName(name);
    }

    public void updateDocument(Long id, Document newDocument, User updater) {
        if (!checkDocumentExistsById(id)) {
            throw new IllegalStateException(String.format("Can't update Document with Id %d already exists", id));
        }
        newDocument.setId(id);
        newDocument.setLastUpdater(updater);
        val timestamp = Timestamp.valueOf(LocalDateTime.now());
        newDocument.setLastUpdate(timestamp);
        saveOrUpdateDocument(newDocument);
    }

    private boolean checkDocumentExistsById(Long id) {
        return dinosaurPassportRepository.existsById(id) ||
                aviaryPassportRepository.existsById(id) ||
                researchDataRepository.existsById(id) ||
                technologicalMapRepository.existsById(id) ||
                themeZoneProjectRepository.existsById(id);
    }

    private void saveOrUpdateDocument(Document document) {
        switch (document.getType()) {

            case THEME_ZONE_PROJECT:
                themeZoneProjectRepository.save((ThemeZoneProject) document);
                break;
            case DINOSAUR_PASSPORT:
                dinosaurPassportRepository.save((DinosaurPassport) document);
                break;
            case TECHNOLOGICAL_MAP:
                technologicalMapRepository.save((TechnologicalMap) document);
                break;
            case AVIARY_PASSPORT:
                aviaryPassportRepository.save((AviaryPassport) document);
                break;
            case RESEARCH_DATA:
                researchDataRepository.save((ResearchData) document);
                break;
        }
    }

    public List<? extends Document> getDocuments(DocumentType type) {
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
                throw new IllegalArgumentException(String.format("Unknown document type %s", type));
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
