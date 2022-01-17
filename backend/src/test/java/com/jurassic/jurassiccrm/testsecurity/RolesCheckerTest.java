package com.jurassic.jurassiccrm.testsecurity;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@DataJpaTest
@ActiveProfiles("test")
class RolesCheckerTest {

    @Autowired
    public RolesCheckerTest(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.rolesChecker = new RolesChecker(userRepository);
    }

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final RolesChecker rolesChecker;

    private static final String NO_GROUP_USER = "No group";
    private static final String NO_ROLES_USER = "No roles";
    private static final String ONE_ROLE_USER = "One role";
    private static final String TWO_ROLES_USER = "Two roles";
    private static final String TWO_GROUPS_USER = "Two groups";

    @BeforeEach
    void setup(){
        User noGroupUser = new User();
        noGroupUser.setUsername(NO_GROUP_USER);
        noGroupUser.setPassword("pass");
        userRepository.save(noGroupUser);

        User NoRolesUser = new User();
        NoRolesUser.setUsername(NO_ROLES_USER);
        NoRolesUser.setPassword("pass");
        userRepository.save(NoRolesUser);

        Group NoRolesGroup = new Group();
        NoRolesGroup.setName("No roles");
        NoRolesGroup.addUser(NoRolesUser);
        groupRepository.save(NoRolesGroup);

        User OneRoleUser = new User();
        OneRoleUser.setUsername(ONE_ROLE_USER);
        OneRoleUser.setPassword("pass");
        userRepository.save(OneRoleUser);

        Group OneRoleGroup = new Group();
        OneRoleGroup.setName("One role");
        OneRoleGroup.addUser(OneRoleUser);
        OneRoleGroup.addRole(Role.TASK_READER);
        groupRepository.save(OneRoleGroup);

        User TwoRolesUser = new User();
        TwoRolesUser.setUsername(TWO_ROLES_USER);
        TwoRolesUser.setPassword("pass");
        userRepository.save(TwoRolesUser);

        Group TwoRolesGroup = new Group();
        TwoRolesGroup.setName("Two roles");
        TwoRolesGroup.addUser(TwoRolesUser);
        TwoRolesGroup.addRole(Role.TASK_READER);
        TwoRolesGroup.addRole(Role.TASK_WRITER);
        groupRepository.save(TwoRolesGroup);

        User TwoGroupsUser = new User();
        TwoGroupsUser.setUsername(TWO_GROUPS_USER);
        TwoGroupsUser.setPassword("pass");
        userRepository.save(TwoGroupsUser);

        Group TwoGroups1 = new Group();
        TwoGroups1.setName("Two groups. Group One");
        TwoGroups1.addUser(TwoGroupsUser);
        TwoGroups1.addRole(Role.TASK_READER);
        groupRepository.save(TwoGroups1);

        Group TwoGroups2 = new Group();
        TwoGroups2.setName("Two groups. Group Two");
        TwoGroups2.addUser(TwoGroupsUser);
        TwoGroups2.addRole(Role.TASK_WRITER);
        groupRepository.save(TwoGroups2);
    }

    @Test
    void returnFalseIfUsernameNotFound(){
        User fakeUser = new User();
        fakeUser.setUsername("fake");
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasRole(fakeUser, role));
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasAnyRole(fakeUser, new HashSet<>(Collections.singletonList(role))));
        assert !rolesChecker.hasAnyRole(fakeUser, new HashSet<>(Arrays.asList(Role.values())));
    }

    @Test
    void returnFalseIfUserHasNoGroup(){
        User user = new User();
        user.setUsername(NO_GROUP_USER);
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasRole(user, role));
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasAnyRole(user, new HashSet<>(Collections.singletonList(role))));
        assert !rolesChecker.hasAnyRole(user, new HashSet<>(Arrays.asList(Role.values())));
    }

    @Test
    void returnFalseIfGroupHasNoRoles(){
        User user = new User();
        user.setUsername(NO_ROLES_USER);
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasRole(user, role));
        assert Arrays.stream(Role.values()).noneMatch(role -> rolesChecker.hasAnyRole(user, new HashSet<>(Collections.singletonList(role))));
        assert !rolesChecker.hasAnyRole(user, new HashSet<>(Arrays.asList(Role.values())));
    }

    @Test
    void returnTrueOnSingleCheckIfUserHasThisRole(){
        User user = new User();
        user.setUsername(ONE_ROLE_USER);
        assert rolesChecker.hasRole(user, Role.TASK_READER);
    }

    @Test
    void returnTrueOnMultipleCheckIfUserHasThisRole(){
        User user = new User();
        user.setUsername(ONE_ROLE_USER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER, Role.TASK_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.values());
    }

    @Test
    void returnFalseIfUserAskedRoleDoesntMatchExisting(){
        User user = new User();
        user.setUsername(ONE_ROLE_USER);
        assert Arrays.stream(Role.values())
                .filter(r -> r != Role.TASK_READER)
                .noneMatch(role -> rolesChecker.hasRole(user, role));
        assert !rolesChecker.hasAnyRole(user, Role.TASK_WRITER, Role.DOCUMENT_READER);
        assert !rolesChecker.hasAnyRole(user,
                Arrays.stream(Role.values()).filter(r -> r != Role.TASK_READER).toArray(Role[]::new));
    }

    @Test
    void returnTrueOnSingleCheckIfUserHasMultipleRolesInOneGroup(){
        User user = new User();
        user.setUsername(TWO_ROLES_USER);
        assert rolesChecker.hasRole(user, Role.TASK_READER);
        assert rolesChecker.hasRole(user, Role.TASK_WRITER);
    }

    @Test
    void returnTrueOnMultipleCheckIfUserHasMultipleRolesInOneGroup(){
        User user = new User();
        user.setUsername(TWO_ROLES_USER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER, Role.DOCUMENT_READER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_WRITER, Role.DOCUMENT_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER, Role.TASK_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.values());
    }

    @Test
    void returnTrueOnSingleCheckIfUserHasMultipleRolesInMultipleGroups(){
        User user = new User();
        user.setUsername(TWO_GROUPS_USER);
        assert rolesChecker.hasRole(user, Role.TASK_READER);
        assert rolesChecker.hasRole(user, Role.TASK_WRITER);
    }

    @Test
    void returnTrueOnMultipleCheckIfUserHasMultipleRolesInMultipleGroups(){
        User user = new User();
        user.setUsername(TWO_GROUPS_USER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER, Role.DOCUMENT_READER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_WRITER, Role.DOCUMENT_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.TASK_READER, Role.TASK_WRITER);
        assert rolesChecker.hasAnyRole(user, Role.values());
    }
}
