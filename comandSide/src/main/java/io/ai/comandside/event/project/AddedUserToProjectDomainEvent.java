package io.ai.comandside.event.project;

import io.ai.comandside.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddedUserToProjectDomainEvent extends DomainEvent {

    private final UUID projectId;
    private final UUID userId;

    public AddedUserToProjectDomainEvent(Object source, UUID projectId, UUID userId) {
        super(source);
        this.projectId = projectId;
        this.userId = userId;
    }
}
