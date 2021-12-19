package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.config.BasicRolesAndPrivileges;
import com.jurassic.jurassiccrm.accesscontroll.entity.Group;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BasicRolesAndPrivileges basicRolesAndPrivileges;

    @Autowired
    private GroupService groupService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<User> users = userRepository.findAll();
        if (users.size()>0){
            alreadySetup = true;
        }

        if (alreadySetup) return;

        createBasicRoles();

        createUser("test-doc", "test", "Нестор", "Летописец",
                new String[]{"ROLE_DOCUMENT_WRITER", "ROLE_DOCUMENT_READER", "ROLE_EMPLOYEE"});
        createUser("test1", "test", "test1", "test2",
                new String[]{"ROLE_TASK_READER"});
        createUser("admin", "admin", "admin", "admin",
                new String[]{"ROLE_ADMIN"});

        List<User> dummies = createNDummies(10);
        Set<Role> rolesForDummies = new HashSet<>();
        rolesForDummies.add(roleService.getBasicRole("ROLE_TASK_READER"));
        Group group = new Group();
        group.setName("Dummies Group");
        group.setUsers(new HashSet<>(dummies));
        group.setRoles(rolesForDummies);
        groupService.createGroup(group);
        alreadySetup = true;
    }

    private User createUser(String username, String password, String firstName, String lastName, String[] Roles) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEnabled(true);
        newUser.setAccountNonExpired(true);
        for (String role : Roles) {
            newUser.addRole(roleService.getBasicRole(role));
        }
        return userService.createUser(newUser);
    }


    private List<User> createNDummies(int N) {
        return IntStream.rangeClosed(1, N)
                .mapToObj(this::createDummyUser)
                .collect(Collectors.toList());
    }

    private User createDummyUser(int number) {
        User usr = new User();

        usr.setUsername("dummy" + number);
        usr.setPassword(passwordEncoder.encode("dummy"));
        usr.setFirstName("Dummy");
        usr.setLastName(String.valueOf(number));
        usr.setEnabled(true);
        usr.setAccountNonExpired(true);
        usr.addRole(roleService.getBasicRole("ROLE_TASK_READER"));

        return userService.createUser(usr);
    }

    private void createBasicRoles() {
        basicRolesAndPrivileges.getRolesToPrivileges()
                .forEach((role, privileges) -> roleService.getOrCreateRole(role, privileges));
    }

}