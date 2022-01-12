package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.decoration.repository.DecorationTypeRepository;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.species.model.Species;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.themezone.model.ThemeZoneProject;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneProjectRepository;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class ThemeZoneProjectTest {

    @Autowired
    ThemeZoneProjectRepository themeZoneProjectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpeciesRepository speciesRepository;

    @Autowired
    AviaryTypeRepository aviaryTypeRepository;

    @Autowired
    DecorationTypeRepository decorationTypeRepository;

    private static final String USERNAME = "Test user";
    private static final String SPECIES_NAME_1 = "Test species 1";
    private static final String SPECIES_NAME_2 = "Test species 2";
    private static final String PROJECT_NAME = "Test project";
    private static final String AVIARY_TYPE_1 = "Test aviary type 1";
    private static final String AVIARY_TYPE_2 = "Test aviary type 2";
    private static final String DECORATIONS_TYPE_1 = "Test decorations type 1";
    private static final String DECORATIONS_TYPE_2 = "Test decorations type 2";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        speciesRepository.save(new Species(SPECIES_NAME_1));
        speciesRepository.save(new Species(SPECIES_NAME_2));

        aviaryTypeRepository.save(new AviaryType(AVIARY_TYPE_1));
        aviaryTypeRepository.save(new AviaryType(AVIARY_TYPE_2));

        decorationTypeRepository.save(new DecorationType(DECORATIONS_TYPE_1));
        decorationTypeRepository.save(new DecorationType(DECORATIONS_TYPE_2));
    }

    @Test
    public void testThemeZoneProjectCreation() {
        User user = userRepository.findByUsername(USERNAME).orElse(null);
        Species species1 = speciesRepository.findByName(SPECIES_NAME_1).orElse(null);
        Species species2 = speciesRepository.findByName(SPECIES_NAME_2).orElse(null);
        AviaryType aviaryType1 = aviaryTypeRepository.findByName(AVIARY_TYPE_1).orElse(null);
        AviaryType aviaryType2 = aviaryTypeRepository.findByName(AVIARY_TYPE_2).orElse(null);
        DecorationType decorationType1 = decorationTypeRepository.findByName(DECORATIONS_TYPE_1).orElse(null);
        DecorationType decorationType2 = decorationTypeRepository.findByName(DECORATIONS_TYPE_2).orElse(null);


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

        themeZoneProject.addAviaries(aviaryType1, 2);
        themeZoneProject.addAviaries(aviaryType2, 5);

        themeZoneProject.addDinosaurs(species1, 1);
        themeZoneProject.addDinosaurs(species2, 3);

        themeZoneProject.addDecorations(decorationType1, 8);
        themeZoneProject.addDecorations(decorationType2, 9);

        themeZoneProjectRepository.save(themeZoneProject);

        val foundProjects = themeZoneProjectRepository.findAll();
        assert themeZoneProject.getType() == DocumentType.THEME_ZONE_PROJECT;
        assert foundProjects.contains(themeZoneProject);
    }

    @Test
    public void updateFieldsOfBaseDocument() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        project.setName("New name");
        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getName().equals("New name");
    }

    @Test
    public void updatePlainFieldsOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        project.setProjectName("New name");
        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getProjectName().equals("New name");
    }

    @Test
    public void deleteAviariesOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val deletedAviaries = project.getAviaries().keySet().iterator().next();

        project.removeAviaries(deletedAviaries);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 1;
        assert !result.get(0).getAviaries().containsKey(deletedAviaries);
    }

    @Test
    public void addAviariesToThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newAviaryType = aviaryTypeRepository.save(new AviaryType("new type"));

        project.addAviaries(newAviaryType, 22);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(3, result.get(0).getAviaries().size());
        Assertions.assertEquals(22, result.get(0).getAviaries().get(newAviaryType));
    }

    @Test
    public void updateAviaryTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newAviaryType = aviaryTypeRepository.save(new AviaryType("new type"));
        
        val updatedAviary = project.getAviaries().entrySet().iterator().next();
        project.removeAviaries(updatedAviary.getKey());
        project.addAviaries(newAviaryType, updatedAviary.getValue());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2, result.get(0).getAviaries().size());
        Assertions.assertEquals(updatedAviary.getValue(),result.get(0).getAviaries().get(newAviaryType));
        Assertions.assertFalse(result.get(0).getAviaries().containsKey(updatedAviary.getKey()));
    }

    @Test
    public void failUpdateAviaryTypeOfThemeZoneProjectIfAviaryTypeDoesntExist() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newAviaryType = new AviaryType("new type");

        val updatedAviary = project.getAviaries().entrySet().iterator().next();
        project.removeAviaries(updatedAviary.getKey());
        project.addAviaries(newAviaryType, updatedAviary.getValue());

        Assertions.assertThrows(Exception.class, 
                () -> themeZoneProjectRepository.saveAndFlush(project));
    }

    @Test
    public void updateNumberOfAviariesInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        
        val updatedAviary = project.getAviaries().entrySet().iterator().next();
        int oldNumber = updatedAviary.getValue();
        project.addAviaries(updatedAviary.getKey(), 42);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2, result.get(0).getAviaries().size());
        Assertions.assertEquals(42, result.get(0).getAviaries().get(updatedAviary.getKey()));
        Assertions.assertFalse(result.get(0).getAviaries().containsValue(oldNumber));
    }

    @Test
    public void deleteDinosaursOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val deletedDinosaurs = project.getDinosaurs().keySet().iterator().next();

        project.removeDinosaurs(deletedDinosaurs);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 1;
        assert !result.get(0).getDinosaurs().containsKey(deletedDinosaurs);
    }

    @Test
    public void addDinosaursToThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val newSpecies = speciesRepository.save(new Species("New species"));
        project.addDinosaurs(newSpecies, 22);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 3;
        assert result.get(0).getDinosaurs().get(newSpecies) == 22;
    }

    @Test
    public void updateSpeciesInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        
        val updatedDinosaurs = project.getDinosaurs().entrySet().iterator().next();
        val newSpecies = speciesRepository.save(new Species("New species"));
        project.removeDinosaurs(updatedDinosaurs.getKey());
        project.addDinosaurs(newSpecies, updatedDinosaurs.getValue());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 2;
        assert result.get(0).getDinosaurs().get(newSpecies).equals(updatedDinosaurs.getValue());
        assert !result.get(0).getDinosaurs().containsKey(updatedDinosaurs.getKey());
    }

    @Test
    public void failUpdateSpeciesInThemeZoneProjectIfSpeciesDoesntExist() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val updatedDinosaurs = project.getDinosaurs().entrySet().iterator().next();
        val newSpecies = new Species("New species");
        project.removeDinosaurs(updatedDinosaurs.getKey());
        project.addDinosaurs(newSpecies, updatedDinosaurs.getValue());

        Assertions.assertThrows(Exception.class, 
                () -> themeZoneProjectRepository.saveAndFlush(project));
    }
    
    @Test
    public void updateNumberOfDinosaursInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        
        val updatedDinosaurs = project.getDinosaurs().entrySet().iterator().next();
        val oldNumber = updatedDinosaurs.getValue();
        project.addDinosaurs(updatedDinosaurs.getKey(), 42);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 2;
        assert result.get(0).getDinosaurs().containsValue(42);
        assert !result.get(0).getDinosaurs().containsValue(oldNumber);
    }

    @Test
    public void deleteDecorationsOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val deletedDecorations = project.getDecorations().keySet().iterator().next();

        project.removeDecorations(deletedDecorations);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 1;
        assert !result.get(0).getDecorations().containsKey(deletedDecorations);
    }

    @Test
    public void addDecorationsOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newDecorationType = decorationTypeRepository.save(new DecorationType("new type"));

        project.addDecorations(newDecorationType, 22);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(3, result.get(0).getDecorations().size());
        Assertions.assertEquals(22, result.get(0).getDecorations().get(newDecorationType));
    }

    @Test
    public void updateDecorationTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newDecorationType = decorationTypeRepository.save(new DecorationType("new type"));

        val updatedDecorations = project.getDecorations().entrySet().iterator().next();
        project.removeDecorations(updatedDecorations.getKey());
        project.addDecorations(newDecorationType, updatedDecorations.getValue());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(2, result.get(0).getDecorations().size());
        Assertions.assertEquals(updatedDecorations.getValue(), result.get(0).getDecorations().get(newDecorationType));
        Assertions.assertFalse(result.get(0).getDecorations().containsKey(updatedDecorations.getKey()));
    }

    @Test
    public void failUpdateDecorationTypeOfThemeZoneProjectIfDecorationTypeDoesntExist() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);
        val newDecorationType = new DecorationType("new type");

        val updatedDecorations = project.getDecorations().entrySet().iterator().next();
        project.removeDecorations(updatedDecorations.getKey());
        project.addDecorations(newDecorationType, updatedDecorations.getValue());

        Assertions.assertThrows(Exception.class,
                () -> themeZoneProjectRepository.saveAndFlush(project));
    }

    @Test
    public void updateNumberOfDecorationsInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val updatedDecorations = project.getDecorations().entrySet().iterator().next();
        int oldNumber = updatedDecorations.getValue();
        updatedDecorations.setValue(42);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 2;
        assert result.get(0).getDecorations().get(updatedDecorations.getKey()) == 42;
        assert !result.get(0).getDecorations().containsValue(oldNumber);
    }
}
