package io.ai.comandside.controller;

import io.ai.comandside.command.project.AddTaskToProjectCommand;
import io.ai.comandside.command.project.AddUserToProjectCommand;
import io.ai.comandside.command.project.ChangeProjectNameCommand;
import io.ai.comandside.command.project.CreateProjectCommand;
import io.ai.comandside.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<HttpStatus> createProject(@RequestBody CreateProjectCommand command) {
        projectService.create(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-project-name")
    public ResponseEntity<HttpStatus> changeProjectName(@RequestBody ChangeProjectNameCommand command) {
        projectService.changeName(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add-user-in-project")
    public ResponseEntity<HttpStatus> addUserInProject(@RequestBody AddUserToProjectCommand command) {
        projectService.addUserToProject(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add-task-in-project")
    public ResponseEntity<HttpStatus> addTaskInProject(@RequestBody AddTaskToProjectCommand command) {
        projectService.addTaskToProject(command);
        return ResponseEntity.ok().build();
    }
}
