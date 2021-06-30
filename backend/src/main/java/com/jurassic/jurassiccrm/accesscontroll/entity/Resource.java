package com.jurassic.jurassiccrm.accesscontroll.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "resource_type")
@Setter
@Getter
public abstract class Resource {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "resources")
    private Set<Role> roles = new HashSet<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!this.getClass().equals(other.getClass())) return false;
        Resource that = (Resource) other;
        return this.id != null && this.id.equals(that.id);
    }
}
