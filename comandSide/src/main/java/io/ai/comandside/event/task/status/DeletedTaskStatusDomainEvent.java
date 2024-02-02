package io.ai.comandside.event.task.status;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeletedTaskStatusDomainEvent extends DomainEvent {

    private final UUID taskStatusId;

    public DeletedTaskStatusDomainEvent(Object source, UUID taskStatusId) {
        super(source);
        this.taskStatusId = taskStatusId;
    }
}
