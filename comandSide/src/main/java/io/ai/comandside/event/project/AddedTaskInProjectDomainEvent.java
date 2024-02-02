package io.ai.comandside.event.project;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddedTaskInProjectDomainEvent extends DomainEvent {

    private final UUID projectId;
    private final UUID taskId;

    public AddedTaskInProjectDomainEvent(Object source, UUID projectId, UUID taskId) {
        super(source);
        this.projectId = projectId;
        this.taskId = taskId;
    }
}
