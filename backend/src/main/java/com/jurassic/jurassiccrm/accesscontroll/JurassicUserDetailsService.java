package com.jurassic.jurassiccrm.accesscontroll;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class JurassicUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private RoleHierarchyAuthoritiesMapper roleHierarchyAuthoritiesMapper;

    @Autowired
    public JurassicUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userSearchResult = userRepository.findByUsername(username);

        return userSearchResult.map(this::getUserDetailsForUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private UserDetails getUserDetailsForUser(User user) {
        Collection<? extends GrantedAuthority> authorities = roleHierarchyAuthoritiesMapper.mapAuthorities(getActualAuthorities(user));

        return new JurassicUserDetails(user, authorities);
    }

    private Collection<? extends GrantedAuthority> getActualAuthorities(User user) {
        Set<Role> roles = user.getRoles();

        return roles.stream()
                .map(Role::roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
