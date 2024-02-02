package io.ai.comandside.command.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskCommand {
    private UUID projectId;
    private String name;
    private String description;
    private UUID taskStatusId;
    private UUID userId;
}