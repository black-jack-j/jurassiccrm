package com.jurassic.jurassiccrm.document.dto.input;

import com.jurassic.jurassiccrm.document.controller.validation.FileSizeConstraint;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.model.Research;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Base64;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResearchDataInputTO extends DocumentInputTO{
    @NotNull
    private Long researchId;

    @NotBlank
    private String attachmentName;

    @NotEmpty
    private String attachmentBase64Encoded;

    public ResearchData toResearchData() throws IOException {
        ResearchData researchData = new ResearchData();
        setBaseFields(researchData);
        researchData.setResearch(new Research(researchId));
        researchData.setAttachmentName(attachmentName);
        researchData.setAttachment(Base64.getDecoder().decode(attachmentBase64Encoded));
        return researchData;
    }
}