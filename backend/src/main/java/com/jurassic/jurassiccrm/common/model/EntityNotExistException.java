package com.jurassic.jurassiccrm.common.model;

import lombok.Getter;

public class EntityNotExistException extends RuntimeException {

    @Getter
    private final Long id;

    public EntityNotExistException(Long id) {
        this.id = id;
    }
}
