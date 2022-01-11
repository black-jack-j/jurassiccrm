package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.themezone.entity.*;
import com.jurassic.jurassiccrm.themezone.repository.ThemeZoneProjectRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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

    private static final String USERNAME = "Test user";
    private static final String SPECIES_NAME_1 = "Test species 1";
    private static final String SPECIES_NAME_2 = "Test species 2";
    private static final String PROJECT_NAME = "Test project";

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("");
        userRepository.save(user);

        speciesRepository.save(new Species(SPECIES_NAME_1));
        speciesRepository.save(new Species(SPECIES_NAME_2));
    }

    @Test
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

        themeZoneProject.addAviaries(AviaryTypes.AVIARY_1, 2L);
        themeZoneProject.addAviaries(AviaryTypes.AVIARY_2, 5L);

        themeZoneProject.addDinosaurs(species1, 1L);
        themeZoneProject.addDinosaurs(species2, 3L);

        themeZoneProject.addDecorations(DecorationTypes.DECORATION_1, 8L);
        themeZoneProject.addDecorations(DecorationTypes.DECORATION_2, 9L);

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

        val deletedAviaries = project.getAviaries().iterator().next();

        project.removeAviaries(deletedAviaries);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 1;
        assert !result.get(0).getAviaries().contains(deletedAviaries);
    }

    @Test
    public void addAviariesOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val newAviaries = new ThemeZoneAviaries(project, AviaryTypes.AVIARY_3, 22L);
        project.addAviaries(newAviaries);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 3;
        assert result.get(0).getAviaries().contains(newAviaries);
    }

    @Test
    public void updateAviaryTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Aviary type is a part of PK, so it cannot be updated in the same instance
        val updatedAviary = project.getAviaries().iterator().next();
        project.removeAviaries(updatedAviary);
        project.addAviaries(AviaryTypes.AVIARY_3, updatedAviary.getNumber());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 2;
        assert result.get(0).getAviaries().stream()
                .anyMatch(a -> a.getAviaryType().equals(AviaryTypes.AVIARY_3));
        assert result.get(0).getAviaries().stream()
                .noneMatch(a -> a.getAviaryType().equals(updatedAviary.getAviaryType()));
    }

    @Test
    public void updateNumberOfAviariesInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
        val updatedAviary = project.getAviaries().iterator().next();
        long oldNumber = updatedAviary.getNumber();
        updatedAviary.setNumber(42L);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 2;
        assert result.get(0).getAviaries().stream().anyMatch(a -> a.getNumber().equals(42L));
        assert result.get(0).getAviaries().stream().noneMatch(a -> a.getNumber().equals(oldNumber));
    }

    @Test
    public void deleteDinosaursOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val deletedDinosaurs = project.getDinosaurs().iterator().next();

        project.removeDinosaurs(deletedDinosaurs);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 1;
        assert !result.get(0).getDinosaurs().contains(deletedDinosaurs);
    }

    @Test
    public void addDinosaursToThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val newSpecies = speciesRepository.save(new Species("New species"));
        val newDinosaurs = new ThemeZoneDinosaurs(project, newSpecies, 22L);
        project.addDinosaurs(newDinosaurs);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 3;
        assert result.get(0).getDinosaurs().contains(newDinosaurs);
    }

    @Test
    public void updateSpeciesInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Aviary type is a part of PK, so it cannot be updated in the same instance
        val updatedDinosaurs = project.getDinosaurs().iterator().next();
        val newSpecies = speciesRepository.save(new Species("New species"));
        project.removeDinosaurs(updatedDinosaurs);
        project.addDinosaurs(newSpecies, updatedDinosaurs.getNumber());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 2;
        assert result.get(0).getDinosaurs().stream()
                .anyMatch(a -> a.getSpecie().equals(newSpecies));
        assert result.get(0).getDinosaurs().stream()
                .noneMatch(a -> a.getSpecie().equals(updatedDinosaurs.getSpecie()));
    }

    @Test
    public void updateNumberOfDinosaursInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
        val updatedDinosaurs = project.getDinosaurs().iterator().next();
        long oldNumber = updatedDinosaurs.getNumber();
        updatedDinosaurs.setNumber(42L);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDinosaurs().size() == 2;
        assert result.get(0).getDinosaurs().stream().anyMatch(a -> a.getNumber().equals(42L));
        assert result.get(0).getDinosaurs().stream().noneMatch(a -> a.getNumber().equals(oldNumber));
    }

    @Test
    public void deleteDecorationsOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val deletedDecorations = project.getDecorations().iterator().next();

        project.removeDecorations(deletedDecorations);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 1;
        assert !result.get(0).getDecorations().contains(deletedDecorations);
    }

    @Test
    public void addDecorationsOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        val newDecorations = new ThemeZoneDecorations(project, DecorationTypes.DECORATION_3, 22L);
        project.addDecorations(newDecorations);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 3;
        assert result.get(0).getDecorations().contains(newDecorations);
    }

    @Test
    public void updateDecorationTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Aviary type is a part of PK, so it cannot be updated in the same instance
        val updatedDecorations = project.getDecorations().iterator().next();
        project.removeDecorations(updatedDecorations);
        project.addDecorations(DecorationTypes.DECORATION_3, updatedDecorations.getNumber());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 2;
        assert result.get(0).getDecorations().stream()
                .anyMatch(a -> a.getDecorationType().equals(DecorationTypes.DECORATION_3));
        assert result.get(0).getDecorations().stream()
                .noneMatch(a -> a.getDecorationType().equals(updatedDecorations.getDecorationType()));
    }

    @Test
    public void updateNumberOfDecorationsInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
        val updatedDecorations = project.getDecorations().iterator().next();
        long oldNumber = updatedDecorations.getNumber();
        updatedDecorations.setNumber(42L);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 2;
        assert result.get(0).getDecorations().stream().anyMatch(a -> a.getNumber().equals(42L));
        assert result.get(0).getDecorations().stream().noneMatch(a -> a.getNumber().equals(oldNumber));
    }
}
