package io.ai.comandside.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectRabbitMQConfig extends AbstractRabbitMQBindingConfig {

    public ProjectRabbitMQConfig(RabbitAdmin rabbitAdmin) {
        super(rabbitAdmin);
    }

    @Autowired
    @ConditionalOnProperty(value = "rabbit.exchanges.project.enable", havingValue = "true")
    @Override
    public void declare(
            @Value("${rabbit.exchanges.project.queues.project.route-keys}") List<String> routeKeys,
            @Value("${rabbit.exchanges.project.name}") String exchangeName,
            @Value("${rabbit.exchanges.project.queues.project.name}") String queueName
    ) {
        binding(routeKeys, exchangeName, queueName);
    }
}
