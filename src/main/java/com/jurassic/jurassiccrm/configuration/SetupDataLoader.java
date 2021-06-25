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

    private void createBasicRoles() {
        basicRolesAndPrivileges.getRolesToPrivileges()
                .forEach((role, privileges) -> roleService.getOrCreateRole(role, privileges));
    }

}
