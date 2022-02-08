package com.jurassic.jurassiccrm.document.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jurassic.jurassiccrm.document.model.DocumentType;
import com.jurassic.jurassiccrm.document.model.ResearchData;
import com.jurassic.jurassiccrm.research.model.Research;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResearchDataInputTO extends DocumentInputTO {

    @NotNull
    @ApiModelProperty(required = true)
    private ResearchDataNameIdTO researchNameId;

    @ApiModelProperty
    private boolean newResearch;

    @JsonIgnore
    private MultipartFile attachment;

    public ResearchData toResearchData() throws IOException {
        Research research;
        if (!newResearch) {
            research = new Research(researchNameId.getId());
        } else {
            research = new Research(researchNameId.getName());
        }
        ResearchData researchData = new ResearchData();
        researchData.setResearch(research);
        researchData.setAttachment(attachment.getBytes());
        setBaseFields(researchData);

        return researchData;
    }

    @Data
    @ApiModel
    public static class ResearchDataNameIdTO {

        @Nullable
        @ApiModelProperty
        private Long id;
        @Nullable
        @ApiModelProperty
        private String name;
    }


}