package com.jurassic.jurassiccrm.research.entity;

import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class ResearchData extends Document {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Research research;

    @Lob
    @Column(nullable = false)
    private byte[] attachment;
}
