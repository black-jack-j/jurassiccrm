package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.exception.UnauthorisedUserOperationException;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final RolesChecker rolesChecker;

    @Setter
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       GroupRepository groupRepository,
                       RolesChecker rolesChecker) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.rolesChecker = rolesChecker;
    }

    public User createUser(User user, User creator) {
        checkWritePermission(creator);
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException(String.format("User with username %s already exists", user.getUsername()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        refreshGroupMembers(user);
        val userWithGroups = userRepository.findById(savedUser.getId());
        if (!userWithGroups.isPresent())
            throw new IllegalStateException("User was saved but wasn't found");
        return userWithGroups.get();
    }

    public List<User> getAllByRolesAll(List<Role> roles) {
        return userRepository.findUsersByRolesAll(roles, roles.size());
    }

    public List<User> getAllByRolesAny(List<Role> roles) {
        return userRepository.findUsersByRolesAny(roles);
    }

    public User updateUser(Long id, User user, User updater) {
        checkWritePermission(updater);
        val currentUser = userRepository.findById(id);
        if (!currentUser.isPresent())
            throw new IllegalArgumentException(String.format("User with id %d doesn't exist", id));
        user.setId(id);
        refreshGroupMembers(user, currentUser.get());
        return userRepository.save(user);
    }

    public List<User> getAllUsers(User requester) {
        checkReadPermission(requester);
        return userRepository.findAll();
    }

    public User getUserById(User requester, Long id) {
        checkReadPermission(requester);
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("No user with id '%s' exists", id)));
    }

    public Set<Role> getUserRoles(User user) {
        return user.getGroups().stream().flatMap(group -> group.getRoles().stream()).collect(Collectors.toSet());
    }

    private void refreshGroupMembers(User newUser) {
        refreshGroupMembers(newUser, new User());
    }

    private void refreshGroupMembers(User newUser, User currentUser) {
        val currentUserGroups = currentUser.getGroups();
        val newUserGroups = newUser.getGroups();

        val groupsToAddUsers = newUserGroups.stream()
                .filter(newGroup -> currentUserGroups.stream().noneMatch(newGroup::equalsById))
                .collect(Collectors.toSet());

        val groupsToRemoveUsers = currentUserGroups.stream()
                .filter(currentGroup -> newUserGroups.stream().noneMatch(currentGroup::equalsById))
                .collect(Collectors.toSet());

        groupsToAddUsers.forEach(group -> {
            val fullGroup = getGroupOrFail(group.getId());
            fullGroup.addUser(userRepository.getOne(newUser.getId()));
            groupRepository.save(fullGroup);
        });

        groupsToRemoveUsers.forEach(group -> {
            val fullGroup = getGroupOrFail(group.getId());
            fullGroup.removeUser(newUser.getId());
            groupRepository.save(fullGroup);
        });
    }

    private Group getGroupOrFail(Long id) {
        val groupOpt = groupRepository.findById(id);
        if (!groupOpt.isPresent())
            throw new IllegalArgumentException(String.format("group with id %s doesn't exist", id));
        return groupOpt.get();
    }

    private void checkReadPermission(User creator) {
        if (!rolesChecker.hasAnyRole(creator, Role.SECURITY_READER, Role.ADMIN))
            throw new UnauthorisedUserOperationException();
    }

    private void checkWritePermission(User creator) {
        if (!rolesChecker.hasAnyRole(creator, Role.SECURITY_WRITER, Role.ADMIN))
            throw new UnauthorisedUserOperationException();
    }
}
