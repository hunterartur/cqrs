package io.ai.comandside.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class DomainEvent extends ApplicationEvent {

    /**
     * Имя события
     */
    private String nameEvent;

    public DomainEvent(Object source) {
        super(source);
    }
}
