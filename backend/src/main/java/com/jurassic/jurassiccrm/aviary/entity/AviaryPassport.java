package com.jurassic.jurassiccrm.aviary.entity;

import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Getter
@Setter
public class AviaryPassport {

    @Id
    @Generated
    private Long id;

    @ManyToOne
    private Document baseDocument;

    @Enumerated(value = EnumType.STRING)
    private AviaryTypes aviaryType;

    @Column(unique = true)
    private Long code;

    private Date builtDate;

    private Integer revisionPeriod;

    private String status;

    private String description;
}
