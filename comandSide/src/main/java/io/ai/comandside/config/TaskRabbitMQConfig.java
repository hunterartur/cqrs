package io.ai.comandside.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskRabbitMQConfig extends AbstractRabbitMQBindingConfig {

    public TaskRabbitMQConfig(RabbitAdmin rabbitAdmin) {
        super(rabbitAdmin);
    }

    @Autowired
    @ConditionalOnProperty(value = "rabbit.exchanges.task.enable", havingValue = "true")
    @Override
    public void declare(
            @Value("${rabbit.exchanges.task.queues.task.route-keys}") List<String> routeKeys,
            @Value("${rabbit.exchanges.task.name}") String exchangeName,
            @Value("${rabbit.exchanges.task.queues.task.name}") String queueName
    ) {
        binding(routeKeys, exchangeName, queueName);
    }
}
