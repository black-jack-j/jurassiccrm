package com.jurassic.jurassiccrm.accesscontroll.entity;

import com.jurassic.jurassiccrm.research.entity.Research;
import com.jurassic.jurassiccrm.task.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private boolean enabled;
    private boolean accountNonExpired;

    private String firstName;

    private String lastName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Task> tasks = new HashSet<>();

    public boolean addTask(Task task) {
        task.setAssignee(this);
        return tasks.add(task);
    }

    public boolean removeTask(Task task) {
        task.setAssignee(null);
        return tasks.remove(task);
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    private Set<Research> researches;

    public boolean addRole(Role role) {
        boolean changed = roles.add(role);
        role.getUsers().add(this);
        return changed;
    }

    public boolean removeRole(Role role) {
        boolean changed = roles.remove(role);
        role.getUsers().remove(this);
        return changed;
    }

    @ManyToMany(targetEntity = Group.class, mappedBy = "users")
    private Set<Group> groups = new HashSet<>();

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
