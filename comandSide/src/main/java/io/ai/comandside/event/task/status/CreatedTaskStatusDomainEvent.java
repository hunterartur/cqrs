package io.ai.comandside.event.task.status;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatedTaskStatusDomainEvent extends DomainEvent {

    private UUID id;

    private final String name;

    private final String color;

    public CreatedTaskStatusDomainEvent(Object source, UUID id, String name, String color) {
        super(source);
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
