package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class AviaryPassport extends Document {

    @ManyToOne
    private AviaryType aviaryType;

    @Column(unique = true, nullable = false)
    private Long code;

    @Column(nullable = false)
    private Date builtDate;

    @Column(nullable = false)
    private Integer revisionPeriod;

    @Column(nullable = false)
    private String status;

    public AviaryPassport() {
        super(DocumentType.AVIARY_PASSPORT);
    }
}
