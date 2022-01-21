package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import lombok.Data;
import org.apache.hc.core5.http.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
