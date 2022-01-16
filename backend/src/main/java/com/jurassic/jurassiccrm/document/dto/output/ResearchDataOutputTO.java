package com.jurassic.jurassiccrm.document.dto.output;

import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResearchDataOutputTO extends DocumentOutputTO {
    private SimpleEntityOutputTO research;
    private byte[] attachment;
    private String attachmentName;

    public static ResearchDataOutputTO fromDocument(ResearchData document){
        ResearchDataOutputTO dto = new ResearchDataOutputTO();
        dto.setBaseFields(document);
        dto.setResearch(SimpleEntityOutputTO.fromEntity(document.getResearch()));
        dto.setAttachment(document.getAttachment());
        dto.setAttachmentName(document.getAttachmentName());
        return dto;
    }
}
