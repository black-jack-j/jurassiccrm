package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import lombok.Data;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

@Data
public class WikiDTO {
    private Long id;
    private String title;
    private String text;
    private String image;
    private List<String> relatedPages;

    public WikiDTO(Long id, String title, String text, byte[] image, List<String> relatedPages) {
        this.id = id;
        this.title = title;
        this.text = text;
        if (image != null){
            this.image = new String(Base64Utils.encode(image));
        }
        this.relatedPages = relatedPages;
    }

    public WikiDTO(Wiki wiki){
        this.id=wiki.getId();
        this.title = wiki.getTitle();
        this.text = wiki.getText();
        if (wiki.getImage() != null){
            this.image = new String(Base64Utils.encode(wiki.getImage()));
        }
        List<String> relatedPages = new ArrayList<>();
        List<Wiki> related = wiki.getRelatedPages();
        for (Wiki wiki_ : related){
            relatedPages.add(wiki_.getTitle());
        }
        this.relatedPages = relatedPages;
    }

    public WikiDTO(){

    }
}
