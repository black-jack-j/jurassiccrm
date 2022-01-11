package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryPassport;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.aviary.repository.AviaryPassportRepository;
import com.jurassic.jurassiccrm.document.dao.DocumentDao;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.research.entity.Research;
import com.jurassic.jurassiccrm.research.repository.ResearchDataRepository;
import com.jurassic.jurassiccrm.research.repository.ResearchesRepository;
import com.jurassic.jurassiccrm.species.entity.DinosaurPassport;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.DinosaurPassportRepository;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.species.repository.TechnologicalMapRepository;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneProjectRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DocumentDaoTest {
    AviaryPassportRepository aviaryPassportRepository;
    DinosaurPassportRepository dinosaurPassportRepository;
    ResearchDataRepository researchDataRepository;
    TechnologicalMapRepository technologicalMapRepository;
    ThemeZoneProjectRepository themeZoneProjectRepository;
    DocumentDao documentDao;
    @Autowired
    ResearchesRepository researchesRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    UserRepository userRepository;

    private static final String RESEARCH_NAME = "Test research";
    private static final String NEW_RESEARCH_NAME = "New Test research";
    private static final String USERNAME_1 = "Test user 1";
    private static final String USERNAME_2 = "Test user 2";
    private static final String RESEARCHER_NAME_1 = "researcher 1";
    private static final String RESEARCHER_NAME_2 = "researcher 2";
    private static final String SPECIES_NAME_1 = "Test species 1";
    private static final String SPECIES_NAME_2 = "Test species 2";
    private static final String DOCUMENT_NAME = "Test document";

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
        researchesRepository.save(research1);

        Research research2 = new Research();
        research2.setName(NEW_RESEARCH_NAME);
        research2.setGoal("some other goal");
        research2.getResearchers().add(researcher1);
        researchesRepository.save(research2);

        speciesRepository.save(new Species(SPECIES_NAME_1));
        speciesRepository.save(new Species(SPECIES_NAME_2));
    }

    @Test
    void returnNothingIfNothingWasSaved() {
        assert Arrays.stream(DocumentType.values()).allMatch(dt -> documentDao.getDocuments(dt).isEmpty());
    }

    @Test
    void saveAviaryPassport() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);

        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName(DOCUMENT_NAME);
        aviaryPassport.setDescription("test");
        aviaryPassport.setAviaryType(AviaryTypes.OPEN_AIR);
        aviaryPassport.setCode(1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(new Date(System.currentTimeMillis()));
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        documentDao.createDocument(aviaryPassport, user);

        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT);
        assert saved.size() == 1;
    }

    @Test
    void saveDinosaurPassport(){
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val species = speciesRepository.findByName(SPECIES_NAME_1).orElse(null);

        val dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setSpecies(species);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(new Timestamp(System.currentTimeMillis()));
        dinosaurPassport.setRevisionPeriod(1);
        dinosaurPassport.setStatus("Done");

        documentDao.createDocument(dinosaurPassport, user);

        val saved = documentDao.getDocuments(DocumentType.DINOSAUR_PASSPORT);
        assert saved.size() == 1;
    }

    @Test
    void fetchAviaryPassportWithSpecificFields() {
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);

        AviaryPassport aviaryPassport = new AviaryPassport();
        aviaryPassport.setName(DOCUMENT_NAME);
        aviaryPassport.setDescription("test");
        aviaryPassport.setAviaryType(AviaryTypes.OPEN_AIR);
        aviaryPassport.setCode(1111L);
        aviaryPassport.setDescription("testDesc");
        aviaryPassport.setBuiltDate(new Date(System.currentTimeMillis()));
        aviaryPassport.setRevisionPeriod(1);
        aviaryPassport.setStatus("Done");
        documentDao.createDocument(aviaryPassport, user);

        val saved = (AviaryPassport) documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert saved.getAviaryType().equals(aviaryPassport.getAviaryType());
    }

    @Test
    void fetchDinosaurPassportSpecificFields(){
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val species = speciesRepository.findByName(SPECIES_NAME_1).orElse(null);

        val dinosaurPassport = new DinosaurPassport();
        dinosaurPassport.setName("test");
        dinosaurPassport.setDescription("test");
        dinosaurPassport.setAuthor(user);
        dinosaurPassport.setSpecies(species);
        dinosaurPassport.setDinosaurName("test");
        dinosaurPassport.setWeight(123.0);
        dinosaurPassport.setHeight(321.0);
        dinosaurPassport.setDescription("testDesc");
        dinosaurPassport.setIncubated(new Timestamp(System.currentTimeMillis()));
        dinosaurPassport.setRevisionPeriod(1);
        dinosaurPassport.setStatus("Done");

        documentDao.createDocument(dinosaurPassport, user);

        val saved = (DinosaurPassport) documentDao.getDocuments(DocumentType.DINOSAUR_PASSPORT).get(0);
        assert saved.getSpecies().equals(species);
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
    void automaticallySetUserAndTimestampFieldsWhileSavingDocument(){
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;

        saveAviaryPassport();
        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert saved.getAuthor().equals(user);
        assert saved.getLastUpdater().equals(user);
        assert saved.getCreated().equals(saved.getLastUpdate());
        assert saved.getCreated().after(Timestamp.valueOf(LocalDateTime.now().minusSeconds(5)));
    }

    @Test
    void failSavingDocumentOfSameTypeIfDocumentWithThisNameAlreadyExists(){
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;

        try{
            val newPassport = new AviaryPassport();
            newPassport.setName(DOCUMENT_NAME);
            documentDao.createDocument(newPassport, user);
            fail("should have thrown");
        } catch (IllegalStateException e){
        } catch (Throwable t){
            fail("unexpected throwable type. Should be IllegalStateException");
        }
    }

    @Test
    void failSavingDocumentOfOtherTypeIfDocumentWithSameNameAlreadyExists(){
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        assert user != null;

        try{
            val newPassport = new DinosaurPassport();
            newPassport.setName(DOCUMENT_NAME);
            documentDao.createDocument(newPassport, user);
            fail("should have thrown");
        } catch (IllegalStateException e){
        } catch (Throwable t){
            fail("unexpected throwable type. Should be IllegalStateException");
        }
    }

    @Test
    void updateExistingAviaryPassport(){
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
    void automaticallySetUserAndTimestampFieldsWhileUpdatingDocument(){
        val user1 = userRepository.findByUsername(USERNAME_1).orElse(null);
        val user2 = userRepository.findByUsername(USERNAME_2).orElse(null);
        assert user1 != null;
        assert user2 != null;

        val beforeSaving = Timestamp.valueOf(LocalDateTime.now());
        updateExistingAviaryPassport();
        val saved = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert saved.getAuthor().equals(user1);
        assert saved.getLastUpdater().equals(user2);
        assert saved.getLastUpdate().after(saved.getCreated());
        assert saved.getCreated().after(beforeSaving);
    }

    @Test
    void failUpdatingDocumentOfOtherTypeIfDocumentWithGivenIdDoesntExist(){
        saveAviaryPassport();
        val user = userRepository.findByUsername(USERNAME_1).orElse(null);
        val savedDocument = documentDao.getDocuments(DocumentType.AVIARY_PASSPORT).get(0);
        assert user != null;
        assert savedDocument != null;

        try{
            documentDao.updateDocument(666L, savedDocument, user);
            fail("should have thrown");
        } catch (IllegalStateException e){
        } catch (Throwable t){
            fail("unexpected throwable type. Should be IllegalStateException");
        }
    }

    @Autowired
    public DocumentDaoTest(
            AviaryPassportRepository aviaryPassportRepository,
            DinosaurPassportRepository dinosaurPassportRepository,
            ResearchDataRepository researchDataRepository,
            TechnologicalMapRepository technologicalMapRepository,
            ThemeZoneProjectRepository themeZoneProjectRepository) {
        this.aviaryPassportRepository = aviaryPassportRepository;
        this.dinosaurPassportRepository = dinosaurPassportRepository;
        this.researchDataRepository = researchDataRepository;
        this.technologicalMapRepository = technologicalMapRepository;
        this.themeZoneProjectRepository = themeZoneProjectRepository;
        this.documentDao = new DocumentDao(
                dinosaurPassportRepository,
                aviaryPassportRepository,
                researchDataRepository,
                technologicalMapRepository,
                themeZoneProjectRepository);
    }
}
