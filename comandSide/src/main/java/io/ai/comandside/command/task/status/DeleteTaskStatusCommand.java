package io.ai.comandside.command.task.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskStatusCommand {
    private UUID taskStatusId;
}
