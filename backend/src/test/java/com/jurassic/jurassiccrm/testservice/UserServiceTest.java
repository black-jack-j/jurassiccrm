package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.exception.UnauthorisedUserOperationException;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserServiceTest {
    private final UserService userService;
    private final GroupRepository groupRepository;

    private User admin = new User();
    private User security = new User();
    private User simpleUser = new User();

    @Autowired
    public UserServiceTest(GroupRepository groupRepository, UserRepository userRepository) {
        this.userService = new UserService(userRepository, groupRepository, new RolesChecker(userRepository));
        this.groupRepository = groupRepository;
    }

    @BeforeEach
    void init(@Autowired GroupRepository groupRepository, @Autowired UserRepository userRepository) {
        User adminUser = userRepository.save(createUser("admin"));
        Group adminGroup = new Group("admin group");
        adminGroup.addUser(adminUser);
        adminGroup.addRole(Role.ADMIN);
        groupRepository.save(adminGroup);
        admin = adminUser;

        User securityUser = userRepository.save(createUser("security"));
        Group securityGroup = new Group("security group");
        securityGroup.addUser(securityUser);
        securityGroup.addRole(Role.SECURITY_READER);
        securityGroup.addRole(Role.SECURITY_WRITER);
        groupRepository.save(securityGroup);
        security = securityUser;

        simpleUser = userRepository.save(createUser("simple user"));
    }

    @Test
    void testUserAndGroupAreSavedCorrectly() {
        Group savedGroup = groupRepository.save(new Group("group"));

        User user = createUser("username");
        user.addGroup(savedGroup);
        User savedUser = userService.createUser(user, admin);

        Assertions.assertEquals(1, savedUser.getGroups().size());
        Group userGroup = savedUser.getGroups().iterator().next();
        Assertions.assertEquals(1, userGroup.getUsers().size());
        User groupUser = userGroup.getUsers().iterator().next();
        Assertions.assertEquals(savedUser.getId(), groupUser.getId());
        Assertions.assertEquals(savedUser.getUsername(), groupUser.getUsername());
    }

    @Test
    void testSaveUserWithDuplicateUsernameThrowsException() {
        userService.createUser(createUser("username"), admin);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(createUser("username"), admin));
    }

    @Test
    void testUpdateUserPlainField() {
        User saved = userService.createUser(createUser("user name"), admin);
        saved.setUsername("updated");
        User updated = userService.updateUser(saved.getId(), saved, admin);
        Assertions.assertEquals("updated", updated.getUsername());
    }

    @Test
    void testUpdateUserGroup() {
        Group group1 = groupRepository.save(new Group("group1"));
        Group group2 = groupRepository.save(new Group("group2"));

        User user = createUser("username");
        user.addGroup(group1);
        User savedUser = userService.createUser(user, admin);

        User user2 = createUser("username");
        user2.setId(savedUser.getId());
        user2.addGroup(group2);
        User updatedUser = userService.updateUser(user2.getId(), user2, admin);

        Assertions.assertEquals(1, updatedUser.getGroups().size());
        Group userGroup = updatedUser.getGroups().iterator().next();
        Assertions.assertEquals(1, userGroup.getUsers().size());
        Assertions.assertEquals(group2.getName(), userGroup.getName());
        User groupUser = userGroup.getUsers().iterator().next();
        Assertions.assertEquals(updatedUser.getId(), groupUser.getId());
        Assertions.assertEquals(updatedUser.getUsername(), groupUser.getUsername());
        Assertions.assertTrue(groupRepository.findById(group1.getId()).get().getUsers().isEmpty());
        Assertions.assertEquals(1, groupRepository.findById(group2.getId()).get().getUsers().size());
    }

    @Test
    void testAdminCanSaveUser() {
        Assertions.assertDoesNotThrow(() -> userService.createUser(createUser("user"), admin));
    }

    @Test
    void testSecurityCanSaveUser() {
        Assertions.assertDoesNotThrow(() -> userService.createUser(createUser("user"), security));
    }

    @Test
    void testSecurityCantSaveUser() {
        Assertions.assertThrows(UnauthorisedUserOperationException.class,
                () -> userService.createUser(createUser("user"), simpleUser));
    }

    @Test
    void testAdminCanGetUsers() {
        Assertions.assertDoesNotThrow(() -> userService.getAllUsers(admin));
    }

    @Test
    void testSecurityCanGetUsers() {
        Assertions.assertDoesNotThrow(() -> userService.getAllUsers(security));
    }

    @Test
    void testAdminCanUpdateUser() {
        User saved = userService.createUser(createUser("user"), admin);
        Assertions.assertDoesNotThrow(() -> userService.updateUser(saved.getId(), saved, admin));
    }

    @Test
    void testSecurityCanUpdateUser() {
        User saved = userService.createUser(createUser("user"), admin);
        Assertions.assertDoesNotThrow(() -> userService.updateUser(saved.getId(), saved, security));
    }

    @Test
    void testSecurityCantUpdateUser() {
        User saved = userService.createUser(createUser("user"), admin);
        Assertions.assertThrows(UnauthorisedUserOperationException.class,
                () -> userService.updateUser(saved.getId(), saved, simpleUser));
    }

    @Test
    void testSecurityCantGetUsers() {
        Assertions.assertThrows(UnauthorisedUserOperationException.class,
                () -> userService.getAllUsers(simpleUser));
    }

    private User createUser(String username) {
        User user = new User(username);
        user.setPassword("");
        return user;
    }
}
