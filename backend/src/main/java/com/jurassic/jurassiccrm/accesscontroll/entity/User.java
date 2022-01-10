package com.jurassic.jurassiccrm.accesscontroll.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@ToString
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Department department;

    @ManyToMany(targetEntity = Group.class, mappedBy = "users")
    private Set<Group> groups = new HashSet<>();

    public Set<Role> getRoles(){
        return groups.stream().flatMap(g -> g.getRoles().stream()).collect(Collectors.toSet());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!this.getClass().equals(other.getClass())) return false;
        User that = (User) other;
        return this.id != null && this.id.equals(that.id);
    }
}
