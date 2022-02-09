package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.dao.*;
import com.jurassic.jurassiccrm.document.dao.exception.DocumentDaoException;
import com.jurassic.jurassiccrm.document.model.AviaryPassport;
import com.jurassic.jurassiccrm.document.model.DinosaurPassport;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@DataJpaTest
public class DocumentDaoTest {
    AviaryPassportRepository aviaryPassportRepository;
    DinosaurPassportRepository dinosaurPassportRepository;
    ResearchDataRepository researchDataRepository;
    TechnologicalMapRepository technologicalMapRepository;
    ThemeZoneProjectRepository themeZoneProjectRepository;
    private final DocumentDao documentDao;
    private final ResearchRepository researchRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;
    private final UserRepository userRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    @Autowired
    DecorationTypeRepository decorationTypeRepository;

    private static final String RESEARCH_NAME = "Test research";
    private static final String NEW_RESEARCH_NAME = "New Test research";
    private static final String USERNAME_1 = "Test user 1";
    private static final String USERNAME_2 = "Test user 2";
    private static final String RESEARCHER_NAME_1 = "researcher 1";
    private static final String RESEARCHER_NAME_2 = "researcher 2";
    private static final String DINOSAUR_TYPE_NAME_1 = "Test dinosaur type 1";
    private static final String DINOSAUR_TYPE_NAME_2 = "Test dinosaur type 2";
    private static final String DOCUMENT_NAME = "Test document";
    private static final String AVIARY_TYPE_1 = "Aviary type 1";
    private static final String AVIARY_TYPE_2 = "Aviary type 2";

    @BeforeEach
    public void init() {
        User user1 = new User();
        user1.setUsername(USERNAME_1);
        user1.setPassword("");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername(USERNAME_2);
        user2.setPassword("");
        userRepository.save(user2);

        User researcher1 = new User();
        researcher1.setUsername(RESEARCHER_NAME_1);
        researcher1.setPassword("");
        User researcher2 = new User();
        researcher2.setUsername(RESEARCHER_NAME_2);
        researcher2.setPassword("");
        Set<User> researchers = new HashSet<>(userRepository.saveAll(Arrays.asList(researcher1, researcher2)));

        Research research1 = new Research();
        research1.setName(RESEARCH_NAME);
        research1.setGoal("some goal");
        research1.setResearchers(researchers);
        researchRepository.save(research1);

        Research research2 = new Research();
        research2.setName(NEW_RESEARCH_NAME);
        research2.setGoal("some other goal");
        research2.getResearchers().add(researcher1);
        researchRepository.save(research2);

        dinosaurTypeRepository.save(new DinosaurType(DINOSAUR_TYPE_NAME_1));
        dinosaurTypeRepository.save(new DinosaurType(DINOSAUR_TYPE_NAME_2));

        aviaryTypeRepository.save(new AviaryType(AVIARY_TYPE_1));
        aviaryTypeRepository.save(new AviaryType(AVIARY_TYPE_2));
    }

    @Test
    void returnNothingIfNothingWasSaved() {
        assert Arrays.stream(DocumentType.values()).allMatch(dt -> documentDao.getDocuments(dt).isEmpty());
    }

