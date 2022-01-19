package com.jurassic.jurassiccrm.wiki;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WikiDTOCreate {
    private Long id;
    private String title;
    private String text;
    private MultipartFile image;
    private String[] relatedPages;

    public WikiDTOCreate(){

    }
}
