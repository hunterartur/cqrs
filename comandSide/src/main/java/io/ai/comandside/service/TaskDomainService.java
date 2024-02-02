package io.ai.comandside.service;

import io.ai.comandside.aggregate.TaskAggregate;
import io.ai.comandside.constant.CommonConstant;
import io.ai.comandside.entity.TaskStatus;
import io.ai.comandside.event.task.CreatedTaskDomainEvent;
import io.ai.comandside.event.task.status.CreatedTaskStatusDomainEvent;
import io.ai.comandside.event.task.status.DeletedTaskStatusDomainEvent;
import io.ai.comandside.exception.ApplicationException;
import io.ai.comandside.repository.ProjectRepository;
import io.ai.comandside.repository.TaskRepository;
import io.ai.comandside.repository.TaskStatusRepository;
import io.ai.comandside.service.constant.ExceptionConstant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskDomainService {

    private final TaskRepository taskRepository;

    private final TaskStatusRepository taskStatusRepository;

    private final ProjectRepository projectRepository;

    private final ApplicationEventPublisher publisher;

    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public void createTask(UUID projectId, String name, String description, UUID taskStatusId, UUID userId) {
        checkProjectExistsById(projectId);
        var correctTaskStatus = getCorrectTaskStatus(taskStatusId);
        var createTask = new TaskAggregate(correctTaskStatus, name, description, projectId, userId);
        var createdTask = taskRepository.save(createTask);
        publisher.publishEvent(new CreatedTaskDomainEvent(this, createdTask.getId(), projectId));
    }

    @Transactional
    public void changeName(UUID taskId, String newName) {
        var updateTask = getTaskById(taskId);
        var updatedTaskDomainEvent = updateTask.changeName(newName);
        taskRepository.save(updateTask);
        publisher.publishEvent(updatedTaskDomainEvent);
    }

    @Transactional
    public void changeTaskStatus(UUID taskId, UUID taskStatusId) {
        var task = getTaskById(taskId);
        var taskStatus = getCorrectTaskStatus(taskStatusId);
        checkTaskStatus(taskStatusId, taskStatus);
        var changedTaskStatusDomainEvent = task.setTaskStatus(taskStatus);
        taskRepository.save(task);
        publisher.publishEvent(changedTaskStatusDomainEvent);
    }

    @Transactional
    public void assignExecutor(UUID taskId, UUID userId) {
        var task = getTaskById(taskId);
        var assignExecutorToProjectDomainEvent = task.addExecutor(userId);
        taskRepository.save(task);
        publisher.publishEvent(assignExecutorToProjectDomainEvent);
    }

    @Transactional
    public void createTaskStatus(String name, String color) {
        var createdTask = taskStatusRepository.save(new TaskStatus(name, color));
        publisher.publishEvent(new CreatedTaskStatusDomainEvent(this, createdTask.getId(), name, color));
    }

    @Transactional
    public void deleteTaskStatus(UUID taskStatusId) {
        try {
            taskStatusRepository.deleteById(taskStatusId);
        } catch (Exception e) {
            throw new ApplicationException(HttpStatusCode.valueOf(400), "ID не может быть NULL");
        }
        publisher.publishEvent(new DeletedTaskStatusDomainEvent(this, taskStatusId));
    }

    private TaskStatus getCorrectTaskStatus(UUID taskStatusId) {
        if (taskStatusId == null) {
            return taskStatusRepository.getDefaultTaskStatus();
        } else if (taskStatusRepository.existsById(taskStatusId)) {
            return taskStatusRepository.findById(taskStatusId).get();
        } else {
            throw new ApplicationException(HttpStatusCode.valueOf(404),
                    MessageFormat.format(ExceptionConstant.TASK_STATUS_NOT_FOUND_EXCEPTION, taskStatusId));
        }
    }

    private void checkProjectExistsById(UUID projectId) {
        if (projectId == null || !projectRepository.existsById(projectId)) {
            throw new ApplicationException(HttpStatusCode.valueOf(404),
                    MessageFormat.format(ExceptionConstant.PROJECT_NOT_FOUND_EXCEPTION, projectId));
        }
    }

    private TaskAggregate getTaskById(UUID taskId) {
        if (taskId == null) throw new ApplicationException(HttpStatusCode.valueOf(400), "ID не может быть NULL");
        return taskRepository.findById(taskId).orElseThrow(
                () -> new ApplicationException(HttpStatusCode.valueOf(404),
                        MessageFormat.format(ExceptionConstant.TASK_NOT_FOUND_EXCEPTION, taskId))
        );
    }

    private static void checkTaskStatus(UUID taskStatusId, TaskStatus taskStatus) {
        if (!taskStatus.getName().equals(CommonConstant.DEFAULT_TASK_STATUS)) {
            throw new ApplicationException(HttpStatusCode.valueOf(400),
                    MessageFormat.format(ExceptionConstant.TASK_STATUS_NOT_FOUND_EXCEPTION, taskStatusId));
        }
    }
}
