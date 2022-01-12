package com.jurassic.jurassiccrm.task.model;

import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;

@FunctionalInterface
public interface TaskStateChanger {

    void execute() throws IllegalTaskStateChangeException;

}
