package com.jurassic.jurassiccrm.accesscontroll.model;

import com.jurassic.jurassiccrm.task.model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;

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

    @Lob
    private byte[] avatar;

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

    public boolean isAdmin() {
        return getRoles().contains(Role.ADMIN);
    }

    public boolean canReadDocuments() {
        val roles = getRoles();
        return isAdmin() || roles.contains(Role.DOCUMENT_READER) || roles.contains(Role.DOCUMENT_WRITER);
    }

    public boolean canReadDinosaurPassports() {
        val roles = getRoles();
        return (
                isAdmin() ||
                canReadDocuments() ||
                roles.contains(Role.DINOSAUR_PASSPORT_READER) ||
                roles.contains(Role.DINOSAUR_PASSPORT_WRITER)
        );
    }

    public boolean canReadAviaryPassports() {
        val roles = getRoles();
        return (
                isAdmin() ||
                canReadDocuments() ||
                roles.contains(Role.AVIARY_PASSPORT_READER) ||
                roles.contains(Role.AVIARY_PASSPORT_WRITER)
        );
    }

    public boolean canReadResearchData() {
        val roles = getRoles();
        return (
                isAdmin() ||
                canReadDocuments() ||
                roles.contains(Role.RESEARCH_DATA_READER) ||
                roles.contains(Role.RESEARCH_DATA_WRITER)
        );
    }

    public boolean canReadTechnologicalMaps() {
        val roles = getRoles();
        return (
                isAdmin() ||
                canReadDocuments() ||
                roles.contains(Role.TECHNOLOGICAL_MAP_READER) ||
                roles.contains(Role.TECHNOLOGICAL_MAP_WRITER)
        );
    }

    public boolean canReadThemeZoneProjects() {
        val roles = getRoles();
        return (
                isAdmin() ||
                canReadDocuments() ||
                roles.contains(Role.THEME_ZONE_PROJECT_READER) ||
                roles.contains(Role.THEME_ZONE_PROJECT_WRITER)
        );
    }

    public boolean canReadTasks() {
        val roles = getRoles();
        return isAdmin() || roles.contains(Role.TASK_READER) || roles.contains(Role.TASK_WRITER);
    }

    public boolean canReadIncubationTasks() {
        val roles = getRoles();
        return (
                canReadTasks() ||
                roles.contains(Role.INCUBATION_TASK_READER) ||
                roles.contains(Role.INCUBATION_TASK_WRITER)
        );
    }

    public boolean canReadAviaryCreationTasks() {
        val roles = getRoles();
        return (
                canReadTasks() ||
                roles.contains(Role.AVIARY_BUILDING_TASK_READER) ||
                roles.contains(Role.AVIARY_BUILDING_TASK_WRITER)
        );
    }

    public boolean canReadResearchTasks() {
        val roles = getRoles();
        return (
                canReadTasks() ||
                roles.contains(Role.RESEARCH_TASK_READER) ||
                roles.contains(Role.RESEARCH_TASK_WRITER)
        );
    }

}
