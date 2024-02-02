package io.ai.comandside.event.project;

import io.ai.comandside.event.DomainEvent;
import io.ai.comandside.valueobject.Information;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangedProjectNameDomainEvent extends DomainEvent {

    private final UUID projectId;

    private final Information information;

    public ChangedProjectNameDomainEvent(Object source, UUID projectId, Information information) {
        super(source);
        this.projectId = projectId;
        this.information = information;
    }
}
