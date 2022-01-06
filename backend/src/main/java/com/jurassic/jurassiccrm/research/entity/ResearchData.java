package com.jurassic.jurassiccrm.research.entity;

import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class ResearchData extends Document {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Research research;

    @Lob
    @Column(nullable = false)
    private byte[] attachment;
}
