package com.jurassic.jurassiccrm.research.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String goal;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "user_table_researches",
            joinColumns = {@JoinColumn(name = "researches_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private Set<User> researchers;
}
