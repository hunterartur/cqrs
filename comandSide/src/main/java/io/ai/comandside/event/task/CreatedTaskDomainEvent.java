package io.ai.comandside.event.task;

import io.ai.comandside.event.DomainEvent;
import io.ai.comandside.service.TaskDomainService;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatedTaskDomainEvent extends DomainEvent {

    private final UUID taskId;

    private final UUID projectId;

    public CreatedTaskDomainEvent(TaskDomainService source, UUID taskId, UUID projectId) {
        super(source);
        this.projectId = projectId;
        this.taskId = taskId;
    }
}