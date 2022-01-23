package com.jurassic.jurassiccrm.task.model;

public enum TaskType {
    RESEARCH("research"),
    INCUBATION("incubation"),
    AVIARY_CREATION("aviary creation");

    private final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
