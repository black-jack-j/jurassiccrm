package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Base64;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewResearchDataInputTO extends DocumentInputTO {

    @NotBlank
    private String researchName;

    @NotBlank
    private String attachmentName;

    @NotEmpty
    private String attachmentBase64Encoded;

    public ResearchData toResearchData() {
        ResearchData researchData = new ResearchData();
        setBaseFields(researchData);
        Research newResearch = new Research();
        newResearch.setName(researchName);
        researchData.setResearch(newResearch);
        researchData.setAttachmentName(attachmentName);
        researchData.setAttachment(Base64.getDecoder().decode(attachmentBase64Encoded));
        return researchData;
    }

}
