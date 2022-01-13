package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.document.controller.validation.FileSizeConstraint;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResearchDataInputTO extends DocumentInputTO{
    @NotBlank
    private Long researchId;

    @NotBlank
    @FileSizeConstraint
    private MultipartFile attachment;

    public ResearchData toResearchData() throws IOException {
        ResearchData researchData = new ResearchData();
        setBaseFields(researchData);
        researchData.setResearch(new Research(researchId));
        researchData.setAttachmentName(attachment.getName());
        researchData.setAttachment(attachment.getBytes());
        return researchData;
    }
}
