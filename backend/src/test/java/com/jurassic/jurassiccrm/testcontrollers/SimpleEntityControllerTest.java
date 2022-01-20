package com.jurassic.jurassiccrm.testcontrollers;

import com.jayway.jsonpath.JsonPath;
import lombok.val;
import org.hamcrest.Matchers;
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

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class SimpleEntityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final String URL;
    private static final String DEFAULT_NAME = "Test type";
    private static final String DEFAULT_JSON = "{\"name\":\"" + DEFAULT_NAME + "\"}";
    private static final String EMPTY_NAME_JSON = "{\"name\":\"\"}";
    private static final String NULL_NAME_JSON = "{\"name\":null}";
    private static final String NO_NAME_JSON = "{\"no-name\":\"" + DEFAULT_NAME + "\"}";
    private static final String UPDATED_NAME = "Updated type";
    private static final String UPDATED_JSON = "{\"name\":\"" + UPDATED_NAME + "\"}";

    protected SimpleEntityControllerTest(String url) {
        URL = url;
    }

    private String getIdUrl(Integer id) {
        return String.format("%s/%d", URL, id);
    }


    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnOkOnGetEntities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void returnUnauthorisedOnUnauthorisedUserOnGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser()
    void returnOkOnAnyLoggedOnUserOnGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnOkOnSaveEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(DEFAULT_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    void returnUnauthorisedOnUnauthorisedUserOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser()
    void returnOkOnAnyLoggedOnUserOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(DEFAULT_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnSavedEntityFromPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(DEFAULT_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnEntityFromGetRequestSavedEarlierOnPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON).content(DEFAULT_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnSaveEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnSaveEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnSaveEntityIfNameIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(NULL_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnSaveEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(NO_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnOkOnUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnNewEntityAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(UPDATED_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfEntityIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(EMPTY_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfNameIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(NULL_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfNameFieldNameIsMisspelled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(NO_NAME_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfIdDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(-1))
                        .contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfIdIsNotStated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/")
                        .contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnUpdateEntityIfIdIsNan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/nan")
                        .contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnNewEntityFromGetQueryAfterUpdateEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.put(getIdUrl(id)).contentType(MediaType.APPLICATION_JSON).content(UPDATED_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(UPDATED_NAME)))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString(DEFAULT_NAME))));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnOkOnDeleteEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(getIdUrl(id)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void returnDeletedEntityAfterDeleteEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(getIdUrl(id)))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnDeleteEntityIfIdDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(getIdUrl(-1)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnDeleteEntityIfIdIsNotStated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void return400OnDeleteEntityIfIdIsNan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/nan"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithUserDetails("admin")
    void notReturnDeletedEntityFromGetQueryAfterDeleteEntity() throws Exception {
        val result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(DEFAULT_JSON)).andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(getIdUrl(id)));
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString(DEFAULT_NAME))));
    }
}
