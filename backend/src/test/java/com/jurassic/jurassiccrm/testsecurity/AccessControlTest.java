package com.jurassic.jurassiccrm.testsecurity;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

@SpringBootTest
@AutoConfigureMockMvc
public class AccessControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUserRoles() throws Exception {
        User user1 = new User();
        user1.setUsername("test user");
        user1.setPassword(passwordEncoder.encode("password"));
        User savedUser1 = userRepository.save(user1);

        Group group1 = new Group();
        group1.setName("Test group");
        group1.setRoles(new HashSet<>(Arrays.asList(Role.DOCUMENT_READER, Role.DOCUMENT_WRITER)));
        group1.setUsers(new HashSet<>(Collections.singletonList(savedUser1)));
        Group savedGroup1 = groupRepository.save(group1);

        User user2 = new User();
        user2.setUsername("test user 2");
        user2.setPassword(passwordEncoder.encode("password"));
        User savedUser2 = userRepository.save(user2);

        Group group2 = new Group();
        group2.setName("Test group 2");
        group2.setRoles(new HashSet<>(Arrays.asList(Role.TASK_READER, Role.TASK_WRITER)));
        group2.setUsers(new HashSet<>(Collections.singletonList(savedUser2)));
        Group savedGroup2 = groupRepository.save(group2);

        mockMvc.perform(formLogin().user(user1.getUsername()).password("password"))
                .andExpect(authenticated().withRoles(rolesToStringArray(group1.getRoles())));
        mockMvc.perform(logout());
        mockMvc.perform(formLogin().user(user2.getUsername()).password("password"))
                .andExpect(authenticated().withRoles(rolesToStringArray(group2.getRoles())));
        mockMvc.perform(logout());

        groupRepository.delete(savedGroup1);
        userRepository.delete(savedUser1);
        groupRepository.delete(savedGroup2);
        userRepository.delete(savedUser2);
    }

    private String[] rolesToStringArray(Set<Role> roles) {
        return roles.stream().map(Role::name).toArray(String[]::new);
    }
}
