package com.jurassic.jurassiccrm.task.model;

public enum TaskType {
    RESEARCH(Constants.RESEARCH),
    INCUBATION(Constants.INCUBATION),
    AVIARY_CREATION(Constants.AVIARY_CREATION);

    private final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Constants {
        public static final String RESEARCH = "RESEARCH";
        public static final String INCUBATION = "INCUBATION";
        public static final String AVIARY_CREATION = "AVIARY_CREATION";
    }
}
