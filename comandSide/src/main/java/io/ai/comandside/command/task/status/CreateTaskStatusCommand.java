package io.ai.comandside.command.task.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskStatusCommand {
    private String name;
    private String color;
}
