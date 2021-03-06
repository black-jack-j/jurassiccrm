package com.jurassic.jurassiccrm.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.common.dto.SimpleEntityOutputTO;
import com.jurassic.jurassiccrm.task.dto.validation.TaskTOMessages;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import com.jurassic.jurassiccrm.task.priority.dao.TaskPriorityRepository;
import com.jurassic.jurassiccrm.validation.existence.NullOrExists;
import com.jurassic.jurassiccrm.validation.groups.OnCreate;
import com.jurassic.jurassiccrm.validation.groups.OnUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(
        subTypes = {AviaryTaskDTO.class, IncubationTaskDTO.class, ResearchTaskDTO.class},
        discriminator = "taskType"
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "taskType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AviaryTaskDTO.class, name = TaskType.Constants.AVIARY_CREATION),
        @JsonSubTypes.Type(value = ResearchTaskDTO.class, name = TaskType.Constants.RESEARCH),
        @JsonSubTypes.Type(value = IncubationTaskDTO.class, name = TaskType.Constants.INCUBATION)
})
public abstract class TaskTO {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = TaskTOMessages.NAME_CONSTRAINT_VIOLATION, groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @JsonProperty("currentState")
    private TaskState status;

    private List<TaskState> possibleNextStates = new ArrayList<>();

    @NotNull(message = TaskTOMessages.TASK_TYPE_CONSTRAINT_VIOLATION, groups = {OnCreate.class, OnUpdate.class})
    @ApiModelProperty
    private TaskType taskType;

    private Instant created;

    private Instant lastUpdated;

    @NullOrExists(
            message = TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION,
            repository = TaskPriorityRepository.class, groups = {OnCreate.class, OnUpdate.class}
            )
    private SimpleEntityOutputTO priority;

    @NullOrExists(
            message = TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION,
            repository = UserRepository.class, groups = {OnCreate.class, OnUpdate.class}
            )
    private Long createdById;

    @NullOrExists(
            message = TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION,
            repository = UserRepository.class, groups = {OnCreate.class, OnUpdate.class}
            )
    private Long lastUpdaterId;

    @NullOrExists(
            message = TaskTOMessages.ENTITY_EXISTENCE_CONSTRAINT_VIOLATION,
            repository = UserRepository.class, groups = {OnCreate.class, OnUpdate.class}
    )
    private Long assigneeId;

    private String description;

    protected void setBaseFields(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.assigneeId = task.getAssignee().getId();
        this.createdById = task.getCreatedBy().getId();
        this.created = task.getCreated();
        this.status = task.getStatus();
        this.description = task.getDescription();
        this.lastUpdated = task.getLastUpdated();
        this.lastUpdaterId = task.getLastUpdater().getId();
        this.possibleNextStates = task.getStatus().getPossibleNextStates();
        this.priority = new SimpleEntityOutputTO(task.getPriority().getId(), task.getPriority().getName());
        this.taskType = task.getTaskType();
    }
}
