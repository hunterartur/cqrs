package io.ai.comandside.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity<HttpStatusCode> test() {
        rabbitTemplate.convertAndSend("Hello epta");
        return ResponseEntity.ok().build();
    }
}
