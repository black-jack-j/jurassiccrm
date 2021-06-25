package com.jurassic.jurassiccrm.document.dto;

import com.jurassic.jurassiccrm.document.controller.validation.FileSizeConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentDTO {

    @NotBlank
    @Size(max = 255)
    private String documentName;

    @FileSizeConstraint
    private MultipartFile document;

    @Size(max = 255)
    private String description;

}
