package io.ai.comandside.event.task;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangedTaskStatusDomainEvent extends DomainEvent {


    private final UUID taskId;

    private final UUID taskStatusId;

    public ChangedTaskStatusDomainEvent(Object source, UUID taskId, UUID taskStatusId) {
        super(source);
        this.taskId = taskId;
        this.taskStatusId = taskStatusId;
    }
}