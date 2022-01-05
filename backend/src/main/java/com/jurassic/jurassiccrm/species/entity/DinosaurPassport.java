package com.jurassic.jurassiccrm.species.entity;

import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class DinosaurPassport extends Document {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Species species;

    @Column(nullable = false)
    private String dinosaurName;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Timestamp incubated;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;
}
