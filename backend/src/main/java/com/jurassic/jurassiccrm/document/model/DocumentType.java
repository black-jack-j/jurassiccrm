package com.jurassic.jurassiccrm.document.model;

public enum DocumentType {
    THEME_ZONE_PROJECT("Theme Zone Project"),
    DINOSAUR_PASSPORT("Dinosaur Passport"),
    TECHNOLOGICAL_MAP("Technological Map"),
    AVIARY_PASSPORT("Aviary Passport"),
    RESEARCH_DATA("Research Data");

    private final String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
