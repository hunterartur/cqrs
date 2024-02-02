package io.ai.comandside.controller;

import io.ai.comandside.command.project.AddTaskToProjectCommand;
import io.ai.comandside.command.project.AddUserToProjectCommand;
import io.ai.comandside.command.project.ChangeProjectNameCommand;
import io.ai.comandside.command.project.CreateProjectCommand;
import io.ai.comandside.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PROJECT CONTROLLER", description = "Контроллер по управлению проектами")
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Создать проект")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан проект и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<HttpStatus> createProject(@RequestBody CreateProjectCommand command) {
        projectService.create(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить имя проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Имя проекта успешно изменено и отправлено в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/change-project-name")
    public ResponseEntity<HttpStatus> changeProjectName(@RequestBody ChangeProjectNameCommand command) {
        projectService.changeName(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить пользователя в проект")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлен пользователь в проект и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/add-user-in-project")
    public ResponseEntity<HttpStatus> addUserInProject(@RequestBody AddUserToProjectCommand command) {
        projectService.addUserToProject(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить задачу в проект")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлена задача в проект и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/add-task-in-project")
    public ResponseEntity<HttpStatus> addTaskInProject(@RequestBody AddTaskToProjectCommand command) {
        projectService.addTaskToProject(command);
        return ResponseEntity.ok().build();
    }
}
