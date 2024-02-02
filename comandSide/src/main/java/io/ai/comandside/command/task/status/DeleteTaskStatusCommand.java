package io.ai.comandside.command.task.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Schema(description = "Команда: Удалить статус")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskStatusCommand {

    @Schema(description = "Идентификатор статуса")
    private UUID taskStatusId;
}
