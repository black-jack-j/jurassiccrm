package com.jurassic.jurassiccrm.accesscontroll.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity = Group.class, mappedBy = "roles")
    private Set<Group> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id")}
    )
    private Set<Privilege> privileges = new HashSet<>();

    public boolean addPrivilege(Privilege privilege) {
        privilege.getRoles().add(this);
        return privileges.add(privilege);
    }

    public boolean removePrivilege(Privilege privilege) {
        privilege.getRoles().remove(this);
        return privileges.remove(privilege);
    }

    @ManyToMany
    @JoinTable(
            name = "role_resource",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")}
    )
    private Set<Resource> resources = new HashSet<>();

    public boolean addResource(Resource resource) {
        resource.getRoles().add(this);
        return resources.add(resource);
    }

    public boolean removeResource(Resource resource) {
        resource.getRoles().remove(this);
        return resources.remove(resource);
    }

    public Role(String name, Collection<Privilege> privileges) {
        this.name = name;
        this.privileges = new HashSet<>(privileges);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!this.getClass().equals(other.getClass())) return false;
        Role that = (Role) other;
        return this.id != null && this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
