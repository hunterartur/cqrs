package io.ai.comandside.service;

import io.ai.comandside.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class MessagePublisher {

    private final ApplicationEventPublisher publisher;

    private final RabbitTemplate rabbitTemplate;

    public void eventPublishAndSend(DomainEvent domainEvent, String routeKey, String exchange) {
        publisher.publishEvent(domainEvent);
        rabbitTemplate.convertAndSend(exchange, routeKey, domainEvent);
    }
}
