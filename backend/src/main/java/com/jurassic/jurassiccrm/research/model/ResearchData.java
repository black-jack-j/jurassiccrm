package com.jurassic.jurassiccrm.research.model;

import com.jurassic.jurassiccrm.document.model.Document;
import com.jurassic.jurassiccrm.document.model.DocumentType;
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

    public ResearchData() {
        super(DocumentType.RESEARCH_DATA);
    }
}
