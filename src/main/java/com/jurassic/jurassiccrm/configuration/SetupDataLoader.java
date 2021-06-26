package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.config.BasicRolesAndPrivileges;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.service.RoleService;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BasicRolesAndPrivileges basicRolesAndPrivileges;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        createBasicRoles();

        createDocumentManager();

        createAdmin();

        createTemp1();

        createNDummies(10);
        alreadySetup = true;
    }

    private User createDocumentManager() {
        User documentManager = new User();

        documentManager.setUsername("test-doc");
        documentManager.setPassword(passwordEncoder.encode("test"));
        documentManager.setFirstName("Нестор");
        documentManager.setLastName("Летописец");
        documentManager.setEnabled(true);
        documentManager.setAccountNonExpired(true);

        documentManager.addRole(roleService.getBasicRole("ROLE_DOCUMENT_WRITER"));
        documentManager.addRole(roleService.getBasicRole("ROLE_DOCUMENT_READER"));
        documentManager.addRole(roleService.getBasicRole("ROLE_EMPLOYEE"));

        return userService.createUser(documentManager);

    }

    private User createTemp1() {
        User temp1 = new User();

        temp1.setUsername("test1");
        temp1.setPassword(passwordEncoder.encode("test"));
        temp1.setFirstName("test1");
        temp1.setLastName("test2");
        temp1.setEnabled(true);
        temp1.setAccountNonExpired(true);

        temp1.addRole(roleService.getBasicRole("ROLE_TASK_READER"));

        return userService.createUser(temp1);
    }

    private User createAdmin() {
        User temp1 = new User();

        temp1.setUsername("admin");
        temp1.setPassword(passwordEncoder.encode("admin"));
        temp1.setFirstName("admin");
        temp1.setLastName("admin");
        temp1.setEnabled(true);
        temp1.setAccountNonExpired(true);

        temp1.addRole(roleService.getBasicRole("ROLE_ADMIN"));

        return userService.createUser(temp1);
    }

    private void createNDummies(int N){
        for(int i = 1; i <= N; i++){
            createDummyUser(i);
        }
    }

    private void createDummyUser(int number) {
        User usr = new User();

        usr.setUsername("dummy" + number);
        usr.setPassword(passwordEncoder.encode("dummy"));
        usr.setFirstName("Dummy");
        usr.setLastName(String.valueOf(number));
        usr.setEnabled(true);
        usr.setAccountNonExpired(true);

        usr.addRole(roleService.getBasicRole("ROLE_DOCUMENT_READER"));

        userService.createUser(usr);
    }

    private void createBasicRoles() {
        basicRolesAndPrivileges.getRolesToPrivileges()
                .forEach((role, privileges) -> roleService.getOrCreateRole(role, privileges));
    }

}
