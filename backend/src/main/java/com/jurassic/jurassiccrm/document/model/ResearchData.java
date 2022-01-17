package com.jurassic.jurassiccrm.document.model;

import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Column(nullable = false)
    private String attachmentName;

    public ResearchData() {
        super(DocumentType.RESEARCH_DATA);
    }
}
