package com.jurassic.jurassiccrm.document.dao;

import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.repository.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.research.entity.ResearchData;
import com.jurassic.jurassiccrm.research.repository.ResearchDataRepository;
import com.jurassic.jurassiccrm.species.entity.DinosaurPassport;
import com.jurassic.jurassiccrm.species.entity.TechnologicalMap;
import com.jurassic.jurassiccrm.species.repository.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentDao {
    private final DinosaurPassportRepository dinosaurPassportRepository;
    private final AviaryPassportRepository aviaryPassportRepository;
    private final ResearchDataRepository researchDataRepository;
    private final TechnologicalMapRepository technologicalMapRepository;
    private final ThemeZoneRepository themeZoneRepository;

    public void saveDocument(Document document) {
        switch (document.getType()) {

            case THEME_ZONE_PROJECT:
                themeZoneRepository.save((ThemeZoneProject) document);
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
                return themeZoneRepository.findAll();
            case DINOSAUR_PASSPORT:
                return dinosaurPassportRepository.findAll();
            case TECHNOLOGICAL_MAP:
                return technologicalMapRepository.findAll();
            case AVIARY_PASSPORT:
                return aviaryPassportRepository.findAll();
            case RESEARCH_DATA:
                return researchDataRepository.findAll();
        }
        return new ArrayList<>();
    }

    @Autowired
    public DocumentDao(
            DinosaurPassportRepository dinosaurPassportRepository,
            AviaryPassportRepository aviaryPassportRepository,
            ResearchDataRepository researchDataRepository,
            TechnologicalMapRepository technologicalMapRepository,
            ThemeZoneRepository themeZoneRepository) {
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.researchDataRepository = researchDataRepository;
        this.technologicalMapRepository = technologicalMapRepository;
        this.themeZoneRepository = themeZoneRepository;
    }
}
