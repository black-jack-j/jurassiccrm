package com.jurassic.jurassiccrm.wiki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
public class Wiki {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique = true)
    private String title;

    @Column(length = 10000)
    private String text;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Wiki> relatedPages;
}
