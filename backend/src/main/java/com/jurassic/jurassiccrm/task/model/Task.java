package com.jurassic.jurassiccrm.task.model;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private TaskType taskType;

    @Enumerated(value = EnumType.STRING)
    private TaskState status = TaskState.OPEN;

    @Column(nullable = false)
    private Timestamp created;

    @JoinColumn(name = "priority_id")
    @ManyToOne
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(nullable = false, name = "created_by_id")
    private User createdBy;

    @Column(nullable = false)
    private Timestamp lastUpdated;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "last_updater_id")
    private User lastUpdater;

    private String description;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    public Task(String name, User creator) {
        this.name = name;
        this.createdBy = creator;
        this.lastUpdater = creator;
    }

    public void close() throws IllegalTaskStateChangeException {
        if (status == TaskState.RESOLVED) {
            status = TaskState.CLOSED;
        } else {
            throw new IllegalTaskStateChangeException(this, TaskState.CLOSED);
        }
    }

    public void resolve() throws IllegalTaskStateChangeException {
        if (status == TaskState.IN_PROGRESS) {
            status = TaskState.RESOLVED;
        } else {
            throw new IllegalTaskStateChangeException(this, TaskState.RESOLVED);
        }
    }

    public void reopen() throws IllegalTaskStateChangeException {
        if (status == TaskState.RESOLVED) {
            status = TaskState.OPEN;
        } else {
            throw new IllegalTaskStateChangeException(this, TaskState.OPEN);
        }
    }

    public void startProgress() throws IllegalTaskStateChangeException {
        if (status == TaskState.OPEN) {
            status = TaskState.IN_PROGRESS;
        } else {
            throw new IllegalTaskStateChangeException(this, TaskState.IN_PROGRESS);
        }
    }

    public TaskStateChanger getTaskStateChanger(TaskState nextTaskState) throws IllegalTaskStateChangeException {
        if (!status.getPossibleNextStates().contains(nextTaskState)) {
            throw new IllegalTaskStateChangeException(this, nextTaskState);
        }
        switch (nextTaskState) {
            case IN_PROGRESS: return this::startProgress;
            case RESOLVED: return this::resolve;
            case OPEN: return this::reopen;
            case CLOSED: return this::close;
            default: {
                throw new IllegalTaskStateChangeException(this, nextTaskState);
            }
        }
    }

    public void updateTask(User updater) {
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
        this.lastUpdater = updater;
    }

}
