package com.jurassic.jurassiccrm.testcontrollers;

import com.jayway.jsonpath.JsonPath;
import com.jurassic.jurassiccrm.accesscontroll.model.Department;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Long groupId1 = -1L;
    private Long groupId2 = -1L;
    private Long groupId3 = -1L;

    private static final String LOGS_URL = "/api/logs";
    private static final String USER_URL = "/api/user";
    private static final String DEFAULT_NAME = "Test user";
    private static final String NO_NAME_JSON = "{\"no-name\":\"" + DEFAULT_NAME + "\"}";
    private static final String UPDATED_NAME = "Updated type";
    private static final String ADMIN_USERNAME = "admin";
    private static final String NON_ADMIN_USERNAME = "test-incubation";

    private static final String EMPTY_NAME_JSON =
            "{\"username\":\"\"," +
                    "\"password\":\"password\"," +
                    "\"firstName\":\"first name\"," +
                    "\"lastName\":\"last name\"," +
                    "\"department\":\"" + Department.INCUBATION + "\"}";

    private static final String EMPTY_PASSWORD_JSON =
            "{\"username\":\"" + DEFAULT_NAME + "\"," +
                    "\"password\":\"\"," +
                    "\"firstName\":\"first name\"," +
                    "\"lastName\":\"last name\"," +
                    "\"department\":\"" + Department.INCUBATION + "\"}";

    private static final String EMPTY_FIRSTNAME_JSON =
            "{\"username\":\"" + DEFAULT_NAME + "\"," +
                    "\"password\":\"password\"," +
                    "\"firstName\":\"\"," +
                    "\"lastName\":\"last name\"," +
                    "\"department\":\"" + Department.INCUBATION + "\"}";

    private static final String EMPTY_LASTNAME_JSON =
            "{\"username\":\"" + DEFAULT_NAME + "\"," +
                    "\"password\":\"password\"," +
                    "\"firstName\":\"first name\"," +
                    "\"lastName\":\"\"," +
                    "\"department\":\"" + Department.INCUBATION + "\"}";

    private static final String EMPTY_DEPARTMENT_JSON =
            "{\"username\":\"" + DEFAULT_NAME + "\"," +
                    "\"password\":\"password\"," +
                    "\"firstName\":\"first name\"," +
                    "\"lastName\":\"last name\"," +
                    "\"department\":\"\"}";

    private static final String INVALID_DEPARTMENT_JSON =
            "{\"username\":\"" + DEFAULT_NAME + "\"," +
                    "\"password\":\"password\"," +
                    "\"firstName\":\"first name\"," +
                    "\"lastName\":\"last name\"," +
                    "\"department\":\"INVALID\"}";

    private String defaultJson() {
        return "{\"username\":\"" + DEFAULT_NAME + "\"," +
                "\"password\":\"password\"," +
                "\"firstName\":\"first name\"," +
                "\"lastName\":\"last name\"," +
                "\"department\":\"" + Department.INCUBATION + "\"," +
                "\"groupIds\":[\"" + groupId1 + "\", \"" + groupId2 + "\"]}";
    }

    private String updatedJson() {
        return "{\"username\":\"" + UPDATED_NAME + "\"," +
                "\"password\":\"password\"," +
                "\"firstName\":\"first name\"," +
                "\"lastName\":\"last name\"," +
                "\"department\":\"" + Department.INCUBATION + "\"," +
                "\"groupIds\":[\"" + groupId1 + "\", \"" + groupId3 + "\"]}";
    }

    private String getGroupIdUrl(Integer id) {
        return String.format("%s/%d", USER_URL, id);
    }

    @BeforeEach
    void init(@Autowired GroupRepository groupRepository) {
        Group group1 = groupRepository.save(new Group("Test group 1"));
        Group group2 = groupRepository.save(new Group("Test group 2"));
        Group group3 = groupRepository.save(new Group("Test group 3"));
        groupId1 = group1.getId();
        groupId2 = group2.getId();
        groupId3 = group3.getId();
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnGetEntities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void returnUnauthorisedOnOnGetRequestWithoutAuthorisation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(NON_ADMIN_USERNAME)
    void returnUnauthorisedOnUnauthorisedUserOnGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnSaveEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(defaultJson()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void returnUnauthorisedOnPostRequestWithoutAuthorisation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(NON_ADMIN_USERNAME)
    void returnUnauthorisedOnUnauthorisedUserOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(defaultJson()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnSavedEntityFromPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(defaultJson()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.username").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogEntryAfterSaveUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON).content(defaultJson()));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$.[0].action").isNotEmpty())
                .andExpect(jsonPath("$.[0].username").value("admin"))
                .andExpect(jsonPath("$.[0].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[1]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnEntityFromGetRequestSavedEarlierOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON).content(defaultJson()));
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$.[0].username").isNotEmpty())
                .andExpect(jsonPath("$.[0].firstName").isNotEmpty())
                .andExpect(jsonPath("$.[0].lastName").isNotEmpty())
                .andExpect(jsonPath("$.[0].password").isNotEmpty())
                .andExpect(jsonPath("$.[0].department").isNotEmpty());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfFirstNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_FIRSTNAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfLastNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_LASTNAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfPasswordIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_PASSWORD_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfDepartmentIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_DEPARTMENT_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfDepartmentIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(INVALID_DEPARTMENT_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(NO_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(USER_URL).contentType(MediaType.APPLICATION_JSON)
                .content(defaultJson())).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogEntryAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(USER_URL).contentType(MediaType.APPLICATION_JSON)
                .content(defaultJson())).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(updatedJson()));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[1]").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").isNotEmpty())
                .andExpect(jsonPath("$.[1].username").value("admin"))
                .andExpect(jsonPath("$.[1].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnNewEntityAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(USER_URL).contentType(MediaType.APPLICATION_JSON)
                .content(defaultJson())).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").value(UPDATED_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(NO_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getGroupIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdIsNotStated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(USER_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.put(USER_URL + "/")
                        .contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdIsNan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(USER_URL + "/nan")
                        .contentType(MediaType.APPLICATION_JSON).content(updatedJson()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
