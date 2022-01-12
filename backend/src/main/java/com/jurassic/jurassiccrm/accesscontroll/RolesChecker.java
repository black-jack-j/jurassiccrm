package com.jurassic.jurassiccrm.accesscontroll;

import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class RolesChecker {
    private final UserRepository userRepository;

    @Autowired
    public RolesChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean hasRole(User user, Role role){
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser.map(value -> value.getRoles().contains(role)).orElse(false);
    }

    public boolean hasAnyRole(User user, Set<Role> roles){
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser.map(u -> roles.stream().anyMatch(r -> u.getRoles().contains(r))).orElse(false);
    }

    public boolean hasAnyRole(User user, Role... roles){
        return hasAnyRole(user, new HashSet<>(Arrays.asList(roles)));
    }
}
