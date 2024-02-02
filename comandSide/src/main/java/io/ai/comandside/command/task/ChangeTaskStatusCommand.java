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
public class ChangeTaskStatusCommand {
    private UUID taskId;
    private UUID taskStatusId;
}