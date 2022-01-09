package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        savedUser.getGroups().stream()
                .filter(g -> !g.getUsers().contains(savedUser))
                .peek(g -> g.addUser(savedUser))
                .forEach(groupRepository::save);
        return savedUser;
    }

    //TODO: replace illegal argument exception
    public User getUserByIdOrThrowException(Long userId) {
        Optional<User> userSearchResult = userRepository.findById(userId);
        return userSearchResult.orElseThrow(
                () -> new IllegalArgumentException("No User with id '" + userId + "'"));
    }

    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }
}
