package com.jurassic.jurassiccrm.document.model;

public enum DocumentType {
    THEME_ZONE_PROJECT(Constants.THEME_ZONE_PROJECT),
    DINOSAUR_PASSPORT(Constants.DINOSAUR_PASSPORT),
    TECHNOLOGICAL_MAP(Constants.TECHNOLOGICAL_MAP),
    AVIARY_PASSPORT(Constants.AVIARY_PASSPORT),
    RESEARCH_DATA(Constants.RESEARCH_DATA);

    private final String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Constants {
        public static final String THEME_ZONE_PROJECT = "THEME_ZONE_PROJECT";
        public static final String DINOSAUR_PASSPORT = "DINOSAUR_PASSPORT";
        public static final String TECHNOLOGICAL_MAP = "TECHNOLOGICAL_MAP";
        public static final String AVIARY_PASSPORT = "AVIARY_PASSPORT";
        public static final String RESEARCH_DATA = "RESEARCH_DATA";
    }
}
