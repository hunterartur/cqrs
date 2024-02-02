package io.ai.comandside.command.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Добавить пользователя в проект")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToProjectCommand {

    @Schema(description = "Идентификатор проекта")
    private UUID projectId;

    @Schema(description = "Идентификатор пользователя")
    protected UUID userId;
}
