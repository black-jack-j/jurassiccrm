package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.entity.AviaryTypes;
import com.jurassic.jurassiccrm.document.entity.DocumentType;
import com.jurassic.jurassiccrm.species.entity.Species;
import com.jurassic.jurassiccrm.species.repository.SpeciesRepository;
import com.jurassic.jurassiccrm.themezone.entity.DecorationTypes;
import com.jurassic.jurassiccrm.themezone.entity.ThemeZoneProject;
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

        themeZoneProject.addAviaries(AviaryTypes.OPEN_AIR, 2);
        themeZoneProject.addAviaries(AviaryTypes.INDOORS, 5);

        themeZoneProject.addDinosaurs(species1, 1);
        themeZoneProject.addDinosaurs(species2, 3);

        themeZoneProject.addDecorations(DecorationTypes.STONE, 8);
        themeZoneProject.addDecorations(DecorationTypes.PLANT, 9);

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
    public void addAviariesOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        project.addAviaries(AviaryTypes.WATER_POOL, 22);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 3;
        assert result.get(0).getAviaries().get(AviaryTypes.WATER_POOL) == 22;
    }

    @Test
    public void updateAviaryTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Aviary type is a part of PK, so it cannot be updated in the same instance
        val updatedAviary = project.getAviaries().entrySet().iterator().next();
        project.removeAviaries(updatedAviary.getKey());
        project.addAviaries(AviaryTypes.WATER_POOL, updatedAviary.getValue());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 2;
        assert result.get(0).getAviaries().get(AviaryTypes.WATER_POOL).equals(updatedAviary.getValue());
        assert !result.get(0).getAviaries().containsKey(updatedAviary.getKey());
    }

    @Test
    public void updateNumberOfAviariesInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
        val updatedAviary = project.getAviaries().entrySet().iterator().next();
        int oldNumber = updatedAviary.getValue();
        project.addAviaries(updatedAviary.getKey(), 42);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getAviaries().size() == 2;
        assert result.get(0).getAviaries().get(updatedAviary.getKey()) == 42;
        assert !result.get(0).getAviaries().containsValue(oldNumber);
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

        //Aviary type is a part of PK, so it cannot be updated in the same instance
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
    public void updateNumberOfDinosaursInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
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

        project.addDecorations(DecorationTypes.SKELETON, 22);

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 3;
        assert result.get(0).getDecorations().get(DecorationTypes.SKELETON) == 22;
    }

    @Test
    public void updateDecorationTypeOfThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Aviary type is a part of PK, so it cannot be updated in the same instance
        val updatedDecorations = project.getDecorations().entrySet().iterator().next();
        project.removeDecorations(updatedDecorations.getKey());
        project.addDecorations(DecorationTypes.SKELETON, updatedDecorations.getValue());

        themeZoneProjectRepository.save(project);
        val result = themeZoneProjectRepository.findAll();
        System.out.println(result);
        assert result.size() == 1;
        assert result.get(0).getDecorations().size() == 2;
        assert result.get(0).getDecorations().get(DecorationTypes.SKELETON).equals(updatedDecorations.getValue());
        assert !result.get(0).getDecorations().containsKey(updatedDecorations.getKey());
    }

    @Test
    public void updateNumberOfDecorationsInThemeZoneProject() {
        testThemeZoneProjectCreation();
        val project = themeZoneProjectRepository.findAll().get(0);

        //Number is NOT a part of PK, so it can be updated in the same instance
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
