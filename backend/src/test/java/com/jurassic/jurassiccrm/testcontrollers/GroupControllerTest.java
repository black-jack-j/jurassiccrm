package com.jurassic.jurassiccrm.testcontrollers;

import com.jayway.jsonpath.JsonPath;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Long userId1 = -1L;
    private Long userId2 = -1L;
    private Long userId3 = -1L;

    private static final Role ROLE_1 = Role.DOCUMENT_READER;
    private static final Role ROLE_2 = Role.DOCUMENT_WRITER;
    private static final Role ROLE_3 = Role.DOCUMENT_WRITER;
    private static final String LOGS_URL = "/api/logs";
    private static final String GROUP_URL = "/api/group";
    private static final String ROLES_URL = "/api/group/role";
    private static final String USERS_URL = "/api/group/user";
    private static final String DEFAULT_NAME = "Test group";
    private static final String EMPTY_NAME_JSON = "{\"name\":\"\"}";
    private static final String NULL_NAME_JSON = "{\"name\":null}";
    private static final String NO_NAME_JSON = "{\"no-name\":\"" + DEFAULT_NAME + "\"}";
    private static final String UPDATED_NAME = "Updated type";
    private static final String ADMIN_USERNAME = "admin";
    private static final String NON_ADMIN_USERNAME = "test-incubation";
    private static final MockMultipartFile mockAvatar = new MockMultipartFile("avatar", new byte[]{1, 2, 3});

    private String defaultJson() {
        return "{\"name\":\"" + DEFAULT_NAME + "\"," +
                "\"roles\":[\"" + ROLE_1 + "\", \"" + ROLE_2 + "\"]," +
                "\"userIds\":[\"" + userId1 + "\", \"" + userId2 + "\"]}";
    }

    private String updatedJson() {
        return "{\"name\":\"" + UPDATED_NAME + "\"," +
                "\"roles\":[\"" + ROLE_1 + "\", \"" + ROLE_3 + "\"]," +
                "\"userIds\":[\"" + userId1 + "\", \"" + userId3 + "\"]}";
    }

    private String userIdJson() {
        return "{\"id\":\"" + userId3 + "\"}";
    }

    private String getGroupIdUrl(Integer id) {
        return String.format("%s/%d", GROUP_URL, id);
    }

    private String getUserUrl(Integer id) {
        return String.format("%s/user", getGroupIdUrl(id));
    }

    private String getUserIdUrl(Integer groupId, Integer userId) {
        return String.format("%s/%d", getUserUrl(groupId), userId);
    }

    @BeforeEach
    void init(@Autowired UserRepository userRepository, @Autowired GroupRepository groupRepository) {
        User user1 = userRepository.save(new User("Test user 1"));
        User user2 = userRepository.save(new User("Test user 2"));
        User user3 = userRepository.save(new User("Test user 3"));
        userId1 = user1.getId();
        userId2 = user2.getId();
        userId3 = user3.getId();
        groupRepository.deleteAll();
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnGetEntities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void returnUnauthorisedOnOnGetRequestWithoutAuthorisation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(NON_ADMIN_USERNAME)
    void returnUnauthorisedOnUnauthorisedUserOnGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnSaveEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                        .part(new MockPart("groupInfo", defaultJson().getBytes())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogsAfterSaveEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                .file(mockAvatar).part(new MockPart("groupInfo", defaultJson().getBytes())));
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
    void returnUnauthorisedOnPostRequestWithoutAuthorisation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(NON_ADMIN_USERNAME)
    void returnUnauthorisedOnUnauthorisedUserOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).part(new MockPart("groupInfo", defaultJson().getBytes())))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnSavedEntityFromPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).part(new MockPart("groupInfo", defaultJson().getBytes())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnEntityFromGetRequestSavedEarlierOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                .file(mockAvatar).part(new MockPart("groupInfo", defaultJson().getBytes())));
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME))
                .andExpect(jsonPath("$.[0].users[0].id").value(Matchers.oneOf(userId1.intValue(), userId2.intValue())))
                .andExpect(jsonPath("$.[0].users[1].id").value(Matchers.oneOf(userId1.intValue(), userId2.intValue())))
                .andExpect(jsonPath("$.[0].users[0].username").isNotEmpty())
                .andExpect(jsonPath("$.[0].users[1].username").isNotEmpty())
                .andExpect(jsonPath("$.[0].roles[0]").value(Matchers.oneOf(ROLE_1.toString(), ROLE_2.toString())))
                .andExpect(jsonPath("$.[0].roles[1]").value(Matchers.oneOf(ROLE_1.toString(), ROLE_2.toString())));
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfNameIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).content(NULL_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnSaveEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).content(NO_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnOkOnUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(id)).file(mockAvatar)
                        .part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogsAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andDo(MockMvcResultHandlers.print()).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(id)).file(mockAvatar)
                .part(new MockPart("groupInfo", updatedJson().getBytes()))
                .with(req -> {
                    req.setMethod("PUT");
                    return req;
                }));
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
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(id)).file(mockAvatar)
                        .part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(UPDATED_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(-1))
                        .file(mockAvatar).content("{}")
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(-1))
                        .file(mockAvatar).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfNameIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(-1))
                        .file(mockAvatar).content(NULL_NAME_JSON)
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(-1))
                        .file(mockAvatar).content(NO_NAME_JSON)
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(-1))
                        .file(mockAvatar).part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdIsNotStated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL)
                        .file(mockAvatar).part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL + "/")
                        .file(mockAvatar).part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void return400OnUpdateEntityIfIdIsNan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL + "/nan")
                        .file(mockAvatar).part(new MockPart("groupInfo", updatedJson().getBytes()))
                        .with(req -> {
                            req.setMethod("PUT");
                            return req;
                        }))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnNewEntityFromGetQueryAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.multipart(getGroupIdUrl(id)).file(mockAvatar)
                .part(new MockPart("groupInfo", updatedJson().getBytes()))
                .with(req -> {
                    req.setMethod("PUT");
                    return req;
                }));
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(jsonPath("$.[0].name").value(UPDATED_NAME))
                .andExpect(jsonPath("$.[0].users[0].id").value(Matchers.oneOf(userId1.intValue(), userId3.intValue())))
                .andExpect(jsonPath("$.[0].users[1].id").value(Matchers.oneOf(userId1.intValue(), userId3.intValue())))
                .andExpect(jsonPath("$.[0].users[2]").doesNotExist())
                .andExpect(jsonPath("$.[0].roles[0]").value(Matchers.oneOf(ROLE_1.toString(), ROLE_3.toString())))
                .andExpect(jsonPath("$.[0].roles[1]").value(Matchers.oneOf(ROLE_1.toString(), ROLE_3.toString())))
                .andExpect(jsonPath("$.[0].roles[2]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void addUsers() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.post(getUserUrl(id)).contentType(MediaType.APPLICATION_JSON).content(userIdJson()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(jsonPath("$.[0].users[2].id").isNotEmpty());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogsAfterAddUsers() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.post(getUserUrl(id)).contentType(MediaType.APPLICATION_JSON).content(userIdJson()));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[1]").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").value(containsString(userId3.toString())))
                .andExpect(jsonPath("$.[1].action").value(containsString(id.toString())))
                .andExpect(jsonPath("$.[1].username").value("admin"))
                .andExpect(jsonPath("$.[1].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[2]").doesNotExist());
    }


    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void removeUsers() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(getUserIdUrl(id, userId2.intValue())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders.get(GROUP_URL))
                .andExpect(jsonPath("$.[0].users[1]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnLogsAfterRemoveUsers() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.multipart(GROUP_URL).file(mockAvatar)
                .part(new MockPart("groupInfo", defaultJson().getBytes()))).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(getUserIdUrl(id, userId2.intValue())));
        mockMvc.perform(MockMvcRequestBuilders.get(LOGS_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[1]").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").isNotEmpty())
                .andExpect(jsonPath("$.[1].action").value(containsString(userId2.toString())))
                .andExpect(jsonPath("$.[1].action").value(containsString(id.toString())))
                .andExpect(jsonPath("$.[1].username").value("admin"))
                .andExpect(jsonPath("$.[1].timestamp").isNotEmpty())
                .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnAllRoles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROLES_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0]").isNotEmpty());
    }

    @Test
    @Transactional
    @WithUserDetails(ADMIN_USERNAME)
    void returnAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0]").isNotEmpty());
    }
}
