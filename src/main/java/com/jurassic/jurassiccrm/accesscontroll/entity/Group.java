package com.jurassic.jurassiccrm.accesscontroll.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_role",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    public boolean addRole(Role role) {
        boolean changed = roles.add(role);
        role.getGroups().add(this);
        return changed;
    }

    public boolean removeRole(Role role) {
        boolean changed = roles.remove(role);
        role.getUsers().remove(this);
        return changed;
    }

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private Set<User> users = new HashSet<>();

    public boolean addUser(User user) {
        boolean changed = users.add(user);
        user.getGroups().add(this);
        return changed;
    }

    public boolean removeUser(User user) {
        boolean changed = users.remove(user);
        user.getGroups().remove(this);
        return changed;
    }
}
