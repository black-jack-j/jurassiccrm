package com.jurassic.jurassiccrm.themezone.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Setter
@Getter
@Entity
public class ThemeZoneProject {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    private Document baseDocument;

    @Column(unique = true)
    private String projectName;

    @ManyToOne
    private User manager;

}
