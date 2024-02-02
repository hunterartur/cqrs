package io.ai.comandside.service;

import io.ai.comandside.command.project.AddTaskToProjectCommand;
import io.ai.comandside.command.project.AddUserToProjectCommand;
import io.ai.comandside.command.project.ChangeProjectNameCommand;
import io.ai.comandside.command.project.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDomainService projectDomainService;

    public void create(CreateProjectCommand command) {
        projectDomainService.create(command.getUserId(), command.getName(), command.getDescription());
    }

    public void changeName(ChangeProjectNameCommand command) {
        projectDomainService.changeName(command.getProjectId(), command.getNewName());
    }

    public void addUserToProject(AddUserToProjectCommand command) {
        projectDomainService.addUser(command.getProjectId(), command.getUserId());
    }

    public void addTaskToProject(AddTaskToProjectCommand command) {
        projectDomainService.addTask(command.getProjectId(), command.getTaskId());
    }
}
