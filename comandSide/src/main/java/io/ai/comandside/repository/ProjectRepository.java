package io.ai.comandside.repository;

import io.ai.comandside.aggregate.ProjectAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectAggregate, UUID> {
}
