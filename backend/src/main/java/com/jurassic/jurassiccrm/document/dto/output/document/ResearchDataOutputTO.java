package com.jurassic.jurassiccrm.document.dto.output.document;

import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
