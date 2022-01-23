package com.jurassic.jurassiccrm.logging.model;

public enum LogActionType {
    CREATE("created"),
    UPDATE("updated"),
    DELETE("deleted");

    private final String name;

    LogActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
