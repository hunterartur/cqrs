package io.ai.comandside.repository;

import io.ai.comandside.constant.CommonConstant;
import io.ai.comandside.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, UUID> {

    @Query("FROM TaskStatus ts WHERE ts.name = '" + CommonConstant.DEFAULT_TASK_STATUS + "'")
    TaskStatus getDefaultTaskStatus();
}
