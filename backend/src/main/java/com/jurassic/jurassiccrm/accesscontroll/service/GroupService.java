package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.RolesChecker;
import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.common.model.EntityNotExistException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final RolesChecker rolesChecker;

    @Transactional
    public Group createGroup(Group group, User creator) {
        checkWriteRights(creator);
        if (groupRepository.existsByName(group.getName()))
            throw new IllegalArgumentException(String.format("Group with this name %s already exists", group.getName()));
        updateUsersWithOnlyIdToFullUsers(group);
        return groupRepository.save(group);
    }

    public Group getGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new EntityNotExistException(id));
    }

    @Transactional
    public Group updateGroup(Long id, Group group, User updater) {
        checkWriteRights(updater);
        if (!groupRepository.existsById(id))
            throw new IllegalArgumentException(String.format("Group with this id %d doesn't exist", id)); /** TODO: replace with {@link com.jurassic.jurassiccrm.common.model.EntityNotExistException}*/
        group.setId(id);
        updateUsersWithOnlyIdToFullUsers(group);
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups(User requester) {
        checkReadRights(requester);
        return groupRepository.findAll();
    }

    public List<User> getAvailableUsers() {
        return userRepository.findAll();
    }

    public List<Role> getAvailableRoles() {
        return Arrays.asList(Role.values());
    }

    public void addUser(Long groupId, Long userId, User redactor) {
        checkWriteRights(redactor);
        Group group = getGroupOrThrow(groupId);
        User user = getUserOrThrow(userId);
        group.addUser(user);
        groupRepository.save(group);
    }

    public void removeUser(Long groupId, Long userId, User redactor) {
        checkWriteRights(redactor);
        Group group = getGroupOrThrow(groupId);
        if (!group.removeUser(userId))
            throw new IllegalArgumentException(String.format("User with id %d is not in a group with id %d", userId, groupId));
        groupRepository.save(group);
    }

    private Group getGroupOrThrow(Long groupId) {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        if (!groupOpt.isPresent())
        /** TODO: replace with {@link com.jurassic.jurassiccrm.common.model.EntityNotExistException}*/
            throw new IllegalArgumentException(String.format("Group with this id %d doesn't exist", groupId));
        return groupOpt.get();
    }

    private User getUserOrThrow(Long userId) {
        if (!userRepository.existsById(userId))
        /** TODO: replace with {@link com.jurassic.jurassiccrm.common.model.EntityNotExistException}*/
            throw new IllegalArgumentException(String.format("User with this id %d doesn't exist", userId));
        return userRepository.getOne(userId);
    }

    private void updateUsersWithOnlyIdToFullUsers(Group group) {
        val savableUsers = new HashSet<User>();
        group.getUsers().forEach(u -> savableUsers.add(userRepository.getOne(u.getId())));
        group.setUsers(savableUsers);
    }

    private void checkReadRights(User user) {
        if (!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.SECURITY_READER))
            throw new UnauthorisedGroupOperationException();
    }

    private void checkWriteRights(User user) {
        if (!rolesChecker.hasAnyRole(user, Role.ADMIN, Role.SECURITY_WRITER))
            throw new UnauthorisedGroupOperationException();
    }

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository, RolesChecker rolesChecker) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.rolesChecker = rolesChecker;
    }
}
