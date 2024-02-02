package io.ai.comandside.event.task;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AssignedExecutorToTaskDomainEvent extends DomainEvent {

    private final UUID taskId;

    private final UUID userId;

    public AssignedExecutorToTaskDomainEvent(Object source, UUID taskId, UUID userId) {
        super(source);
        this.taskId = taskId;
        this.userId = userId;
    }
}
