package io.ai.comandside.repository;

import io.ai.comandside.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskStatus, UUID> {
}
