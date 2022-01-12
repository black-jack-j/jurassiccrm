package com.jurassic.jurassiccrm.task.model.state;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum TaskState {
    CLOSED,
    RESOLVED,
    IN_PROGRESS(new TaskState[]{RESOLVED}),
    OPEN(new TaskState[]{IN_PROGRESS});

    static {
        RESOLVED.possibleNextStates = Arrays.asList(OPEN, CLOSED);
    }

    private List<TaskState> possibleNextStates;

    TaskState(TaskState[] possibleNextStates) {
        this.possibleNextStates = Arrays.asList(possibleNextStates);
    }

    TaskState() {
        this.possibleNextStates = Collections.emptyList();
    }

    public List<TaskState> getPossibleNextStates() {
        return this.possibleNextStates;
    }

}
