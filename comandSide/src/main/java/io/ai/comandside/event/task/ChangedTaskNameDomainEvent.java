package io.ai.comandside.event.task;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangedTaskNameDomainEvent extends DomainEvent {

    private final UUID taskId;

    private final String newName;

    public ChangedTaskNameDomainEvent(Object source, UUID taskId, String newName) {
        super(source);
        this.taskId = taskId;
        this.newName = newName;
    }
}