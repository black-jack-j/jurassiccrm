package com.jurassic.jurassiccrm.testsecurity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
public class AccessControlTest {
    @Autowired
    private MockMvc mockMvc;

    private String[] adminPages = {
            "/document/upload",
            "/task", "/group/create", "/admin"
    };

    private String[] documentWriterPages = {
            "/document/upload"
    };

    private String[] groupEditorPages = {
            "/group/create"
    };

    @Test
    @WithUserDetails("test1")
    public void testTaskReaderAccessAdminPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .accept(MediaType.ALL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    public void testAdminAccessPages() throws Exception {
        for (String url: adminPages){
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .accept(MediaType.ALL))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Test
    @WithMockUser(roles = "DOCUMENT_READER")
    public void testDocumentReaderAccessToOtherPages() throws Exception {
        for (String url: documentWriterPages){
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .accept(MediaType.ALL))
                    .andExpect(status().isForbidden());
        }
        for (String url: groupEditorPages){
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .accept(MediaType.ALL))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    @WithMockUser(roles = "GROUP_EDITOR")
    public void testGroupEditorAccess() throws Exception {
        for (String url: groupEditorPages){
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .accept(MediaType.ALL))
                    .andExpect(status().is2xxSuccessful());
        }
    }

    @Test
    @WithMockUser(roles = "DOCUMENT_READER")
    public void testWikiPageAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wiki/home")
                        .accept(MediaType.ALL))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDefaultUsersRoles() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("admin")).andExpect(authenticated().withRoles("ADMIN"));
        mockMvc.perform(logout());
        mockMvc.perform(formLogin().user("test-doc").password("test")).andExpect(authenticated().withRoles("DOCUMENT_WRITER", "DOCUMENT_READER", "EMPLOYEE"));
        mockMvc.perform(logout());
        mockMvc.perform(formLogin().user("test1").password("test")).andExpect(authenticated().withRoles("TASK_READER"));
        mockMvc.perform(logout());
        mockMvc.perform(formLogin().user("dummy1").password("dummy")).andExpect(authenticated().withRoles("TASK_READER"));
        mockMvc.perform(logout());
    }
}