    @Test
    void saveAviaryPassport() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val aviaryType = aviaryTypeRepository.findByName(AVIARY_TYPE_1).orElse(null);

        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName(DOCUMENT_NAME);
        aviaryPassport.setDescription("test");
        aviaryPassport.setAviaryType(aviaryType);
        aviaryPassport.setCode(""+1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(LocalDate.now());
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        aviaryPassport.setSquare(1L);
        documentDao.createDocument(aviaryPassport, user);

        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT);
        Assertions.assertEquals(1, saved.size());
    }

    @Test
    void saveDinosaurPassport() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val dinosaurType = dinosaurTypeRepository.findByName(DINOSAUR_TYPE_NAME_1).orElse(null);

        val dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setDinosaurType(dinosaurType);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(LocalDate.now());
        dinosaurPassport.setRevisionPeriod(1);
        dinosaurPassport.setStatus("Done");

        documentDao.createDocument(dinosaurPassport, user);

        val saved = documentDao.getDocuments(DocumentType.DINOSAUR_PASSPORT);
        Assertions.assertEquals(1, saved.size());
    }

    @Test
    void fetchAviaryPassportWithSpecificFields() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val aviaryType = aviaryTypeRepository.findByName(AVIARY_TYPE_2).orElse(null);

        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName(DOCUMENT_NAME);
        aviaryPassport.setDescription("test");
        aviaryPassport.setAviaryType(aviaryType);
        aviaryPassport.setCode(""+1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(LocalDate.now());
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        aviaryPassport.setSquare(123L);
        documentDao.createDocument(aviaryPassport, user);

        val saved = (AviaryPassport) documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        Assertions.assertEquals(aviaryPassport.getAviaryType(), saved.getAviaryType());
    }

    @Test
    void fetchDinosaurPassportSpecificFields() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val dinosaurType = dinosaurTypeRepository.findByName(DINOSAUR_TYPE_NAME_1).orElse(null);

        val dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setDinosaurType(dinosaurType);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(LocalDate.now());
        dinosaurPassport.setRevisionPeriod(1);
        dinosaurPassport.setStatus("Done");

        documentDao.createDocument(dinosaurPassport, user);

        val saved = (DinosaurPassport) documentDao.getDocuments(DocumentType.DINOSAUR_PASSPORT).get(0);
        Assertions.assertEquals(dinosaurType, saved.getDinosaurType());
    }

    @Test
    void dontSaveAnythingElseSavingAviaryPassport() {
        saveAviaryPassport();
        val otherTypes = Arrays.stream(DocumentType.values()).filter(dt -> !dt.equals(DocumentType.AVIARY_PASSPORT)).collect(Collectors.toSet());
        assert otherTypes.stream().allMatch(t -> documentDao.getDocuments(t).isEmpty());
    }

    @Test
    void dontSaveAnythingElseSavingDinosaurPassport() {
        saveDinosaurPassport();
        val otherTypes = Arrays.stream(DocumentType.values()).filter(dt -> !dt.equals(DocumentType.DINOSAUR_PASSPORT)).collect(Collectors.toSet());
        assert otherTypes.stream().allMatch(t -> documentDao.getDocuments(t).isEmpty());
    }

    @Test
    void automaticallySetUserAndTimestampFieldsWhileSavingDocument() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;

        saveAviaryPassport();
        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert saved.getAuthor().equals(user);
        assert saved.getLastUpdater().equals(user);
        assert saved.getCreated().equals(saved.getLastUpdate());
        assert saved.getCreated().isAfter(LocalDateTime.now().minusSeconds(5));
    }

    @Test
    void failSavingDocumentOfSameTypeIfDocumentWithThisNameAlreadyExists() {
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;
            val newPassport = new AviaryPassport();
            newPassport.setName(DOCUMENT_NAME);
            Assertions.assertThrows(DocumentDaoException.class, () -> documentDao.createDocument(newPassport, user));
    }

    @Test
    void failSavingDocumentOfOtherTypeIfDocumentWithSameNameAlreadyExists() {
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;

        val newPassport = new DinosaurPassport();
        newPassport.setName(DOCUMENT_NAME);
        Assertions.assertThrows(DocumentDaoException.class, () -> documentDao.createDocument(newPassport, user));
    }

    @Test
    void updateExistingAviaryPassport() {
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_2).orElse(null);
        assert user != null;

        val savedDocument = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        savedDocument.setName("New name");
        documentDao.updateDocument(savedDocument.getId(), savedDocument, user);

        val updated = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT);
        assert updated.size() == 1;
        assert updated.get(0).getName().equals(savedDocument.getName());
    }

    @Test
    void automaticallySetUserAndTimestampFieldsWhileUpdatingDocument() {
        val user1 = userRepository.findByUsername(USERNAME_1).orElse(null);
        val user2 = userRepository.findByUsername(USERNAME_2).orElse(null);
        assert user1 != null;
        assert user2 != null;

        val beforeSaving = LocalDateTime.now();
        updateExistingAviaryPassport();
        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert saved.getAuthor().equals(user1);
        assert saved.getLastUpdater().equals(user2);
        assert saved.getLastUpdate().isAfter(saved.getCreated());
        assert saved.getCreated().isAfter(beforeSaving);
    }

    @Test
    void failUpdatingDocumentOfOtherTypeIfDocumentWithGivenIdDoesntExist() {
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val savedDocument = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert user != null;
        assert savedDocument != null;
        Assertions.assertThrows(DocumentDaoException.class,
                () -> documentDao.updateDocument(666L, savedDocument, user));
    }

    @Autowired
    public DocumentDaoTest(
            AviaryPassportRepository aviaryPassportRepository,
            DinosaurPassportRepository dinosaurPassportRepository,
            ResearchDataRepository researchDataRepository,
            TechnologicalMapRepository technologicalMapRepository,
            ThemeZoneProjectRepository themeZoneProjectRepository,
            DocumentRepository documentRepository,
            ResearchRepository researchRepository,
            DinosaurTypeRepository dinosaurTypeRepository,
            UserRepository userRepository,
            AviaryTypeRepository aviaryTypeRepository) {
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.researchDataRepository = researchDataRepository;
        this.technologicalMapRepository = technologicalMapRepository;
        this.themeZoneProjectRepository = themeZoneProjectRepository;
        this.researchRepository = researchRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
        this.userRepository = userRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.documentDao = new DocumentDao(
                dinosaurPassportRepository,
                aviaryPassportRepository,
                researchDataRepository,
                technologicalMapRepository,
                themeZoneProjectRepository,
                documentRepository,
                this.dinosaurTypeRepository,
                this.aviaryTypeRepository,
                decorationTypeRepository, this.researchRepository, this.userRepository);
    }
}
