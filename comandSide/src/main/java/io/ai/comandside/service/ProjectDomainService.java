package io.ai.comandside.service;

import io.ai.comandside.aggregate.ProjectAggregate;
import io.ai.comandside.event.project.CreatedProjectDomainEvent;
import io.ai.comandside.exception.ApplicationException;
import io.ai.comandside.repository.ProjectRepository;
import io.ai.comandside.repository.TaskRepository;
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
public class ProjectDomainService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final ApplicationEventPublisher publisher;

    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public void create(UUID userId, String name, String description) {
        var project = new ProjectAggregate(userId, name, description);
        var saveProject = projectRepository.save(project);
        publisher.publishEvent(new CreatedProjectDomainEvent(this, saveProject.getId(), userId));
    }

    @Transactional
    public void changeName(UUID projectId, String newName) {
        var project = getProjectById(projectId);
        var changedProjectNameDomainEvent = project.changeName(newName);
        projectRepository.save(project);
        publisher.publishEvent(changedProjectNameDomainEvent);
    }

    @Transactional
    public void addUser(UUID projectId, UUID userId) {
        var project = getProjectById(projectId);
        var addedUserToProjectDomainEvent = project.addUser(userId);
        projectRepository.save(project);
        publisher.publishEvent(addedUserToProjectDomainEvent);
    }

    @Transactional
    public void addTask(UUID projectId, UUID taskId) {
        checkTaskExistsById(taskId);
        var project = getProjectById(projectId);
        var addedTaskInProjectDomainEvent = project.addTask(taskId);
        projectRepository.save(project);
        publisher.publishEvent(addedTaskInProjectDomainEvent);
    }

    private ProjectAggregate getProjectById(UUID projectId) {
        if (projectId == null)
            throw new ApplicationException(HttpStatusCode.valueOf(400), "ID проекта не может быть NULL");
        return projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ApplicationException(HttpStatusCode.valueOf(404),
                                MessageFormat.format(ExceptionConstant.PROJECT_NOT_FOUND_EXCEPTION, projectId)));
    }

    private void checkTaskExistsById(UUID taskId) {
        if (taskId == null || !taskRepository.existsById(taskId)) {
            throw new ApplicationException(HttpStatusCode.valueOf(404),
                    MessageFormat.format(ExceptionConstant.TASK_NOT_FOUND_EXCEPTION, taskId));
        }
    }
}
