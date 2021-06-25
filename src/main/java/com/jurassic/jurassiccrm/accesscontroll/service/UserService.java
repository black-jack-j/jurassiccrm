package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User assignRoleToUser(Long roleId, Long userId) {
        User targetUser = getUserByIdOrThrowException(userId);

        Role roleToAdd = roleRepository.findById(roleId).orElseThrow(
                () -> new IllegalArgumentException("No User with id '"+roleId+"'")
        );
        if (targetUser.addRole(roleToAdd)) {
            return userRepository.save(targetUser);
        } else {
            return targetUser;
        }
    }

    private User getUserByIdOrThrowException(Long userId) {
        Optional<User> userSearchResult = userRepository.findById(userId);
        return userSearchResult.orElseThrow(
                () -> new IllegalArgumentException("No User with id '"+userId+"'"));
    }
}
