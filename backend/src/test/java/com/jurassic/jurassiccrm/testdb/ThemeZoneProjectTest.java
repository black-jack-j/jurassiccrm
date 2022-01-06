package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.themezone.entity.*;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneRepository;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class ThemeZoneProjectTest {

    @Autowired
    ThemeZoneRepository themeZoneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpeciesRepository speciesRepository;

    private static final String USERNAME = "Test user";
    private static final String SPECIES_NAME_1 = "Test species 1";
    private static final String SPECIES_NAME_2 = "Test species 2";
    private static final String PROJECT_NAME = "Test project";

    @BeforeAll
    public static void init(
            @Autowired UserRepository userRepository,
            @Autowired SpeciesRepository speciesRepository) {
        User user = new User();
        user.setUsername(USERNAME);
        userRepository.save(user);

        Species species1 = new Species();
        species1.setName(SPECIES_NAME_1);
        speciesRepository.save(species1);

        Species species2 = new Species();
        species2.setName(SPECIES_NAME_2);
        speciesRepository.save(species2);
    }

    @AfterAll
    public static void cleanup(
            @Autowired UserRepository userRepository,
            @Autowired SpeciesRepository speciesRepository,
            @Autowired ThemeZoneRepository themeZoneRepository) {
        userRepository.deleteAll();
        speciesRepository.deleteAll();
        themeZoneRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    public void testThemeZoneProjectCreation() {
        User user = userRepository.findByUsername(USERNAME).orElse(null);
        Species species1 = speciesRepository.findByName(SPECIES_NAME_1).orElse(null);
        Species species2 = speciesRepository.findByName(SPECIES_NAME_2).orElse(null);

        val themeZoneProject = new ThemeZoneProject();
        themeZoneProject.setName("test");
        themeZoneProject.setDescription("test");
        themeZoneProject.setAuthor(user);
        themeZoneProject.setCreated(new Timestamp(System.currentTimeMillis()));
        themeZoneProject.setLastUpdater(user);
        themeZoneProject.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        themeZoneProject.setDescription("testDesc");
        themeZoneProject.setProjectName(PROJECT_NAME);
        themeZoneProject.setManager(user);

        val aviary1 = new ThemeZoneAviaries();
        aviary1.setNumber(2L);
        aviary1.setAviaryType(AviaryTypes.AVIARY_1);
        aviary1.setThemeZone(themeZoneProject);

        val aviary2 = new ThemeZoneAviaries();
        aviary2.setNumber(5L);
        aviary2.setAviaryType(AviaryTypes.AVIARY_2);
        aviary2.setThemeZone(themeZoneProject);

        val aviaries = Arrays.asList(aviary1, aviary2);

        themeZoneProject.setAviaries(aviaries);

        val dino1 = new ThemeZoneDinosaurs();
        dino1.setThemeZone(themeZoneProject);
        dino1.setNumber(1L);
        dino1.setSpecie(species1);

        val dino2 = new ThemeZoneDinosaurs();
        dino2.setThemeZone(themeZoneProject);
        dino2.setNumber(3L);
        dino2.setSpecie(species2);

        val dinos = Arrays.asList(dino1, dino2);

        themeZoneProject.setDinosaurs(dinos);

        val decoration1 = new ThemeZoneDecorations();
        decoration1.setThemeZone(themeZoneProject);
        decoration1.setNumber(8L);
        decoration1.setDecorationType(DecorationTypes.DECORATION_1);

        val decoration2 = new ThemeZoneDecorations();
        decoration2.setThemeZone(themeZoneProject);
        decoration2.setNumber(9L);
        decoration2.setDecorationType(DecorationTypes.DECORATION_2);

        val decorations = Arrays.asList(decoration1, decoration2);

        themeZoneProject.setDecorations(decorations);

        themeZoneRepository.save(themeZoneProject);

        val foundProjects = themeZoneRepository.findAll();
        assert themeZoneProject.getType() == DocumentType.THEME_ZONE_PROJECT;
        assert foundProjects.contains(themeZoneProject);
    }
 
    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    public void testThemeZoneProjectDeletion() {
        val themeZoneProject = themeZoneRepository.findAll().stream().filter(d -> d.getProjectName().equals(PROJECT_NAME)).collect(Collectors.toList()).get(0);
        themeZoneRepository.delete(themeZoneProject);
        assert !(themeZoneRepository.findAll().contains(themeZoneProject));
    }
}
