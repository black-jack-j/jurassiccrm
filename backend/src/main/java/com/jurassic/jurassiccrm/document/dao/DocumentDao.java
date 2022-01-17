package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dao.exception.DocumentDaoException;
import com.jurassic.jurassiccrm.document.model.*;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DocumentDao {
    private final DinosaurPassportRepository dinosaurPassportRepository;
    private final AviaryPassportRepository aviaryPassportRepository;
    private final ResearchDataRepository researchDataRepository;
    private final TechnologicalMapRepository technologicalMapRepository;
    private final ThemeZoneProjectRepository themeZoneProjectRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final DecorationTypeRepository decorationTypeRepository;
    private final ResearchRepository researchRepository;
    private final UserRepository userRepository;

    public Document createDocument(Document document, User author) {
        if (checkDocumentExistsByName(document.getName()))
            throw DocumentDaoException.duplicateDocumentName(document.getName());
        document.setAuthor(author);
        document.setLastUpdater(author);
        val creationTime = LocalDateTime.now();
        document.setCreated(creationTime);
        document.setLastUpdate(creationTime);
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
        val updateTime = LocalDateTime.now();
        newDocument.setLastUpdate(updateTime);
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
                return saveThemeZoneProject((ThemeZoneProject) document);
            case DINOSAUR_PASSPORT:
                return saveDinosaurPassport((DinosaurPassport) document);
            case TECHNOLOGICAL_MAP:
                return saveTechnologicalMap((TechnologicalMap) document);
            case AVIARY_PASSPORT:
                return saveAviaryPassport((AviaryPassport) document);
            case RESEARCH_DATA:
                return saveResearchData((ResearchData) document);
            default:
                throw DocumentDaoException.unsupportedDocumentType(document.getType());
        }
    }

    private ResearchData saveResearchData(ResearchData researchData) {
        val research = researchRepository.getOne(researchData.getResearch().getId());
        researchData.setResearch(research);
        return researchDataRepository.saveAndFlush(researchData);
    }

    private AviaryPassport saveAviaryPassport(AviaryPassport aviaryPassport) {
        val aviaryType = aviaryTypeRepository.getOne(aviaryPassport.getAviaryType().getId());
        aviaryPassport.setAviaryType(aviaryType);
        return aviaryPassportRepository.saveAndFlush(aviaryPassport);
    }

    private ThemeZoneProject saveThemeZoneProject(ThemeZoneProject themeZoneProject){
        Map<DinosaurType, Integer> dinosaurs = new HashMap<>();
        Map<AviaryType, Integer> aviaries = new HashMap<>();
        Map<DecorationType, Integer> decorations = new HashMap<>();

        themeZoneProject.getDinosaurs().forEach((key, value) -> {
            val foundType = dinosaurTypeRepository.getOne(key.getId());
            dinosaurs.put(foundType, value);
        });
        themeZoneProject.getAviaries().forEach((key, value) -> {
            val foundType = aviaryTypeRepository.getOne(key.getId());
            aviaries.put(foundType, value);
        });
        themeZoneProject.getDecorations().forEach((key, value) -> {
            val foundType = decorationTypeRepository.getOne(key.getId());
            decorations.put(foundType, value);
        });

        themeZoneProject.setDinosaurs(dinosaurs);
        themeZoneProject.setAviaries(aviaries);
        themeZoneProject.setDecorations(decorations);

        User manager = userRepository.getOne(themeZoneProject.getManager().getId());
        themeZoneProject.setManager(manager);

        return themeZoneProjectRepository.saveAndFlush(themeZoneProject);
    }

    private DinosaurPassport saveDinosaurPassport(DinosaurPassport dinosaurPassport) {
        DinosaurType type = dinosaurTypeRepository.getOne(dinosaurPassport.getDinosaurType().getId());
        dinosaurPassport.setDinosaurType(type);
        return dinosaurPassportRepository.saveAndFlush(dinosaurPassport);
    }

    private TechnologicalMap saveTechnologicalMap(TechnologicalMap technologicalMap){
        val type = dinosaurTypeRepository.getOne(technologicalMap.getDinosaurType().getId());
        technologicalMap.setDinosaurType(type);
        return technologicalMapRepository.saveAndFlush(technologicalMap);
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
            ThemeZoneProjectRepository themeZoneProjectRepository,
            DinosaurTypeRepository dinosaurTypeRepository,
            AviaryTypeRepository aviaryTypeRepository,
            DecorationTypeRepository decorationTypeRepository,
            ResearchRepository researchRepository, UserRepository userRepository) {
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.researchDataRepository = researchDataRepository;
        this.technologicalMapRepository = technologicalMapRepository;
        this.themeZoneProjectRepository = themeZoneProjectRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.decorationTypeRepository = decorationTypeRepository;
        this.researchRepository = researchRepository;
        this.userRepository = userRepository;
    }
}
