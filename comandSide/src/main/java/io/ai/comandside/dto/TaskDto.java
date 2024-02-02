package io.ai.comandside.dto;

import io.ai.comandside.entity.TaskStatus;
import io.ai.comandside.valueobject.Information;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TaskDto(
        UUID id,
        Information information,
        List<UUID> executors,
        TaskStatus taskStatus,
        LocalDateTime createdAt,
        UUID projectId
) {
}
