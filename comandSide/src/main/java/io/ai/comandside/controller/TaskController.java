package io.ai.comandside.controller;

import io.ai.comandside.command.task.AssignExecutorToTaskCommand;
import io.ai.comandside.command.task.ChangeTaskNameCommand;
import io.ai.comandside.command.task.ChangeTaskStatusCommand;
import io.ai.comandside.command.task.CreateTaskCommand;
import io.ai.comandside.command.task.status.CreateTaskStatusCommand;
import io.ai.comandside.command.task.status.DeleteTaskStatusCommand;
import io.ai.comandside.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TASK CONTROLLER", description = "Контроллер по управлению задачами и их статусами")
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Создать задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создана задача и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<HttpStatus> createTask(@RequestBody CreateTaskCommand command) {
        taskService.create(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить имя задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Имя задачи успешно изменено и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/change-task-name")
    public ResponseEntity<HttpStatus> changeTaskName(@RequestBody ChangeTaskNameCommand command) {
        taskService.changeName(command);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Изменить статус задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус задачи успешно изменен и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/change-task-status")
    public ResponseEntity<HttpStatus> changeTaskStatus(@RequestBody ChangeTaskStatusCommand command) {
        taskService.changeTaskStatus(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить исполнителя в задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлен исполнитель в задачу и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/assign-executor")
    public ResponseEntity<HttpStatus> assignExecutor(@RequestBody AssignExecutorToTaskCommand command) {
        taskService.assignExecutor(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Создать статус задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан статус для задачи и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PostMapping("/create-task-status")
    public ResponseEntity<HttpStatus> createTaskStatus(@RequestBody CreateTaskStatusCommand command) {
        taskService.createTaskStatus(command);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить статус задачи(статус задачи можно удалить если только данный статус не используется ни в каких задачах)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удзален статус задачи и отправлен в брокер сообщении"),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
            @ApiResponse(responseCode = "404", description = "Ошибка в переданных в теле запроса параметрах"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @DeleteMapping("/delete-task-status")
    public ResponseEntity<HttpStatus> deleteTaskStatus(@RequestBody DeleteTaskStatusCommand command) {
        taskService.deleteTaskStatus(command);
        return ResponseEntity.ok().build();
    }
}
