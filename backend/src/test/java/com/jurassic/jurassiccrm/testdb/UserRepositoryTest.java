package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.Group;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import com.jurassic.jurassiccrm.configuration.SetupDataLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Import({
        SetupDataLoader.class,
        UserService.class,
        GroupService.class,
        BCryptPasswordEncoder.class
})
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    private final String username = "test_user";
    private final List<Role> roles = Arrays.asList(Role.TASK_READER, Role.TASK_WRITER);
    private final List<String> usernames = Arrays.asList("admin", "test-research", "test-incubation", "test-security",
            "test-maintenance", "test-accommodation", "dummy1", "dummy2", "dummy3", "dummy4",
            "dummy5", "dummy6", "dummy7", "dummy8", "dummy9", "dummy10"
    );

    @Test
    @Order(1)
    public void testAllDefaultUsersCreated(){
        List<User> users = userRepository.findAll();
        List<String> foundUsernames = users.stream().map(User::getUsername).collect(Collectors.toList());

        Assertions.assertTrue(foundUsernames.containsAll(usernames));
    }

    @Test
    @Transactional
    @Rollback(false)
    @Order(2)
    public void testUserCreation(){
        User newTestUser = new User();
        newTestUser.setUsername(username);
        newTestUser.setPassword("$2a$10$l5dQSxvtYQpYElyxsUk3buXgCSBwPlzCvha5adgdaGEyJgCjrLpC2");
        newTestUser.setFirstName("test_user1");
        newTestUser.setLastName("test_user2");
        userRepository.save(newTestUser);

        Group group = new Group();
        group.setName("Test group");
        group.setRoles(new HashSet<>(roles));
        group.setUsers(new HashSet<>(Collections.singletonList(newTestUser)));
        groupRepository.saveAndFlush(group);

        User findNewTestUser = userRepository.findByUsername(username).orElse(null);

        Assertions.assertEquals(newTestUser, Objects.requireNonNull(findNewTestUser));
    }

    @Test
    @Transactional
    @Rollback(false)
    @Order(3)
    public void testUpdateFirstName(){
        String new_username = "NEW_FIRSTNAME";
        User updatableUser = userRepository.findByUsername(username).orElse(null);
        Objects.requireNonNull(updatableUser).setFirstName(new_username);
        userRepository.flush();
        User checkUpdatedUser = userRepository.findByUsername(username).orElse(null);

        Assertions.assertEquals(new_username, Objects.requireNonNull(checkUpdatedUser).getFirstName());
    }

    @Test
    @Order(4)
    public void testUserCheckRoles(){
        User testUser = userRepository.findByUsername(username).orElse(null);
        Set<Role> testUserRoles = Objects.requireNonNull(testUser).getRoles();

        Assertions.assertTrue(testUserRoles.containsAll(roles));
        Assertions.assertEquals(roles.size(), testUserRoles.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    @Order(5)
    public void testUserDeletion(){
        User user = userRepository.findByUsername(username).orElse(null);
        assert user != null;
        userRepository.delete(user);
        groupRepository.deleteAll(user.getGroups());

        Assertions.assertFalse(userRepository.findByUsername(username).isPresent());
        Assertions.assertTrue(groupRepository.findAllById(user.getGroups().stream().map(Group::getId).collect(Collectors.toSet())).isEmpty());
    }

}
