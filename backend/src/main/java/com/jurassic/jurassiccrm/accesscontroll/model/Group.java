package com.jurassic.jurassiccrm.accesscontroll.model;

import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "group_table")
@NoArgsConstructor
public class Group implements SimpleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public boolean addRole(Role role) {
        return roles.add(role);
    }

    public boolean removeRole(Role role) {
        return roles.remove(role);
    }

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    public boolean addUser(User user) {
        boolean changed = users.add(user);
        user.getGroups().add(this);
        return changed;
    }

    public boolean removeUser(Long userId) {
        return users.removeIf(u -> u.getId().equals(userId));
    }

    public Boolean equalsById(Group other) {
        return this.id.equals(other.id);
    }

    public Group(Long id) {
        this.id = id;
    }

    public Group(String name) {
        this.name = name;
    }
}
