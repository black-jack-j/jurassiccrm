package com.jurassic.jurassiccrm.wiki.model;

import java.util.List;

public class WikiPage {
    private String title;
    private String text;
    private String imageName;
    private List<String> relatedPages;

    public WikiPage(String title, String text, String imageName, List<String> relatedPages) {
        this.title = title;
        this.text = text;
        this.imageName = imageName;
        this.relatedPages = relatedPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<String> getRelatedPages() {
        return relatedPages;
    }

    public void setRelatedPages(List<String> relatedPages) {
        this.relatedPages = relatedPages;
    }
}
