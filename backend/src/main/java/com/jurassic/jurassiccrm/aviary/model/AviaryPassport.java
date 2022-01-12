package com.jurassic.jurassiccrm.aviary.model;

import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class AviaryPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Document baseDocument;

    @ManyToOne
    private AviaryType aviaryType;

    @Column(unique = true)
    private Long code;

    private Date builtDate;

    private Integer revisionPeriod;

    private String status;

    private String description;
}

