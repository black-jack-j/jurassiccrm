package com.jurassic.jurassiccrm.testcontrollers;

import com.jayway.jsonpath.JsonPath;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static Long dinosaurTypeId = 1L;
    private static Long decorationTypeId = 1L;
    private static Long aviaryTypeId = 1L;
    private static Long managerId = 1L;
    private static Long researchId = 1L;

    private static final String LOGS_URL = "/api/logs";
    private static final String UPDATED_NAME = "updated name";
    private static Long updatedDinosaurTypeId = 1L;

    private static String dinosaurPassportJson() {
        return "{\"name\": \"some map\"," +
                "\"description\": \"some description\"," +
                "\"dinosaurName\": \"some dinosaur\"," +
                "\"dinosaurTypeId\": " + dinosaurTypeId + "," +
                "\"weight\": 10.5," +
                "\"height\": 20.25," +
                "\"incubated\": \"2021-01-01\"," +
                "\"revisionPeriod\": 10," +
                "\"status\": \"some status\"}";
    }

    private static String themeZoneProjectJson() {
        return "{\"name\": \"some project\"," +
                "\"description\": \"some description\"," +
                "\"managerId\": " + managerId + "," +
                "\"projectName\": \"some project name\"," +
                "\"dinosaurs\": {\"" + dinosaurTypeId + "\":2}," +
                "\"aviaries\": {\"" + aviaryTypeId + "\":1}," +
                "\"decorations\": {\"" + decorationTypeId + "\":6}}";
    }

    private static String technologicalMapJson() {
        return "{\"name\": \"some map\"," +
                "\"description\": \"some description\"," +
                "\"dinosaurTypeId\": " + dinosaurTypeId + "," +
                "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}";
    }

    private static String updatedTechnologicalMapJson() {
        return "{\"name\": \"" + UPDATED_NAME + "\"," +
                "\"description\": \"some description\"," +
                "\"dinosaurTypeId\": " + updatedDinosaurTypeId + "," +
                "\"incubationSteps\": [\"inc step1\"]," +
                "\"eggCreationSteps\": [\"egg step1\"]}";
    }

    private static String aviaryPassportJson() {
        return "{\"name\": \"some map\"," +
                "\"description\": \"some description\"," +
                "\"code\": 111," +
                "\"square\": 123," +
                "\"aviaryTypeId\": " + aviaryTypeId + "," +
                "\"builtDate\": \"2021-01-01\"," +
                "\"revisionPeriod\": 10," +
                "\"status\": \"some status\"}";
    }

