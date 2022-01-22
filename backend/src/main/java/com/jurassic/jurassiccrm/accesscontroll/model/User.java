package com.jurassic.jurassiccrm.accesscontroll.model;

import com.jurassic.jurassiccrm.task.model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
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

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

    public boolean addTask(Task task) {
        task.setAssignee(this);
        return tasks.add(task);
    }

    public boolean removeTask(Task task) {
        task.setAssignee(null);
        return tasks.remove(task);
    }

    @ManyToMany(targetEntity = Group.class, mappedBy = "users")
    @ToString.Exclude
    private Set<Group> groups = new HashSet<>();

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Set<Role> getRoles() {
        return groups.stream().flatMap(g -> g.getRoles().stream()).collect(Collectors.toSet());
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
        this.password = "";
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
