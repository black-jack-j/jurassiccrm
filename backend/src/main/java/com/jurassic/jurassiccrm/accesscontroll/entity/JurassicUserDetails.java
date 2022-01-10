package com.jurassic.jurassiccrm.accesscontroll.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JurassicUserDetails extends User {

    @Getter
    private final com.jurassic.jurassiccrm.accesscontroll.entity.User userInfo;

    public JurassicUserDetails(com.jurassic.jurassiccrm.accesscontroll.entity.User user,
                               Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(user.getUsername(), user.getPassword(), true, true,
                true, true, grantedAuthorities);
        this.userInfo = user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private static List<String> getPrivileges(Collection<Role> roles) {
        return roles.stream().map(Role::name).collect(Collectors.toList());
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        return privileges.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
