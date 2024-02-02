package io.ai.comandside.service;

import io.ai.comandside.command.task.AssignExecutorToTaskCommand;
import io.ai.comandside.command.task.ChangeTaskNameCommand;
import io.ai.comandside.command.task.ChangeTaskStatusCommand;
import io.ai.comandside.command.task.CreateTaskCommand;
import io.ai.comandside.command.task.status.CreateTaskStatusCommand;
import io.ai.comandside.command.task.status.DeleteTaskStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDomainService taskDomainService;

    public void create(CreateTaskCommand command) {
        taskDomainService.createTask(command.getProjectId(), command.getName(), command.getDescription(), command.getTaskStatusId(), command.getUserId());
    }

    public void changeName(ChangeTaskNameCommand command) {
        taskDomainService.changeName(command.getTaskId(), command.getNewName());
    }

    public void changeTaskStatus(ChangeTaskStatusCommand command) {
        taskDomainService.changeTaskStatus(command.getTaskId(), command.getTaskStatusId());
    }

    public void assignExecutor(AssignExecutorToTaskCommand command) {
        taskDomainService.assignExecutor(command.getTaskId(), command.getUserId());
    }

    public void createTaskStatus(CreateTaskStatusCommand command) {
        taskDomainService.createTaskStatus(command.getName(), command.getColor());
    }

    public void deleteTaskStatus(DeleteTaskStatusCommand command) {
        taskDomainService.deleteTaskStatus(command.getTaskStatusId());
    }
}
