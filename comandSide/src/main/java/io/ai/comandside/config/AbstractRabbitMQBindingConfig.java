package io.ai.comandside.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRabbitMQBindingConfig {

    protected final RabbitAdmin rabbitAdmin;

    public abstract void declare(List<String> routeKeys, String exchangeName, String queueName);

    protected void binding(List<String> routeKeys, String exchangeName, String projectQueueName) {
        var exchange = new DirectExchange(exchangeName, true, false);
        var queue = new Queue(projectQueueName);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        for (var routeKey : routeKeys) {
            var binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), routeKey, null);
            rabbitAdmin.declareBinding(binding);
            log.info("Created bindings: {} -> ({}) -> {}", exchange.getName(), routeKey, queue.getName());
        }
    }
}
