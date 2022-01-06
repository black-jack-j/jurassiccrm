package com.jurassic.jurassiccrm.testdb;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Disabled
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private String username = "test_user";
    private String firstName = "test_user1";
    private String lastName = "test_user2";
    private String password = "$2a$10$l5dQSxvtYQpYElyxsUk3buXgCSBwPlzCvha5adgdaGEyJgCjrLpC2";
    private String[] roles = new String[]{"ROLE_TASK_READER", "ROLE_DOCUMENT_READER"};
    private String[] usernames = {"admin", "test-doc", "test1", "admin", "dummy1", "dummy2",
            "dummy3", "dummy4", "dummy5", "dummy6", "dummy7", "dummy8", "dummy9", "dummy10"
    };

    @Test
    @Order(1)
    public void testAllDefaultUsersCreated(){

        List<User> users = userRepository.findAll();
        List<String> foundUsernames = new ArrayList<>();
        for (User user : users){
            foundUsernames.add(user.getUsername());
        }
        boolean flagAllFound = true;
        for (String username: usernames){
            if (!foundUsernames.contains(username)){
                flagAllFound = false;
                break;
            }
        }
        assert flagAllFound;
    }

    @Test
    @Transactional
    @Rollback(false)
    @Order(2)
    public void testUserCreation(){
        User newTestUser = new User();
        newTestUser.setEnabled(true);
        newTestUser.setAccountNonExpired(true);
        newTestUser.setUsername(username);
        newTestUser.setPassword(password);
        newTestUser.setFirstName(firstName);
        newTestUser.setLastName(lastName);
        for (String role : roles) {
            newTestUser.addRole(roleRepository.findByName(role).orElseThrow(() -> new IllegalArgumentException("cant find basic role with name '" + role + "' ")));
        }
        userRepository.save(newTestUser);
        userRepository.flush();
        User findNewTestUser = userRepository.findByUsername(username).orElse(null);
        assert Objects.requireNonNull(findNewTestUser).equals(newTestUser);
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
        assert Objects.equals(Objects.requireNonNull(checkUpdatedUser).getFirstName(), new_username);
    }

    @Test
    @Order(4)
    public void testUserCheckRoles(){
        User testUser = userRepository.findByUsername(username).orElse(null);
        Set<Role> testUserRoles = Objects.requireNonNull(testUser).getRoles();
        List<String> rolesNames = new ArrayList<>();
        boolean allRolesRight = true;
        for (Role role: testUserRoles){
            rolesNames.add(role.getName());
        }
        for (String role: roles){
            if (!rolesNames.contains(role)){
                allRolesRight = false;
                break;
            }
        }
        if (rolesNames.size() != roles.length){
            allRolesRight = false;
        }
        assert allRolesRight;
    }

    @Test
    @Transactional
    @Rollback(false)
    @Order(5)
    public void testUserDeletion(){
        userRepository.deleteByUsername(username);
        userRepository.flush();
        User findNewTestUser = userRepository.findByUsername(username).orElse(null);
        assert findNewTestUser == null;
    }

}