//    private static String researchDataJson() {
//        return "{\"name\": \"some map\"," +
//                "\"description\": \"some description\"," +
//                "\"researchNameId\": {\"id\":" + researchId + "}," +
//                "\"newResearch\": false}";
//    }
//
//    MockMultipartFile researchData = new MockMultipartFile("attachment", "data.txt", "text/plain", "kekLol".getBytes());

    @BeforeEach
    void init(
            @Autowired DinosaurTypeRepository dinosaurTypeRepository,
            @Autowired AviaryTypeRepository aviaryTypeRepository,
            @Autowired DecorationTypeRepository decorationTypeRepository,
            @Autowired ResearchRepository researchRepository,
            @Autowired UserRepository userRepository
    ) {
        val dinosaurType = dinosaurTypeRepository.saveAndFlush(new DinosaurType("Test dinosaur"));
        val aviaryType = aviaryTypeRepository.saveAndFlush(new AviaryType("Test aviary"));
        val decorationType = decorationTypeRepository.saveAndFlush(new DecorationType("Test decoration"));
        val research = researchRepository.saveAndFlush(new Research("Test research"));
        val user = userRepository.findAll().get(0);

        val updatedDinosaurType = dinosaurTypeRepository.saveAndFlush(new DinosaurType("New dinosaur"));

        dinosaurTypeId = dinosaurType.getId();
        aviaryTypeId = aviaryType.getId();
        decorationTypeId = decorationType.getId();
        researchId = research.getId();
        managerId = user.getId();

        updatedDinosaurTypeId = updatedDinosaurType.getId();
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnEmptyListIfNoDocumentsWasSaved() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/document/DINOSAUR_PASSPORT").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @Transactional
    @WithMockUser(roles = "ADMIN")
    void allowAdminRoleToAccessEveryDocumentType() {
        Arrays.stream(DocumentType.values()).forEach(type ->
        {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/document/" + type).accept(MediaType.ALL))
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
            } catch (Exception e) {
            }
        });
    }

    @Test
    @Transactional
    @WithMockUser(roles = "DOCUMENT_READER")
    void allowDocumentReaderToAccessEveryDocumentType() {
        Arrays.stream(DocumentType.values()).forEach(type ->
        {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/document/" + type).accept(MediaType.ALL))
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
            } catch (Exception e) {
            }
        });
    }

    @Test
    @Transactional
    @WithUserDetails("test-accommodation")
    void allowDinosaurPassportReaderToAccessDinosaurPassports() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/document/DINOSAUR_PASSPORT").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails("test-accommodation")
    void forbidDinosaurPassportReaderToAccessOtherDocumentType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/document/AVIARY_PASSPORT").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "TASK_READER")
    void forbidIrrelevantRoleToAccessAnyDocumentType() {
        Arrays.stream(DocumentType.values()).forEach(type ->
        {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/document/" + type).accept(MediaType.ALL))
                        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
            } catch (Exception e) {
            }
        });
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveDinosaurPassportAndReturnIt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/document/DINOSAUR_PASSPORT")
                        .content(dinosaurPassportJson()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(greaterThan(1)))
                .andExpect(jsonPath("$.name").value(notNullValue()))
                .andExpect(jsonPath("$.author.username").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdater.username").value(notNullValue()))
                .andExpect(jsonPath("$.created").value(notNullValue()))
                .andExpect(jsonPath("$.type").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdate").value(notNullValue()))
                .andExpect(jsonPath("$.description").value(notNullValue()))
                .andExpect(jsonPath("$.status").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurType.name").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurName").value(notNullValue()))
                .andExpect(jsonPath("$.weight").value(notNullValue()))
                .andExpect(jsonPath("$.height").value(notNullValue()))
                .andExpect(jsonPath("$.revisionPeriod").value(notNullValue()))
                .andExpect(jsonPath("$.incubated").value(notNullValue()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveThemeZoneProjectAndReturnIt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/document/THEME_ZONE_PROJECT")
                        .content(themeZoneProjectJson()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(greaterThan(1)))
                .andExpect(jsonPath("$.name").value(notNullValue()))
                .andExpect(jsonPath("$.author.username").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdater.username").value(notNullValue()))
                .andExpect(jsonPath("$.created").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdate").value(notNullValue()))
                .andExpect(jsonPath("$.type").value(notNullValue()))
                .andExpect(jsonPath("$.description").value(notNullValue()))
                .andExpect(jsonPath("$.manager.username").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurs[0].number").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurs[0].type.name").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurs[0].type.id").value(notNullValue()))
                .andExpect(jsonPath("$.aviaries[0].number").value(notNullValue()))
                .andExpect(jsonPath("$.aviaries[0].type.name").value(notNullValue()))
                .andExpect(jsonPath("$.aviaries[0].type.id").value(notNullValue()))
                .andExpect(jsonPath("$.decorations[0].number").value(notNullValue()))
                .andExpect(jsonPath("$.decorations[0].type.name").value(notNullValue()))
                .andExpect(jsonPath("$.decorations[0].type.id").value(notNullValue()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveTechnologicalMapAndReturnIt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/document/TECHNOLOGICAL_MAP")
                        .content(technologicalMapJson()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(greaterThan(1)))
                .andExpect(jsonPath("$.name").value(notNullValue()))
                .andExpect(jsonPath("$.author.username").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdater.username").value(notNullValue()))
                .andExpect(jsonPath("$.created").value(notNullValue()))
                .andExpect(jsonPath("$.type").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdate").value(notNullValue()))
                .andExpect(jsonPath("$.description").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurType.id").value(notNullValue()))
                .andExpect(jsonPath("$.dinosaurType.name").value(notNullValue()))
                .andExpect(jsonPath("$.incubationSteps[0]").value(notNullValue()))
                .andExpect(jsonPath("$.incubationSteps[1]").value(notNullValue()))
                .andExpect(jsonPath("$.eggCreationSteps[0]").value(notNullValue()))
                .andExpect(jsonPath("$.eggCreationSteps[1]").value(notNullValue()));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveAviaryPassportAndReturnIt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/document/AVIARY_PASSPORT")
                        .content(aviaryPassportJson()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(greaterThan(1)))
                .andExpect(jsonPath("$.name").value(notNullValue()))
                .andExpect(jsonPath("$.author.username").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdater.username").value(notNullValue()))
                .andExpect(jsonPath("$.created").value(notNullValue()))
                .andExpect(jsonPath("$.type").value(notNullValue()))
                .andExpect(jsonPath("$.lastUpdate").value(notNullValue()))
                .andExpect(jsonPath("$.description").value(notNullValue()))
                .andExpect(jsonPath("$.status").value(notNullValue()))
                .andExpect(jsonPath("$.aviaryType.id").value(notNullValue()))
                .andExpect(jsonPath("$.aviaryType.name").value(notNullValue()))
                .andExpect(jsonPath("$.code").value(notNullValue()))
                .andExpect(jsonPath("$.builtDate").value(notNullValue()))
                .andExpect(jsonPath("$.revisionPeriod").value(notNullValue()));
    }

//    @Test
//    @Transactional
//    @WithUserDetails("admin")
//    void saveResearchDataAndReturnIt() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/document/RESEARCH_DATA")
//                        .file(researchData)
//                .content(researchDataJson()).contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                .andExpect(jsonPath("$.id").value(greaterThan(1)))
//                .andExpect(jsonPath("$.name").value(notNullValue()))
//                .andExpect(jsonPath("$.author.username").value(notNullValue()))
//                .andExpect(jsonPath("$.lastUpdater.username").value(notNullValue()))
//                .andExpect(jsonPath("$.created").value(notNullValue()))
//                .andExpect(jsonPath("$.type").value(notNullValue()))
//                .andExpect(jsonPath("$.lastUpdate").value(notNullValue()))
//                .andExpect(jsonPath("$.description").value(notNullValue()))
//                .andExpect(jsonPath("$.research.id").value(notNullValue()))
//                .andExpect(jsonPath("$.research.name").value(notNullValue()))
//                .andExpect(jsonPath("$.attachmentName").value(notNullValue()))
//                .andExpect(jsonPath("$.attachment").value(notNullValue()));
//    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void updateTechnologicalMapAndReturnIt() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/document/" + DocumentType.TECHNOLOGICAL_MAP)
                        .content(technologicalMapJson()).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/document/" + DocumentType.TECHNOLOGICAL_MAP + "/" + id)
                        .content(updatedTechnologicalMapJson()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(UPDATED_NAME))
                .andExpect(jsonPath("$.dinosaurType.id").value(updatedDinosaurTypeId))
                .andExpect(jsonPath("$.incubationSteps[1]").doesNotExist())
                .andExpect(jsonPath("$.eggCreationSteps[1]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveLogsAfterDocumentSave() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/document/TECHNOLOGICAL_MAP")
                .content(technologicalMapJson()).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$.[0].action").isNotEmpty())
                .andExpect(jsonPath("$.[0].action").value(containsString(DocumentType.TECHNOLOGICAL_MAP.getName())))
                .andExpect(jsonPath("$.[0].username").value("admin"))
                .andExpect(jsonPath("$.[0].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[1]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void saveLogsAfterDocumentUpdate() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/api/document/" + DocumentType.TECHNOLOGICAL_MAP)
                        .content(technologicalMapJson()).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/document/" + DocumentType.TECHNOLOGICAL_MAP + "/" + id)
                .content(updatedTechnologicalMapJson()).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[1]").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").value(containsString(DocumentType.TECHNOLOGICAL_MAP.getName())))
                .andExpect(jsonPath("$.[1].action").value(containsString(UPDATED_NAME)))
                .andExpect(jsonPath("$.[1].username").value("admin"))
                .andExpect(jsonPath("$.[1].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[2]").doesNotExist());
    }
}
