package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class WikiDTO {
    private Long id;
    private String title;
    private String text;
    private byte[] image;
    private List<String> relatedPages;

    public WikiDTO(Long id, String title, String text, byte[] image, List<String> relatedPages) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.image = image;
        this.relatedPages = relatedPages;
    }
}
