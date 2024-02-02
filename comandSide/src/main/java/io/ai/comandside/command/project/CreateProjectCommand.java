package io.ai.comandside.command.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Создать проект")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectCommand {

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Идентификатор пользователя")
    private UUID userId;
}
