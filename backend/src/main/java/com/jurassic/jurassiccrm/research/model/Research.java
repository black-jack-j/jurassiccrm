package com.jurassic.jurassiccrm.research.model;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.common.model.SimpleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Research implements SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String goal;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "user_table_researches",
            joinColumns = {@JoinColumn(name = "researches_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private Set<User> researchers = new HashSet<>();

    public boolean addResearcher(User researcher){
        return researchers.add(researcher);
    }

    public Research(Long id) {
        this.id = id;
    }

    public Research(String name) {
        this.name = name;
    }
}
