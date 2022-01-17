package com.jurassic.jurassiccrm.accesscontroll.model;

import java.util.Optional;

public enum Role {
    DOCUMENT_READER,
    DINOSAUR_PASSPORT_READER,
    AVIARY_PASSPORT_READER,
    THEME_ZONE_PROJECT_READER,
    TECHNOLOGICAL_MAP_READER,
    RESEARCH_DATA_READER,
    DOCUMENT_WRITER,
    DINOSAUR_PASSPORT_WRITER,
    AVIARY_PASSPORT_WRITER,
    THEME_ZONE_PROJECT_WRITER,
    TECHNOLOGICAL_MAP_WRITER,
    RESEARCH_DATA_WRITER,
    TASK_READER,
    INCUBATION_TASK_READER,
    AVIARY_BUILDING_TASK_READER,
    RESEARCH_TASK_READER,
    TASK_WRITER,
    INCUBATION_TASK_WRITER,
    AVIARY_BUILDING_TASK_WRITER,
    RESEARCH_TASK_WRITER,
    SECURITY_READER,
    SECURITY_WRITER,
    ADMIN;

    public static Optional<Role> getByName(String name){
        try{
            return Optional.of(Role.valueOf(name));
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }

    public String roleName(){
        return "ROLE_" + name();
    }
}
