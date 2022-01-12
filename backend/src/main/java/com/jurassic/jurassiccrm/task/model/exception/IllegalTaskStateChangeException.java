package com.jurassic.jurassiccrm.task.model.exception;

import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.state.TaskState;

public class IllegalTaskStateChangeException extends Throwable {

    private final Task targetTask;
    private final TaskState targetState;

    private final static String EXCEPTION_MESSAGE_TEMPLATE = "Exception trying to move task %s to state %s";

    public IllegalTaskStateChangeException(Task targetTask, TaskState targetState) {
        super(String.format(EXCEPTION_MESSAGE_TEMPLATE, targetTask, targetState));
        this.targetTask = targetTask;
        this.targetState = targetState;
    }
}
