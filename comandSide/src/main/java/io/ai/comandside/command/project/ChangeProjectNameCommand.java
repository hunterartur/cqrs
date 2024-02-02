package io.ai.comandside.command.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProjectNameCommand {
    private UUID projectId;
    private String newName;
}
