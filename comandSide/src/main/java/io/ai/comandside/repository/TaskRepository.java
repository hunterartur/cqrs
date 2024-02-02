package io.ai.comandside.repository;

import io.ai.comandside.aggregate.TaskAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskAggregate, UUID> {
}
