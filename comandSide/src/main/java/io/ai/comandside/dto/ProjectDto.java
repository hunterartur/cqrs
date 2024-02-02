package io.ai.comandside.dto;

import io.ai.comandside.valueobject.Information;

import java.util.List;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        Information information,
        List<UUID> members,
        List<UUID> taskIds
) {
}
